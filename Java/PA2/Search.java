/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author samer
 */

public class Search {
    
  //mergeSort
  static void mergeSort(String[] word, int[] lineNumber, int p, int r){
    int q;
    if(p<r){
      q = (p+r)/2;
      mergeSort(word, lineNumber, p, q);
      mergeSort(word, lineNumber, q+1, r); 
      merge(word, lineNumber, p, q, r);
    }
  }

  //merge
  static void merge(String[] word, int[] lineNumber, int p, int q, int r){
    int n1 = q-p+1;
    int n2 = r-q;
    String[] left = new String[n1];
    String[] right = new String[n2];
    int[] leftNumber = new int[n1];
    int[] rightNumber = new int[n2];
    int i, j, k;
    
    for(i=0; i<n1; i++){
      left[i] = word[p+i];
      leftNumber[i] = lineNumber[p+i];
    }
    
    for(j=0; j<n2; j++){
      right[j] = word[q+j+1];
      rightNumber[j] = lineNumber[q+j+1];
    }
    
    i = 0;
    j = 0;
    
    for(k=p; k<=r; k++){
      if( i<n1 && j<n2){
        if( left[i].compareTo(right[j])>0 ){
          word[k] = left[i];
          lineNumber[k] = leftNumber[i]; 
          i++;
        }
        else{
          word[k] = right[j];
          lineNumber[k] = rightNumber[j];
          j++;
        } 
      }
      else if( i<n1){
        word[k] = left[i];
        lineNumber[k] = leftNumber[i];
        i++;
      }
      else{  // j<n2
        word[k] = right[j];
        lineNumber[k] = rightNumber[j];
        j++;
      } 
    }
  }
  
  //binarySearch 
  public static String binarySearch(String[] word, int[] lineNumber, int p, int r, String target){
    int q;
    if( p == r ){
      return target + " not found";
    }
    else{
      q = (p+r)/2;
      if( word[q].compareTo(target) == 0){
        return target + " found on line " + lineNumber[q];
      }
      else if( word[q].compareTo(target)<0 ) {
        return binarySearch(word, lineNumber, p, q, target);
      }
      else{ 
        return binarySearch(word, lineNumber, q+1, r, target);
      }
    }
    
  }
  public static void main(String[] args) throws IOException{
    Scanner in = null;
    String line = null;
    String[] token = null;
    int[] lineNumber = null;
    int numLines = 0;
    
    
  
    if(args.length < 2){
      System.err.println("Usage: Search input target1 [target2 target 3]");
      System.exit(1);
    }
    
    //count number of lines in the file
    in = new Scanner(new File(args[0]));
    while(in.hasNextLine()){
      numLines++;
      line = in.nextLine();
    }
    
    //initialize length of String array and int array
    token = new String[numLines];
    lineNumber = new int[numLines];
    in = new Scanner(new File(args[0]));     //scan again
    
    //add lineNumber to array
    for(int i=1; i<=lineNumber.length; i++){
      lineNumber[i-1] = i;
    }
    //scan file, insert word in string array
    for(int i =0; in.hasNextLine(); i++){
      line = in.nextLine();
      token[i] = line;
    }

    mergeSort(token,lineNumber, 0, token.length-1);
    //print target if found and report the linenumber of target
    for(int i=1; i<args.length; i++){
      System.out.println( binarySearch(token, lineNumber, 0, token.length-1, args[i]));
    }
    
    in.close();
    
  }    
  
  
}
