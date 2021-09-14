/**************************************************************************
 * C S 429 EEL interpreter
 * 
 * eval.c - This file contains the skeleton of functions to be implemented by
 * you. When completed, it will contain the code used to evaluate an expression
 * based on its AST.
 * 
 * Copyright (c) 2021. S. Chatterjee, X. Shen, T. Byrd. All rights reserved.
 * May not be used, modified, or copied without permission.
 **************************************************************************/ 

#include "ci.h"

extern bool is_binop(token_t);
extern bool is_unop(token_t);
char *strrev(char *str);

//*******!!!!!!!
/* infer_type() - set the type of a non-root node based on the types of children
 * Parameter: A node pointer, possibly NULL.
 * Return value: None.
 * Side effect: The type field of the node is updated.
 * (STUDENT TODO)
 */
//recursive method too**** fix bc must go to leaves//????help need to do bc it effects eval
static void infer_type(node_t *nptr) {
    printf("in infer_type\n");
    //sent in a node
    //look at children 
    //set up the children types

    if(nptr == NULL || nptr->node_type == NT_LEAF){
        //printf("pointer is null\n");
        return;
    }

    //printf("i will loop through children \n");
    //go and check each child for type
    for(int x = 0; x < 3; x++){    
        if(nptr->children[x] != NULL){
            //check no type was here
            //if no_type //change place of this
            if(nptr->children[x]-> type == NO_TYPE){
                //printf("going through children\n");
                infer_type(nptr->children[x]);
            }
        }
    }

    if(is_unop(nptr->tok)){
        if(nptr->tok == TOK_NOT && nptr->children[0]->type != BOOL_TYPE ){
            handle_error(ERR_TYPE);
        } else if(nptr->tok == TOK_UMINUS && nptr->children[0]->type != STRING_TYPE && nptr->children[0]->type != INT_TYPE ){
              handle_error(ERR_TYPE);
        }

    } else if(is_binop(nptr->tok)){
        if(nptr->children[0]->type != nptr->children[1]->type){
        //printf("we are not the same chilren ttype\n");
         if(nptr->children[0]->type == STRING_TYPE && nptr->children[1]->type == INT_TYPE){
            if(nptr->tok == TOK_PLUS || nptr->tok == TOK_BMINUS || nptr->tok == TOK_DIV || nptr->tok == TOK_MOD 
            || nptr->tok == TOK_AND || nptr->tok == TOK_OR){
                handle_error(ERR_TYPE);
                }
            } else{
              handle_error(ERR_TYPE);
         }
        } else if( (nptr->tok == TOK_AND || nptr->tok == TOK_OR || nptr->tok == TOK_NOT) && nptr->children[0]->type != BOOL_TYPE){
             handle_error(ERR_TYPE);
         } else if( (nptr->tok == TOK_LT || nptr->tok == TOK_GT) && nptr->children[0]->type == BOOL_TYPE){
         handle_error(ERR_TYPE);
        }
        //ternary operation check
    } else{
        if(nptr->children[0]->type != BOOL_TYPE || nptr->children[1]->type != nptr->children[2]->type ){
        handle_error(ERR_TYPE);
        }
    }

    //set based on child
    nptr->type = nptr->children[0]->type;

    //set based on boolean types
    if(nptr->tok == TOK_LT || nptr->tok == TOK_GT || nptr->tok == TOK_EQ || nptr->tok == TOK_OR || nptr->tok == TOK_AND || nptr->tok == TOK_NOT){
        nptr->type = BOOL_TYPE;
    }

    return;
}


/* infer_root() - set the type of the root node based on the types of children
 * Parameter: A pointer to a root node, possibly NULL.
 * Return value: None.
 * Side effect: The type field of the node is updated. 
 */
static void infer_root(node_t *nptr) {
    printf("in infer_root\n");
    //checks for null
    if (nptr == NULL){
        printf("infer type null : \n");
        return; 
    } 
    
    // check running status
    if (terminate || ignore_input) return;

    // check for ID type
    if (nptr->type == ID_TYPE) {

        //calls infer_type
        infer_type(nptr->children[1]);
    } else {
        //goes through all children,recursive step?
        for (int i = 0; i < 3; ++i) {

            //calls infer_type
            //sets up children's type recursively?
            infer_type(nptr->children[i]);
        }
        if (nptr->children[0] == NULL) {
            logging(LOG_ERROR, "failed to find child node");
            return;
        }
        //key line can be reused
        //sets up type in according to the very first child
        nptr->type = nptr->children[0]->type;

    }
    //printf("done with infer root\n");
    return;
}

//checks if appropriate children have been populated
bool checkChildren(int lastChild, node_t *curNode){
    for(int x = 2; x >= 0 ; x++){
        if(curNode->children[x] != NULL && x > lastChild){
            return false;
        }
    }
    return true;
}

void nullChildren(node_t *curNode){
    for(int x = 0; x < 3; x++){
        curNode->children[x] = NULL;
    }
    return;
}

//******!!!!!!!
/* eval_node() - set the value of a non-root node based on the values of children
 * Parameter: A node pointer, possibly NULL.
 * Return value: None.
 * Side effect: The val field of the node is updated.
 * (STUDENT TODO) 
 */

static void eval_node(node_t *nptr) {
    printf("in eval node\n");

    //take current operation from cur node (+-*/%,etc)
    //determine action based on 
    //create variable based on curnode type?
    //?????? help

    //printf("I am about to check pre-conditions\n");
    if(nptr == NULL || nptr->node_type == NT_LEAF){
        //printf("i check pre-conditions\n");
        return;
    }

    //printf("i will loop\n");
    //if not the bottom of the tree, go down recursively
    //check children first, should compute all the way down
    for(int i = 0; i < 3; i++){
        printf("i am looping\n");
        eval_node(nptr->children[i]);
        
    }

    if(nptr->type == STRING_TYPE){
        printf("i am a string type\n");
        char* word;
        if(nptr->tok == TOK_PLUS){
            if(nptr->children[0]->type == STRING_TYPE && nptr->children[1]->type == STRING_TYPE){
                printf("I will add 2 strings together\n");
                word = malloc((strlen(nptr->children[0]->val.sval) - 1) + strlen(nptr->children[1]->val.sval));
                strcat(word, nptr->children[0]->val.sval);
                strcat(word, nptr->children[1]->val.sval);
                //printf("%s\n", word);
                nptr->val.sval = word;
                nptr->tok = TOK_STR;
            } else{
                handle_error(ERR_EVAL);
            }

        } else if(nptr->tok == TOK_TIMES){
            if(nptr->children[0]->type == STRING_TYPE && nptr->children[1]->type == INT_TYPE){
                    //printf("will mulitply\n");
                    int times = nptr->children[1]->val.ival;
                    word = calloc(times, (strlen(nptr->children[0]->val.sval)));
                    for(int x = 0; x < times; x++){
                        strcat(word, nptr->children[0]->val.sval);
                    }
                    nptr->val.sval = word;
                    nptr->tok = TOK_STR;
            }
        } else if(nptr->tok == TOK_UMINUS){
            printf("going in uminus\n");
            word = strrev(nptr->children[0]->val.sval);
            nptr->val.sval = word;
            nptr->tok = TOK_STR;
        } else{
            handle_error(ERR_EVAL);
        }

    
    } else if(nptr->type == BOOL_TYPE){
        printf("entering boolean type\n");
        if(nptr->tok == TOK_EQ){
            if(nptr->children[0]->type == BOOL_TYPE && nptr->children[1]->type == BOOL_TYPE){
                nptr->val.bval = (nptr->children[0]->val.bval == nptr->children[1]->val.bval);

            } else if(nptr->children[0]->type == STRING_TYPE && nptr->children[1]->type == STRING_TYPE){
                int result = strcmp(nptr->children[0]->val.sval,nptr->children[1]->val.sval );
                nptr->val.bval = (result == 0 ? true : false);
                nptr->tok = (nptr->val.bval == true ? TOK_TRUE : TOK_FALSE);

            } else if(nptr->children[0]->type == INT_TYPE && nptr->children[1]->type == INT_TYPE){
                nptr->val.bval = (nptr->children[0]->val.ival == nptr->children[1]->val.ival);
                nptr->tok = (nptr->val.bval == true ? TOK_TRUE : TOK_FALSE);
            } else{
                handle_error(ERR_EVAL);
            }

        } else if(nptr->tok == TOK_GT){
            if(nptr->children[0]->type == STRING_TYPE && nptr->children[1]->type == STRING_TYPE){
                int result = strcmp(nptr->children[0]->val.sval,nptr->children[1]->val.sval);
                nptr->val.ival = (result > 0 ? true : false);
                nptr->tok = (nptr->val.bval > 0 ? TOK_TRUE : TOK_FALSE);

            } else if(nptr->children[0]->type == INT_TYPE && nptr->children[1]->type == INT_TYPE){
                nptr->val.bval = (nptr->children[0]->val.ival > nptr->children[1]->val.ival);
                nptr->tok = (nptr->val.bval == true ? TOK_TRUE : TOK_FALSE);
            }

        } else if(nptr->tok == TOK_LT){
            if(nptr->children[0]->type == STRING_TYPE && nptr->children[1]->type == STRING_TYPE){
                int result = strcmp(nptr->children[0]->val.sval,nptr->children[1]->val.sval );
                nptr->val.bval = (result < 0 ? true : false);
                nptr->tok = (nptr->val.bval == true ? TOK_TRUE : TOK_FALSE);

            } else if(nptr->children[0]->type == INT_TYPE && nptr->children[1]->type == INT_TYPE){
                nptr->val.bval = (nptr->children[0]->val.ival < nptr->children[1]->val.ival);
                nptr->tok = (nptr->val.bval == true ? TOK_TRUE : TOK_FALSE);
            }

        } else if(nptr->tok == TOK_OR){
            if(nptr->children[0]->type == BOOL_TYPE && nptr->children[1]->type == BOOL_TYPE){
                nptr->val.bval = (nptr->children[0]->val.bval || nptr->children[1]->val.bval);
                nptr->tok = (nptr->val.bval == true ? TOK_TRUE : TOK_FALSE);
            }
        } else if(nptr->tok == TOK_AND){
            if(nptr->children[0]->type == BOOL_TYPE && nptr->children[1]->type == BOOL_TYPE){
                nptr->val.bval = (nptr->children[0]->val.bval && nptr->children[1]->val.bval);
                nptr->tok = (nptr->val.bval == true ? TOK_TRUE : TOK_FALSE);
            }

            //booleans not working
        } else if(nptr->tok == TOK_NOT){
            if(nptr->children[0]->type == BOOL_TYPE){
                printf("boolean type is: %i\n", nptr->val.bval);
                nptr->val.bval = (nptr->children[0]->val.bval) == true ? false : true;

                nptr->tok = (nptr->val.bval == true ? TOK_TRUE : TOK_FALSE);
                printf("boolean type is: %i\n", nptr->val.bval);
            }
        } else{
            handle_error(ERR_EVAL);
        }

    } else if(nptr->type == INT_TYPE){
        if(nptr->tok == TOK_PLUS){
                nptr->val.ival = nptr->children[0]->val.ival + nptr->children[1]->val.ival;
                nptr->tok = TOK_NUM;
            } else if(nptr->tok == TOK_BMINUS){
                nptr->val.ival = nptr->children[0]->val.ival - nptr->children[1]->val.ival;
                nptr->tok = TOK_NUM;
            } else if(nptr->tok == TOK_TIMES){
                nptr->val.ival = nptr->children[0]->val.ival * nptr->children[1]->val.ival;
                nptr->tok = TOK_NUM;
            } else if(nptr->tok == TOK_DIV){

                if(nptr->children[1]->val.ival != 0){
                    nptr->val.ival = nptr->children[0]->val.ival / nptr->children[1]->val.ival;
                    nptr->tok = TOK_NUM;
                } else{
                    handle_error(ERR_EVAL);
                }
            } else if(nptr->tok == TOK_MOD){

                if(nptr->children[1]->val.ival != 0){
                   nptr->val.ival = nptr->children[0]->val.ival % nptr->children[1]->val.ival; 
                   nptr->tok = TOK_NUM;
                } else{
                    handle_error(ERR_EVAL);
                }
                
            } else if(nptr->tok == TOK_UMINUS){
                nptr->val.ival = nptr->children[0]->val.ival * (-1);
                nptr->tok = TOK_NUM;
            } else{
                handle_error(ERR_EVAL);
            }
    } else{
        handle_error(ERR_EVAL);
    }
   
    nullChildren(nptr);
    printf("done with eval node\n");
    return;
}

/* eval_root() - set the value of the root node based on the values of children 
 * Parameter: A pointer to a root node, possibly NULL.
 * Return value: None.
 * Side effect: The val dield of the node is updated. 
 */

void eval_root(node_t *nptr) {
    printf("In eval_root \n");
    if (nptr == NULL) return; 
    // check running status
    if (terminate || ignore_input) return;

    // check for assignment
    if (nptr->type == ID_TYPE) {
        eval_node(nptr->children[1]);
        if (terminate || ignore_input) return;
        
        if (nptr->children[0] == NULL) {
            logging(LOG_ERROR, "failed to find child node");
            return;
        }
        put(nptr->children[0]->val.sval, nptr->children[1]);
        return;
    }

    //printf("about to loop through root's children!\n");
    for (int i = 0; i < 2; ++i) {
        eval_node(nptr->children[i]);
    }
    if (terminate || ignore_input) return;
    
    if (nptr->type == STRING_TYPE) {
        //debugging
        printf("I caught a string type in eval_root\n");
        if(nptr->children[0]->val.sval == NULL){
            printf("the child is null tho\n");
        }

        (nptr->val).sval = (char *) malloc(strlen(nptr->children[0]->val.sval) + 1);
        if (! nptr->val.sval) {
            logging(LOG_FATAL, "failed to allocate string");
            return;
        }
        strcpy(nptr->val.sval, nptr->children[0]->val.sval);
    } else {
        nptr->val.ival = nptr->children[0]->val.ival;
    }
    return;
}

/* infer_and_eval() - wrapper for calling infer() and eval() 
 * Parameter: A pointer to a root node.
 * Return value: none.
 * Side effect: The type and val fields of the node are updated. 
 */

void infer_and_eval(node_t *nptr) {
    infer_root(nptr);
    eval_root(nptr);
    return;
}

//!!!!!!!*******
/* strrev() - helper function to reverse a given string 
 * Parameter: The string to reverse.
 * Return value: The reversed string. The input string is not modified.
 * (STUDENT TODO)
 */

char *strrev(char *str) {
    char* rev = malloc(strlen(str) + 1);
    int revPlace = 0;
    for(int x = strlen(str)-1; x >=0; x--){
        rev[revPlace] = str[x];
        revPlace++;
    }
    rev[revPlace] = '\0';
    printf("revstring is %s", rev);
    return rev;
}