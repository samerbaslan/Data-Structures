//Samer Baslan
//sbaslan
//CMPS 12B
//08-18-2017
//PA5
//Dictionary Test Client 
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
  
  //char* S[] = {"Dog", "Cat", "Monkey", "Elephant");
  //char* B[] = {"one", "two", "three", "four");
  //insert(A, S[1], B[1];
  
   char* X[] = {"one", "two", "three", "four", "five", "six", "seven"};
   char* Y[] = {"a", "b", "c", "d", "e", "f", "g"};
   int i;
   for(i = 0; i < 7; i++){
      insert(A, X[i], Y[i]);
   }
      
   printDictionary(stdout, A);
	
  //makeEmpty(A);
   
   delete(A, "two");
   delete(A,"three");
   delete(A, "four");
   delete(A, "five");
   printDictionary(stdout, A);
  
   printf("%s\n", (isEmpty(A)?"true":"false")); //false
   makeEmpty(A);
   printf("%s\n", (isEmpty(A)?"true":"false")); //true

 //delete(A, "two"); // error
   
}
