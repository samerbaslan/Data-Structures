//Samer Baslan
//sbaslan
//CMPS12M
//charType Lab 4
//07/28/2017

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <assert.h>
#include <string.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w){
	int i = 0;
	int j = 0;
	int k = 0;
	int l = 0;
	int m = 0;

	while( i < MAX_STRING_LENGTH && s[i] != '\0'){
		if( isalpha((int)s[i])){ //check if string is alphabetic
			a[j] = s[i];
			j++;
		}
		else if( isdigit((int)s[i])){ //check if string is numeric
			d[k] = s[i];
			k++;
		}
		else if( ispunct((int)s[i])){ //check if punctuation
			p[l] = s[i];
			l++;
		}
		else if( isspace((int)s[i])){ //check if whitespace
			w[m] = s[i];
			m++;
		}
		i++;
	}
	
	//null character termination, making them into valid C strings
	a[j] = '\0';
	d[k] = '\0';
	p[l] = '\0';
	w[m] = '\0';
}

void extract_chars(char* s, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]){
	FILE* in;
	FILE* out;
	char* line; //string holding input line
	char* alphabet; //string holding alphabet chars
	char* numeric; //string holding numeric chars
	char* punct; //string holding punctuation chars
	char* wspace; //string holding whitespace chars
	int numberLine;
	
	//check command line for correct number of arguments
	if(argc != 3){
		printf("Usage: %s input-file output-file \n", argv[0]);
		exit(EXIT_FAILURE);
	}
	
	//open input file for reading
	if( (in = fopen(argv[1], "r"))==NULL ){
		printf("Unable to read from file %s \n", argv[1]);
		exit(EXIT_FAILURE);
	}
	
	//open output file for writing
	if( (out=fopen(argv[2], "w"))==NULL ){
		printf("Unable to write to file %s \n", argv[2]);
		exit(EXIT_FAILURE);
	}
	
	//allocate strings on the heap 
	line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	alphabet = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	numeric = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	punct = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	wspace = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	assert(line != NULL && alphabet != NULL && numeric != NULL && punct != NULL && wspace != NULL);
	
	//read each line in input file, extract alphabetic, numeric, punctiation, whitespace characters
	while( fgets( line, MAX_STRING_LENGTH, in) != NULL){
		extract_chars(line, alphabet, numeric, punct, wspace);
		fprintf(out, "line %d contains: \n", numberLine+1);
		if(strlen(alphabet)>1){
			fprintf(out, "%d alphabetic characters: %s \n", (int)strlen(alphabet), alphabet);
		}
		else{
			fprintf(out, "%d alphabetic character: %s \n", (int)strlen(alphabet), alphabet);
		}
		if(strlen(numeric)>1){
			fprintf(out, "%d numeric characters: %s \n", (int)strlen(numeric), numeric);
		}
		else{
			fprintf(out, "%d numeric character: %s \n", (int)strlen(numeric), numeric);
		}
		if(strlen(punct)>1){
			fprintf(out, "%d punctuation characters: %s \n", (int)strlen(punct), punct);
		}
		else{
			fprintf(out, "%d punctuaton character: %s \n", (int)strlen(punct), punct);
		}
		if(strlen(wspace)>1){
			fprintf(out, "%d whitespace characters: %s \n", (int)strlen(wspace), wspace);
		}
		else{
			fprintf(out, "%d whitespace character: %s \n", (int)strlen(wspace), wspace);
		}
		numberLine++;
	}
	
	//free heap memory
	free(line);
	free(alphabet);
	free(numeric);
	free(punct);
	free(wspace);
	
	//close files
	fclose(in);
	fclose(out);
	
	return EXIT_SUCCESS;
}
