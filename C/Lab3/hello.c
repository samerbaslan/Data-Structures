#include <stdio.h>
#include <stdlib.h>

#define HELLO_STRING "Hello World!\n"

extern char** environ;

int main( void ){
   int i;

   printf(HELLO_STRING);
   for( i = 0; environ[i] != NULL; i++ ){
      printf("environ[%d]=(%s)\n", i, environ[i]);
   }
   return EXIT_SUCCESS;
}