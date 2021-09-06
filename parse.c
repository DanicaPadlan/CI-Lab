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

//did partially
//*******!!!!!!
/* build_leaf() - create a leaf node based on this_token and / or next_token
 * Parameter: none
 * Return value: pointer to a leaf node
 * (STUDENT TODO) */
static node_t *build_leaf(void) {
    //build leaf, can access token, node type, val, type, children

    //base case return actual node, not the address
    node_t* leaf;

    if(this_token->ttype == TOK_NUM){
        //set it to *leaf? leaf>
        leaf = malloc(sizeof(atoi(this_token->repr)));

        //incompatible setting types
        leaf->tok = TOK_NUM;
        leaf->node_type = NT_LEAF;
        leaf->type = INT_TYPE;

        //not sure if i set it right
        leaf->val.ival = atoi(this_token->repr); 

    } else if(this_token->ttype == TOK_STR){
        //malloc node
        leaf = malloc(sizeof(this_token->repr) + 1);

        //incompatible setting type
        leaf->tok = TOK_STR;
        leaf->node_type = NT_LEAF;
        leaf->type = STRING_TYPE;

        //not sure if i set it right***
        leaf->val.sval = this_token->repr; 
    } 
    //more else statements?*****

    //no children if leaf is base case, returns address of leaf
    return leaf;
}

//********!!!!!!! did partially
/* build_exp() - parse an expression based on this_token and / or next_token
 * Make calls to build_leaf() or build_exp() if necessary. 
 * Parameter: none
 * Return value: pointer to an internal node
 * (STUDENT TODO) */
static node_t *build_exp(void) {
    // check running status
    if (terminate || ignore_input) return NULL;
    
    token_t t;

    // The case of a leaf node is handled for you
    if (this_token->ttype == TOK_NUM)
        return build_leaf();
    if (this_token->ttype == TOK_STR)
        return build_leaf();
    // handle the reserved identifiers, namely true and false
    if (this_token->ttype == TOK_ID) {
        if ((t = check_reserved_ids(this_token->repr)) != TOK_INVALID) {
            this_token->ttype = t;
        }
        return build_leaf();
    } else {

        //!!!!!!*****   
        // (STUDENT TODO) implement the logic for internal nodes
        //create empty node we arent sure yet
        node_t *curNode = malloc(sizeof(node_t));
        curNode->node_type = NT_INTERNAL;
        //currently don't know if there will be an expression or not
        curNode->type = NO_TYPE;

        //left parenthesis calls for new subtree
        if(this_token->ttype == TOK_LPAREN){
            
            //should we check for certain types? more types?
            //look at next token
            if(next_token->ttype == TOK_NUM || next_token->ttype == TOK_STR){
                //change progress to next token
                advance_lexer();

                //check for which child is empty? 
                curNode->children[0] = build_exp();
                //after setting child, it comes back up a step 
            }

        }

        //put somewhere else?
        //when to check for binop? this_token or next_token
        //if binary operator
        if(is_binop(this_token->ttype)){
            //set curnode to appropriate expression
            if(this_token->ttype == TOK_PLUS){
                curNode->tok = TOK_PLUS;

                //should expression signs be string/*sval
                curNode->val.sval = this_token->repr;
            } else if(this_token->ttype == TOK_BMINUS){
                curNode->tok = TOK_BMINUS;
            } else if(this_token->ttype == TOK_TIMES){
                curNode->tok = TOK_TIMES;
            } else if(this_token->ttype == TOK_DIV){
                curNode->tok = TOK_DIV;
            } else if(this_token->ttype == TOK_MOD){
                curNode->tok = TOK_MOD;
            }
            //should expression signs be string/*sval
            curNode->val.sval = this_token->repr;

            //after checking and setting up, we should move on to next token

        } 

        //return the node that we set up? or there can be instances when we do not return anything
        //if right parenthesis, subtree is done go back up and send nothing
        return NULL;
    }
}

/* build_root() - construct the root of the AST for the current input
 * This function is provided to you. Use it as a reference for your code
 * Parameter: none
 * Return value: the root of the AST */
static node_t *build_root(void) {
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

        //changes type
        ret->type = ID_TYPE;

        //calls build leaf for children [0]
        ret->children[0] = build_leaf();


        advance_lexer();
        advance_lexer();
        ret->children[1] = build_exp();
        if (next_token->ttype != TOK_EOL) {
            handle_error(ERR_SYNTAX);
        }
        return ret;
    }
    
    // build an expression based on the current token
    // this will be where the majority of the tree is recursively constructed
    ret->children[0] = build_exp();

    // if the next token is End of Line, we're done
    if (next_token->ttype == TOK_EOL)
        return ret;
    else {                                     
    /* At this point, we've finished building the main expression. The only
     * syntactically valid tokens that could remain would be format specifiers */    
        
        // check that our next token is a format specifier
        if (next_token->ttype != TOK_SEP) {
            handle_error(ERR_SYNTAX);
            return ret;
        }

        advance_lexer();
        
        // check that there is an ID following the format specifier
        if (next_token->ttype != TOK_ID) {
            handle_error(ERR_SYNTAX);
            return ret;
        }

        // check that the ID is a format specifier ID
        if (id_is_fmt_spec(next_token->repr))
            next_token->ttype = TOK_FMT_SPEC;
        if (next_token->ttype != TOK_FMT_SPEC) {
            handle_error(ERR_SYNTAX);
            return ret;
        }

        advance_lexer();

        // build the leaf for the format specifier. 
        // if any tokens besides EOL remain, the syntax is not valid
        ret->children[1] = build_leaf();
        if (next_token->ttype != TOK_EOL) {
            handle_error(ERR_SYNTAX);
            return ret;
        }
        return ret;
    }

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

//******!!!!!!!
/* cleanup() - given the root of an AST, free all associated memory
 * Parameter: The root of an AST
 * Return value: none
 * (STUDENT TODO) */
void cleanup(node_t *nptr) {
    // Is it enough to free the node the function called upon?
    free(nptr);
    return;
}
