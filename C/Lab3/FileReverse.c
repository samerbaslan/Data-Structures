#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* stringReverse*/
/* reverses char array */
void stringReverse(char* s){
	int i;
	int j=strlen(s)-1;
	char temp;
	for(i=0; i<=j; i++){
		temp = s[j];
		s[j] = s[i];
		s[i]=temp;
		j--;
	}
}


int main ( int argc, char* argv[]){
	FILE* in;
	FILE* out;
	char word[256];
	/*check command line for correct number of arguments*/
	if( argc != 3){
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	/*open input file for reading*/
	in = fopen(argv[1], "r");
	if( in==NULL){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}
	/*open output file for writing*/
	out = fopen(argv[2], "w");
	if( out==NULL){
		printf("Unable to write to file %s\n",argv[2]);
		exit(EXIT_FAILURE);
	}
	/*read words from input file, reverse, and print*/ 
	while( fscanf(in, " %s",word) != EOF){
		stringReverse(word);
		fprintf(out,"%s\n",word );
	
	}
	/*closes the input and output file*/
	fclose(in);
	fclose(out);


	return(EXIT_SUCCESS);

}
