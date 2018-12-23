//Samer Baslan
//sbaslan
//CMPS 12B
//08-18-2017
//PA5
//Dictionary ADT with hashing


#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>
#include "Dictionary.h"

const int tableSize = 101;

// prototype freeArray
void freeArray(Dictionary D);

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input){
   unsigned int result = 0xBAE86554;
   while (*input) {
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}
  
// private types --------------------------------------------------------------


// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v){
   Node N = malloc(sizeof(NodeObj));
   assert(N != NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if(pN != NULL && *pN != NULL){
      free(*pN);
      *pN = NULL;
   }
}

// create dictionary struct
typedef struct DictionaryObj{
   Node* hashtable;
   int numItems;
} DictionaryObj;

// findKey()
// returns a reference to the Node containing key k or returns NULL if no such Node exists
Node findKey(Node R, char* k){
   while(R != NULL){
      if(strcmp(R->key, k) == 0){
         return R;
      }
      else{
         R = R->next;
      }
   } 
   return NULL;
}
// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D != NULL);
   D->hashtable = calloc(tableSize, sizeof(NodeObj));
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary *pD){
   if(pD != NULL && *pD != NULL){
      if(isEmpty(*pD) == 0){
         makeEmpty(*pD);
      }
      freeArray(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
int isEmpty(Dictionary D){
   if(D == NULL){
      fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return (D->numItems == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   if(D == NULL){
      fprintf(stderr, "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return (D->numItems);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns D if no
// such value v exists
// pre: none
char* lookup(Dictionary D, char* k){
   int arrayIndex = hash(k);
   Node P = findKey(D->hashtable[arrayIndex], k);
   if(P == NULL){
      return NULL;
   }
   else{
      return P->value;
   }
}

// insert()
// inserts new (key, value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   int arrayIndex = hash(k);
   Node N = newNode(k, v);
   if(findKey(D->hashtable[arrayIndex], k) != NULL){
      fprintf(stderr, "Dictionary Error: cannot insert duplicate key: %s\n", k);
      exit(EXIT_FAILURE);
   }
   N->next = D->hashtable[arrayIndex];
   D->hashtable[arrayIndex] = N;
   N = NULL;
   D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char*k){
   if(D == NULL){
      fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   
   Node N;
   int arrayIndex = hash(k);

   if(findKey(D->hashtable[arrayIndex], k) == NULL){
      fprintf(stderr, "Dictionary Error: cannot delete() non-existent key: %s\n", k);
      exit(EXIT_FAILURE);
   }

   if(findKey(D->hashtable[arrayIndex], k) == D->hashtable[arrayIndex]){
      N = D->hashtable[arrayIndex];
      D->hashtable[arrayIndex] = D->hashtable[arrayIndex]->next;
      N->next = NULL;
   }
   else{
      N = findKey(D->hashtable[arrayIndex], k);
      Node prev = D->hashtable[arrayIndex];
      Node temp = D->hashtable[arrayIndex]->next;
      while(temp != N){
         temp = temp->next;
         prev = prev->next;
      }
      prev->next = N->next;
      N->next = NULL;
   }
   D->numItems--;
   freeNode(&N);
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none             
void makeEmpty(Dictionary D){
   if(D == NULL){
      fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
    
   if(D->numItems == 0){
      fprintf(stderr, "Dictionary Error: calling makeEmpty() on an empty Dictionary\n");
      exit(EXIT_FAILURE);
   }
   for(int i = 0; i < tableSize; i++){
      while(D->hashtable[i] != NULL){
         Node N = D->hashtable[i];
         D->hashtable[i] = D->hashtable[i]->next;
         freeNode(&N);
         D->numItems--;
      }
   }
}

// freeArray()
// function to free array hashtable
void freeArray(Dictionary D){
   free(D->hashtable);
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed by out
void printDictionary(FILE* out, Dictionary D){
   if(D == NULL){
      fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   Node P;
   for(int i = 0; i<tableSize; i++){
      P = D->hashtable[i];
      while(P!=NULL){
         fprintf(out, "%s %s\n", P->key, P->value);
         P = P->next;
      }
   }
}
