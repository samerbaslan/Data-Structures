// Samer Baslan
// sbaslan
// CMPS 12M
// 08-06-2017
// Dictionary.c


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

//create dictionary struct
typedef struct DictionaryObj{
	Node head;
	Node tail;
	int numItems;
} DictionaryObj;


	

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v){
	Node N = malloc(sizeof(NodeObj));
	assert(N != NULL);
	N-> key = k;
	N-> value = v;
	N-> next = NULL;
	return (N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// public functions


// newDictionary
// Constructor of the Dictionary type
Dictionary newDictionary(void){
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D != NULL);
	D->head = NULL;
	D->tail = NULL;
	D->numItems = 0;
	return D;
}


// freeDictionary()
// destructor for the Stack type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      if( !isEmpty(*pD) ) makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if(D->numItems> 0){
	return 0;
   }else{
	return 1;
   }
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
	return D->numItems;
}

// lookup()
// returns the value v such that (k,v) is in D, or returns NULL if no such value exists
// pre: !isEmpty(S)
char* lookup(Dictionary D, char* k){
   Node N = D->head;
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems==0 ){
      fprintf(stderr, "Dictionary Error: calling lookup() on empty Dictionary\n");
      exit(EXIT_FAILURE);
   }
   while(N != NULL){
	if(strcmp(N->key, k) == 0)
	return N->value;
	N = N->next;
   }
	return NULL;
}


// insert()
// interts new (key, value) pair into D
// pre: lookup(D, k) == NULL
void insert(Dictionary D, char* k, char* v){
   if(D->numItems == 0){
	D-> head = D-> tail = newNode(k, v);
   }else{
	Node N;
	N = newNode(k, v);
	D->tail -> next = N;
	D-> tail = N;
	}
   D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!= NULL
void delete(Dictionary D, char* k){
   Node N = D->head;
   if(lookup(D, k) == NULL){
	fprintf(stderr, "Dictionary error: Key Not Found\n");
	exit(EXIT_FAILURE);
   }
   if(strcmp(N->key, k) == 0){
	Node P = D->head;
	Node S = P->next;
	D->head = S;
	freeNode(&P);
   }
   else{	
        while( N!= NULL && N->next != NULL){
	if(strcmp(N->next->key, k) == 0){
	Node S = N;
	Node C = N->next;
	S->next = C-> next;
	N = S;
	freeNode(&C);
   }
   N = N->next;
}
}
   D->numItems--;
}

// makeEmpty()
// re-sets D to the empty state
// pre: none

void makeEmpty(Dictionary D){
   D->head = NULL;
   D->tail = NULL;
   D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out

void printDictionary(FILE* out, Dictionary D){
   Node N;
   if(D == NULL){
	fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
	exit(EXIT_FAILURE);
   }
   for(N=D->head; N != NULL; N=N->next){
	fprintf(out, "%s %s ", N->key, N->value);
	fprintf(out, "\n");
}
}
