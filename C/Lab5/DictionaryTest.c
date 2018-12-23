//Samer Baslan
//sbaslan
//CMPS 12M
//08-06-2017
//DictionaryTest.c
//Test client for Dictionary ADT

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
	Dictionary A = newDictionary();
	char* k;
	char* v;
	char* word1[] = {"t", "e", "s", "t", "1"};
	char* word2[] = {"g", "o", "o", "d", "2"};
	int i;

	for( i = 0; i < 4; i++){
	insert(A, word1[i], word2[i]);
	}
	
//	printDictionary(stdout, A);

	for( i = 0; i < 5; i++){
	k = word1[i];
	v = lookup(A, k);
	printf("key=\"%s\"%s\"%s\"\n", k, (v==NULL?"not found":"value="), v);
	}

	//delete(A, "2");
	//delete(A, "4");

	//printDictionary(stdout, A);
	
	//insert(A, "key2", "val2");
	//printDictionary(stdout, A);

	printf("%s\n", (isEmpty(A)?"true":"false"));
	printf("%d\n", size(A));
	makeEmpty(A);
	printf("%s\n", (isEmpty(A)?"true":"false"));

	freeDictionary(&A);
	return(EXIT_SUCCESS);
}
