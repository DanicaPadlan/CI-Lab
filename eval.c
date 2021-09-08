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
    //printf("in infer_type\n");
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
        
            //if no_type
            if(nptr->children[x]-> type == NO_TYPE){
                infer_type(nptr->children[x]);
            }

            //*****work for checkpoint 2?
            //compare the curChild to the first child (since that will be what the parent will change to) 
            if(nptr->children[x]->type != nptr->children[0]->type){
                //compare for compatible result using 
                //have hierarchy of types?


            }
        }

    }
    //problem here but works in infer_root
    //problematic 
    nptr->type = nptr->children[0]->type;

    //use switch cases for 4+ checks
    //hard code checking to see if it works
    //nptr->type = INT_TYPE;
    
    //asssume operators will be compatible, just worry about kids

    /*
    printf("will loop through children[] \n");
    //segfault 
    //go through children
    for(int x = 0; x < 3; x++){
        //compare to first child
        //problem is right here
        if(nptr->children[x]->type == INT_TYPE){
            printf("type is int!\n");
        }
        
    }
    */


    //dereference the pointer somewhere

    //problem dereferencing
    //this is the problem
    //problem with referencing
    //nptr->type = nptr->children[0]->type;

    /*
    
    //null check
    //base case 
    if(nptr == NULL){
        //printf("nptr is null\n");
       return;
    } 
    //printf("nptr is not a null\n");
    //not sure if will use
    //get_type(nptr);
    //printf("passed get_type\n");
    //all changes happens to the first child
    //go through each child and set their type to nodes type
    for(int x = 0; x < 3; x++){
        printf("checking child %i \n", x);
        //checking type
        //problem is here
        if(nptr->children[x] != NULL){
            if(nptr->children[x]->type == INT_TYPE ){
                //printf("child is has a int type\n");
            }
        }
           
        if(nptr->children[x]->type != nptr->children[0]->type){
            printf("will debate the first child's new type!");
        }
    }
    */


    //printf("about to do for loop\n");
    //general case go through childrens types
    //want to get all of childrens types and basing off of dominate type to set parent node
    //type_t domType = NO_TYPE; //problem here<-
    //if internal type
    /*for(int x = 0; x < 3; x++){
        //infer_type();
        printf("going through children!");
        if(nptr->type == NO_TYPE){
            printf("no type");
        }
        //for now assuming its 
        //if(nptr->children[x]->type != NO_TYPE){
        //    printf("a child is not a NO_TYPE!!!");
        //    nptr->type = nptr->children[x]->type;
        //} else{
        //    printf("child is a NO_TYPE\n");
        //}
        printf("finished checking child\n");
        
    }*/

    //not setting up to anything
    //if(nptr->type == NO_TYPE){
    //    printf("cur node is nothing!!!");
    //}

    //printf("done with infer\n");
    /*
    //we must change internal nodes and root value types without types
    if(nptr->node_type != NT_LEAF && nptr->type == NO_TYPE){
        //its based off of childrens types
         //loop through children and see their types
         //recursive part is going through children
        for(int x = 0; x < 3; x++){
            //analyze the children's type
            //should also take signs into account
            //if parent null, set to the first child's occurence
            if(nptr->type == NO_TYPE && nptr->children[x] != NULL){
                printf("changed parent type");
                nptr->type = nptr->children[x]->type;
                
              //checks if parent type and children type is not the same  
            } else if(nptr->type != nptr->children[x]->type){
                //C2 check?
                //checking for compatibility
                //string * int allowed if string and int and has times
                if((nptr->type == INT_TYPE && nptr->children[x]->type == STRING_TYPE) 
                    || (nptr->type == STRING_TYPE && nptr->children[x]->type == INT_TYPE)){
                        if(nptr->tok == TOK_TIMES){
                            nptr->type = STRING_TYPE;
                        }
                }
            } 
            //C2 check?
            //checks for signs compatibility with types***

            //what about combos? (int + string)
            //children and maybe token type decides
            // num +-*/ //num = int
            //num * string = string
            //num <>||~ num = boolean

            //any other combo is an error
        //}
        
    //}
    return;
}


/* infer_root() - set the type of the root node based on the types of children
 * Parameter: A pointer to a root node, possibly NULL.
 * Return value: None.
 * Side effect: The type field of the node is updated. 
 */
static void infer_root(node_t *nptr) {
    //printf("in infer_root\n");
    //checks for null
    if (nptr == NULL){
        //printf("infer type null :( \n");
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

//add more for more opretations
static void compute(node_t *nptr, node_t *child1, node_t *child2 ){
    if(nptr->tok == TOK_PLUS){
        //printf("adding children values of %i and %i \n",child1->val.ival, child2->val.ival);
        nptr->val.ival = child1->val.ival + child2->val.ival;
    }
    //turn child into null to not be accessible anymore?
    nptr->children[0] = NULL;
    nptr->children[1] = NULL;
    //change node type
    nptr->node_type = NT_LEAF;
    nptr->tok = TOK_NUM;
    if(nptr->type == INT_TYPE){
        //printf("i created an int_type!\n");
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

//did not finish***????need to big brain think
static void eval_node(node_t *nptr) {
    //printf("in eval\n");
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
        //printf("i am looping\n");
        if(nptr->children[i] != NULL){
            eval_node(nptr->children[i]);
        }
    }

    if(nptr->children[0] != NULL && nptr->children[1] != NULL){
        
        compute(nptr, nptr->children[0], nptr->children[1]);
    }

    /*
    //go through children nodes after possible implementing kids 
    for(int x = 0; x < 3; x++){
        if(nptr->children[x] != NULL){
            

        }
    }
    */

    //printf("done with eval");
    return;
}

/* eval_root() - set the value of the root node based on the values of children 
 * Parameter: A pointer to a root node, possibly NULL.
 * Return value: None.
 * Side effect: The val dield of the node is updated. 
 */

void eval_root(node_t *nptr) {
    //printf("In eval_root \n");
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

    //debugging
    /*
    if(nptr->node_type == NT_ROOT && nptr->type == INT_TYPE){
        printf("Node value: %i\n", nptr -> val.ival);
    }
    */

    
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