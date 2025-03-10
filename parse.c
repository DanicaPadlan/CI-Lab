/**************************************************************************
 * C S 429 EEL interpreter
 * 
 * parse.c - This file contains the skeleton of functions to be implemented by
 * you. When completed, it will contain the code used to parse an expression and
 * create an AST.
 * 
 * Copyright (c) 2021. S. Chatterjee, X. Shen, T. Byrd. All rights reserved.
 * May not be used, modified, or copied without permission.
 **************************************************************************/ 

#include "ci.h"

/* Explained in ci.h */
extern lptr_t this_token, next_token;
extern void init_lexer(void);
extern void advance_lexer(void);

/* Valid format specifers */
static const char *VALID_FMTS = "dxXbB";

/* The only reserved identifiers are "true" and "false" */
static const int NUM_RESERVED_IDS = 2;
static const struct {
    char *id;
    token_t t;
} reserved_ids[] = {
    {"true", TOK_TRUE},
    {"false", TOK_FALSE}
};

/* is_binop() - return true if a token represents a binary operator
 * Parameter: Any token
 * Return value: true if t is a binary operator, false otherwise */
bool is_binop(token_t t) {
    return t >= TOK_PLUS && t <= TOK_EQ;
}

/* is_unop() - return true if a token represents a unary operator
 * Parameter: Any token
 * Return value: true if t is a unary operator, false otherwise */
bool is_unop(token_t t) {
    return t >= TOK_UMINUS && t <= TOK_NOT;
}

/* id_is_fmt_spec() - return true if a string is a format specifier
 * Parameter: Any string
 * Return value: true if s is format specifier, false otherwise */
bool id_is_fmt_spec(char *s) {
    return strlen(s) == 1 && strspn(s, VALID_FMTS) == 1;
}

/* check_reserved_ids() - check if a given string is a reserved identifier
 * Parameter: Any string
 * Return value: true if s is a reserved identifier, false otherwise */
static token_t check_reserved_ids(char *s) {
    for (int i = 0; i < NUM_RESERVED_IDS; i++)
        if (strcmp(reserved_ids[i].id, s) == 0) return reserved_ids[i].t;
    return TOK_INVALID;
}

//build check terop function
bool is_terop(token_t t){
    return t >= TOK_QUESTION && t <= TOK_COLON;
}

bool is_literal(token_t t){
    return t >= TOK_NUM && t <= TOK_STR;
}


//build helper constructor method for build leaf
//static node_t *set_leaf(){ }

//build set constructor for unop 
static void set_unop(node_t *curNode, token_t tokenType){
    if(tokenType == TOK_UMINUS){
        curNode->tok = TOK_UMINUS;
    } else{
        curNode->tok = TOK_NOT;
    }
    return;
}

//build set constructor for binop
//static node_t *set_binop(){ }
static void set_binop(node_t *curNode, token_t tokenType){
    //figure out how to use switches after completing assignment
    if(this_token->ttype == TOK_PLUS){
        //printf("cur node is +\n");
        curNode->tok = TOK_PLUS;
    } else if(this_token->ttype == TOK_BMINUS){
        curNode->tok = TOK_BMINUS;
    } else if(this_token->ttype == TOK_TIMES){
        //printf("cur node is *\n");
        curNode->tok = TOK_TIMES;
    } else if(this_token->ttype == TOK_DIV){
        curNode->tok = TOK_DIV;
    } else if(this_token->ttype == TOK_MOD){
        curNode->tok = TOK_MOD;
    } else if(this_token->ttype == TOK_AND){
        curNode->tok = TOK_AND;
    } else if(this_token->ttype == TOK_OR){
        curNode->tok = TOK_OR;
    } else if(this_token->ttype == TOK_LT){
        curNode->tok = TOK_LT;
    } else if(this_token->ttype == TOK_GT){
        curNode->tok = TOK_GT;
    } else {
        curNode->tok = TOK_EQ;
    }
    return;
}

//build set constructor for terop
//static node_t *set_terop(){ }
static void set_terop(node_t *curNode, token_t tokenType){
    if(this_token->ttype == TOK_QUESTION){
        curNode->tok = TOK_QUESTION;
    } 
    return;
}

/* build_leaf() - create a leaf node based on this_token and / or next_token
 * Parameter: none
 * Return value: pointer to a leaf node
 * (STUDENT TODO) */
static node_t *build_leaf(void) {
    //build leaf, can access token, node type, val, type, children
    //printf("inside build_leaf\n");

    //base case return actual node, not the address
    node_t* leaf = calloc(1, sizeof(node_t));
    leaf->node_type = NT_LEAF;

    if(this_token->ttype == TOK_NUM){
        //compatible setting types
        //printf("I am a token! \n");
        leaf->tok = TOK_NUM;
        leaf->type = INT_TYPE;
        leaf->val.ival = atoi(this_token->repr); 
        //printf("I am done setting the token\n");
    } else if(this_token->ttype == TOK_STR){
        char* word = malloc(strlen(this_token->repr));
        strcpy(word, this_token->repr);
        //compatible setting type
        leaf->tok = TOK_STR;
        leaf->type = STRING_TYPE;
        leaf->val.sval = word; 
    } else if(this_token->ttype == TOK_FMT_SPEC){
        leaf->tok = TOK_FMT_SPEC;
        leaf->type = FMT_TYPE;
        leaf->val.fval = this_token->repr[0];

    } else if(check_reserved_ids(this_token->repr) == TOK_TRUE){
        //printf("created true leaf node\n");
        leaf->tok = TOK_TRUE;
        leaf->type = BOOL_TYPE;
        leaf->val.bval = true;
        //printf("bval %i\n", leaf->val.bval);
    } else if(check_reserved_ids(this_token->repr) == TOK_FALSE){
        //printf("created false leaf node\n");
        leaf->tok = TOK_FALSE;
        leaf->type = BOOL_TYPE;
        leaf->val.bval = 0;
        //printf("bval %i\n", leaf->val.bval);
        //do i need this??
    } else if(this_token->ttype == TOK_ID){
        //getting/setting var name
        char* varName = malloc(strlen(this_token->repr));
        strcpy(varName, this_token->repr);
        leaf->tok = TOK_ID;
        leaf->type = ID_TYPE;
        //setting variable name
        leaf->val.sval = varName;
    } else{
        //error?
    }

    //isn't all the children already null? do i really need to check?
    //special case for ID_TYPE //do i need this code? 
    if(leaf->type != ID_TYPE){
        //set children to null when leaf
        for(int x = 0; x < 3; x++){
            leaf->children[x] = NULL;
        }
    } 
    return leaf;
}

/* build_exp() - parse an expression based on this_token and / or next_token
 * Make calls to build_leaf() or build_exp() if necessary. 
 * Parameter: none
 * Return value: pointer to an internal node
 * (STUDENT TODO) */
static node_t *build_exp(void) {
    //printf("in build_exp\n");
    // check running status
    if (terminate || ignore_input) return NULL;
    
    token_t t;

    // The case of a leaf node is handled for you
    if (this_token->ttype == TOK_NUM){
        //debug
        //printf("returning num leaf\n");
        return build_leaf();
    }

    if (this_token->ttype == TOK_STR)
        return build_leaf();
    // handle the reserved identifiers, namely true and false
    if (this_token->ttype == TOK_ID) {
        if ((t = check_reserved_ids(this_token->repr)) != TOK_INVALID) {
            this_token->ttype = t;
        }
        return build_leaf();
    } else {
          
        // (STUDENT TODO) implement the logic for internal nodes
        //create empty node we arent sure yet
        //if its not a leaf, its an internal node
        node_t *curNode = calloc(1, sizeof(node_t));
        curNode->node_type = NT_INTERNAL;
        //currently don't know if there will be an expression or not
        curNode->type = NO_TYPE;

        //works for inner 
        //assumes cur node starts with l parent
        //checks for l_paren 
        if(this_token->ttype == TOK_LPAREN){
            int childNum = 0;
            //printf("Passed Parenthesis, entering new level\n");

            //loop cursor until reach RParen
            while(this_token->ttype != TOK_RPAREN && this_token->ttype != TOK_EOL && childNum < 4){
                advance_lexer();
                //printf("Curtoken to evaluate is: %s\n", this_token->repr);
                if(this_token->ttype == TOK_LPAREN){
                    //printf("Theres another L_paren");
                    curNode->children[childNum] = build_exp();
                    //advances to move past l_paren
                    if(next_token->ttype != TOK_EOL){
                        advance_lexer();
                    }
                    childNum++;
                    //printf("done with level\n");
                } 
                
                if(is_unop(this_token->ttype)){
                    set_unop(curNode,this_token->ttype);
                //checking binary operations
                } else if(is_binop(this_token->ttype)){
                    set_binop(curNode,this_token->ttype);
                //checking tertiary terms 
                } else if(is_terop(this_token->ttype)){
                    if(curNode->tok != TOK_QUESTION){
                        set_terop(curNode, this_token->ttype);
                    } else if(curNode->tok == TOK_QUESTION && this_token->ttype != TOK_COLON){
                        handle_error(ERR_SYNTAX);
                    }
                    //dont do anything with the colon token 

                //checks for variable types, special case first time creation
                } else if(this_token->ttype == TOK_ID && next_token->ttype == TOK_ASSIGN){
                    //note: parent node - type ID
                    //      child[0] - create string/variable name
                    //      child[1] - set value

                    //set current to tok id 
                    //curNode->tok = TOK_ID; //didnt need to do this

                    //call build leaf to set the variable name for children
                    curNode->children[0] = build_leaf();

                    //advance to skip cur variable name to =
                    advance_lexer();
                    //advance from = to the actual value of the variable
                    advance_lexer();

                    //either catches literal type early or goes on in reading the line
                    curNode->children[1] = build_exp();

                //checks for literals and strings, and assuming already developed strings
                } else if(is_literal(this_token->ttype) || check_reserved_ids(this_token->repr) != TOK_INVALID){
                    //printf("I am in literal\n");
                    curNode->children[childNum] = build_exp();
                    childNum++;
                //special variable case? maybe works idk    
                } 
                /*
                else if(this_token->ttype == TOK_RPAREN && next_token->ttype == TOK_RPAREN){
                     advance_lexer();
                } 
                */
                //printf("I am still in the ()\n");
            }
            //printf("i got out of the level! going back up to parent\n");
            
        } 
        return curNode;
    }
}

/* build_root() - construct the root of the AST for the current input
 * This function is provided to you. Use it as a reference for your code
 * Parameter: none
 * Return value: the root of the AST */
static node_t *build_root(void) {
    //printf("creating a new root and tree\n");
    // check running status
    if (terminate || ignore_input) return NULL;

    // allocate memory for the root node
    node_t *ret = calloc(1, sizeof(node_t));
    if (! ret) {
        // calloc returns NULL if memory allocation fails
        logging(LOG_FATAL, "failed to allocate node");
        return NULL;
    }

    // set the node struct's fields
    ret->node_type = NT_ROOT;
    ret->type = NO_TYPE;

    // (EEL-2) check for variable assignment
    //if do nothing and '='
    if (this_token->ttype == TOK_ID && next_token->ttype == TOK_ASSIGN) {
        //checks if not a reserved identifier
        if (check_reserved_ids(this_token->repr) != TOK_INVALID) {
            logging(LOG_ERROR, "variable name is reserved");
            return ret;
        }

        //changes type of the current node for what it evaluates to later
        ret->type = ID_TYPE;

        //calls build leaf for children [0]
        //to build string name perhaps
        ret->children[0] = build_leaf();

        //moves from varName to =
        advance_lexer();
        // = to value the variable is being set to 
        advance_lexer();
        //if literal, it will set other child to value of the variable
        ret->children[1] = build_exp();
        
        if (next_token->ttype != TOK_EOL) {
            //printf("next token is not TOK_EOL\n");
            handle_error(ERR_SYNTAX);
        }

        return ret;
    }
    
    // build an expression based on the current token
    // this will be where the majority of the tree is recursively constructed
    ret->children[0] = build_exp();

    //not reaching EOL
    // if the next token is End of Line, we're done
    if (next_token->ttype == TOK_EOL){
        //printf("no more tokens\n");
        return ret;
    }
    else {                                     
    /* At this point, we've finished building the main expression. The only
     * syntactically valid tokens that could remain would be format specifiers */    
        // check that our next token is a format specifier
        if (next_token->ttype != TOK_SEP) {
            //******** errors reach here??
            //printf("next token is not TOK_SEP\n");
            handle_error(ERR_SYNTAX);
            return ret;
        }

        advance_lexer();
        
        // check that there is an ID following the format specifier
        if (next_token->ttype != TOK_ID) {
            //printf("next token is not TOK_ID\n");
            handle_error(ERR_SYNTAX);
            return ret;
        }

        // check that the ID is a format specifier ID
        if (id_is_fmt_spec(next_token->repr))
            next_token->ttype = TOK_FMT_SPEC;
        if (next_token->ttype != TOK_FMT_SPEC) {
            //printf("next token is not TOK_FMT_SPEC\n");
            handle_error(ERR_SYNTAX);
            return ret;
        }

        advance_lexer();

        // build the leaf for the format specifier. 
        // if any tokens besides EOL remain, the syntax is not valid
        ret->children[1] = build_leaf();
        if (next_token->ttype != TOK_EOL) {
            //printf("next token is not TOK_EOL2\n");
            handle_error(ERR_SYNTAX);
            return ret;
        }

        return ret;
    }

    //printf("overall err syntax\n");
    // this return statement will only be reached if there was a syntax error
    handle_error(ERR_SYNTAX);
    return ret;
}

/* read_and_parse - return the root of an AST representing the current input
 * Parameter: none
 * Return value: the root of the AST */
node_t *read_and_parse(void) {
    init_lexer();
    return build_root();
}

//do this after
/* cleanup() - given the root of an AST, free all associated memory
 * Parameter: The root of an AST
 * Return value: none
 * (STUDENT TODO) */
void cleanup(node_t *nptr) {
    // Is it enough to free the node the function called upon?
    //starts at root 
    /*
    if(nptr == NULL){
        return;
    }

    //loop through children
    for(int x = 0; x < 3; x++){
        cleanup(nptr->children[x]);
    }

    if(nptr->type == STRING_TYPE){
        free(nptr->val.sval);
    }
    */
    free(nptr);
    return;
}
