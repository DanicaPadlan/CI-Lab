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
//recursive method too
static void infer_type(node_t *nptr) {
    //null check
    //base case perhaps
    if(nptr == NULL){
        return;
    }

    //we must change internal nodes and root value types without types
    if(nptr.node_type != NT_LEAF && nptr.type == NO_TYPE){
        //its based off of childrens types
         //loop through children and see their types
         //recursive part is going through children
        for(int x = 0; x < 3; x++){
            //analyze the children's type
            //should also take signs into account
            //if parent null, set to the first child's occurence
            if(nptr->type == NO_TYPE){
                nptr->type = nptr->children[x]->type;
                
              //checks if parent type and children type is not the same  
            } else if(nptr->type != nptr->children[x]->type){
                //C2 check?
                //checking for compatibility
                //string * int allowed if string and int and has times
                if((nptr->type == INT_TYPE && nptr->children[x]->type == STRING_TYPE) 
                    || (nptr->type == STRING_TYPE && nptr->children[x]->type == INT_TYPE)){
                        if(nptr->tok->ttype == TOK_TIMES){
                            nptr->type = STRING_TYPE;
                        }
                }
            } 
            //C2 check?
            //checks for signs compatibility with types***

            //what about combos? (int + string)
            //children and maybe token type decides
            // num +-*/ num = int
            //num * string = string
            //num <>||~ num = boolean

            //any other combo is an error
        }
    }
    return;
}

/* infer_root() - set the type of the root node based on the types of children
 * Parameter: A pointer to a root node, possibly NULL.
 * Return value: None.
 * Side effect: The type field of the node is updated. 
 */

//recursive? 
static void infer_root(node_t *nptr) {
    //checks for null
    if (nptr == NULL) return;
    // check running status
    if (terminate || ignore_input) return;

    // check for ID type
    if (nptr->type == ID_TYPE) {

        //calls infer_type
        infer_type(nptr->children[1]);
    } else {

        //goes through all children
        for (int i = 0; i < 3; ++i) {

            //calls infer_type
            infer_type(nptr->children[i]);
        }
        if (nptr->children[0] == NULL) {
            logging(LOG_ERROR, "failed to find child node");
            return;
        }
        //key line can be reused
        nptr->type = nptr->children[0]->type;
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
    //take current operation from cur node (+-*/%,etc)
    //determine action based on 
    //create variable based on curnode type?
    //?????? help
    //go through children nodes 
    for(int x = 0; x < 3; x++){
        if(nptr->children[x] != NULL){
            //checking for each sign


            //problem is looking at one child at a time, how to keep track of items? 
            //helper method? no hard coding children[0] and [1]!!!
            if(){

            }

        }
    }




    return;
}

/* eval_root() - set the value of the root node based on the values of children 
 * Parameter: A pointer to a root node, possibly NULL.
 * Return value: None.
 * Side effect: The val dield of the node is updated. 
 */

void eval_root(node_t *nptr) {
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

    for (int i = 0; i < 2; ++i) {
        eval_node(nptr->children[i]);
    }
    if (terminate || ignore_input) return;
    
    if (nptr->type == STRING_TYPE) {
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
    return NULL;
}