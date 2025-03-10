/**************************************************************************
 * C S 429 EEL interpreter
 * 
 * variable.c - This file contains the skeleton of functions to be implemented
 * for EEL-2. When completed, it will contain the code to maintain a hashtable
 * for defined variables.
 * 
 * Work on it only after finishing EEL-0 and EEL-1.
 * 
 * Copyright (c) 2021. S. Chatterjee, X. Shen, T. Byrd. All rights reserved.
 * May not be used, modified, or copied without permission.
 **************************************************************************/ 

#include "ci.h"

table_t *var_table = NULL;
static char *bool_print[] = {"false", "true"};

void init_table(void) {
    var_table = (table_t *) calloc(1, sizeof(table_t));
    if (! var_table) {
        logging(LOG_FATAL, "failed to allocate table");
        return;
    }
    var_table->entries = (entry_t **) calloc(CAPACITY, sizeof(entry_t *));
    if (! var_table->entries) {
        free(var_table);
        logging(LOG_FATAL, "failed to allocate entries");
    }
    return;
}

void delete_entry(entry_t *eptr) {
    if (! eptr) return;
    if (eptr->type == STRING_TYPE) {
        free(eptr->val.sval);
    }
    free(eptr->id);
    free(eptr);
    return;
}

void delete_entries(entry_t *eptr) {
    if (! eptr) return;
    delete_entries(eptr->next);
    delete_entry(eptr);
    return;
}

void delete_table(void) {
    if (! var_table) return;

    for (int i = 0; i < CAPACITY; ++i) {
        delete_entries(var_table->entries[i]);
    }
    free(var_table->entries);
    free(var_table);
    return;
}

/* Pre-defined hash function to index variables by their names. */
unsigned long hash_function(char *s) {
    unsigned long i = 0;
    for (int j=0; s[j]; j++)
        i += s[j];
    return i % CAPACITY;
}

/* init_entry() - provided entry constructor
 * Parameters: Variable name, pointer to a node.
 * Return value: An allocated entry. */
entry_t * init_entry(char *id, node_t *nptr) {
    if (nptr == NULL) {
        logging(LOG_FATAL, "failed to allocate entry");
        return NULL;
    }
    entry_t *eptr = (entry_t *) calloc(1, sizeof(entry_t));
    //printf("i am mallocing\n");
    if (! eptr) {
        logging(LOG_FATAL, "failed to allocate entry");
        return NULL;
    }
    eptr->id = (char *) malloc(strlen(id) + 1);
    if (! id) {
        logging(LOG_FATAL, "failed to allocate entry id");
        free(eptr);
        return NULL;
    }
    strcpy(eptr->id, id);
    eptr->type = nptr->type;
    if (eptr->type == STRING_TYPE) {
        (eptr->val).sval = (char *) malloc(strlen(nptr->val.sval) + 1);
        if (! eptr->val.sval) {
            logging(LOG_FATAL, "failed to allocate string");
            free(eptr->id);
            free(eptr);
            return NULL;
        }
        strcpy(eptr->val.sval, nptr->val.sval);
    } else {
        //printf("i am saving an int variable\n");
        eptr->val.ival = nptr->val.ival;
    }
    //printf("malloced memory for variable\n");
    //print_entry(eptr);
    return eptr;
}

//think i did this decently
/* put() - insert an entry into the hashtable or update the existing entry.
 * Use a linked list to handle collisions.
 * Parameters: Variable name, pointer to a node.
 * Return value: None.
 * Side effect: The entry is inserted into the hashtable, or is updated if
 * it already exists.
 * (STUDENT TODO) 
 */

void put(char *id, node_t *nptr) {
    printf("setting up an entry in table\n");
    if(id == NULL || nptr == NULL){
        return;
        //error or return blank
    }
    //call hash function to get variables index
    long index = hash_function(id);

    if(index < 0 && index >= CAPACITY){
        printf("error! index is out of bounds\n ");
    } else{
        entry_t* check = get(id);
        //check if exist first
        if(check != NULL){
            if(nptr->type == INT_TYPE){
                check->type = INT_TYPE;
                check->val.ival = nptr->val.ival;
            } else if(nptr->type == STRING_TYPE){
                check->type = STRING_TYPE;
                //no need to worry about mallocing and freeing
                check->val.sval = nptr->val.sval;
            } else if(nptr->type == BOOL_TYPE){
                check->type = BOOL_TYPE;
                check->val.bval = nptr->val.bval; 
            } else{
                //error?
                printf("Not one of the valid types!\n");
            }

        //does not exist    
        } else{
            //problems, dont know how to 
            if(var_table->entries[index] == NULL){
                //its going in table just get() is not right
                //printf("index: %li\n", index);
                var_table->entries[index] =  init_entry(id, nptr);

                printf("printing table\n");
                print_table();
            } else{
                entry_t* curEntry = var_table->entries[index];
                //finds place to put 
                //if the next entry is null, stop loop 
                while(curEntry != NULL && curEntry->next != NULL){
                    curEntry = curEntry->next;
                }
                //build a entry and connect the curEntry's next to the new next
                curEntry->next = init_entry(id, nptr);
            }
        }
        

    }
    return;
}

//maybe i did this right
/* get() - search for an entry in the hashtable.
 * Parameter: Variable name.
 * Return value: Pointer to the matching entry, or NULL if not found.
 * (STUDENT TODO) 
 */
entry_t* get(char* id) {
    //printf("in get\n");
    //find according to return of hash function
    long index = hash_function(id);

    //this is null,
    entry_t* curEntry = var_table->entries[index];

    if(curEntry == NULL){
        //printf("getting my curEntry is null\n");
    }else{
        //printf("getting my curEntry is NOT null\n");
    }

    while(curEntry != NULL){
        //printf("searching for %s, the current entry is %s\n", id, curEntry->id);
        if(strcmp(id, curEntry->id) == 0){
            //printf("i am returning the entry i found!");
            return curEntry;
        }
        curEntry = curEntry->next;
    }
    //printf("returning null\n");
    return NULL;
}

void print_entry(entry_t *eptr) {
    if (! eptr) return;
    switch (eptr->type) {
        case INT_TYPE:
            fprintf(outfile, "%s = %d; ", eptr->id, eptr->val.ival);
            break;
        case BOOL_TYPE:
            fprintf(outfile, "%s = %s; ", eptr->id, bool_print[eptr->val.bval]);
            break;
        case STRING_TYPE:
            fprintf(outfile, "%s = \"%s\"; ", eptr->id, eptr->val.sval);
            break;
        default:
            logging(LOG_ERROR, "unsupported entry type for printing");
            break;
    }
    print_entry(eptr->next);
    return;
}

void print_table(void) {
    if (! var_table) {
        logging(LOG_ERROR, "variable table doesn't exist");
        return;
    }
    fprintf(outfile, "\t");
    for (int i = 0; i < CAPACITY; ++i) {
        print_entry(var_table->entries[i]);
    }
    fprintf(outfile, "\n");
    return;
}
