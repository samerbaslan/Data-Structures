/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samer
 */

class Recursion {
   
   // reverseArray1()
   // Places the leftmost n elements of X[] into the rightmost n positions in
   // Y[] in reverse order
   static void reverseArray1(int[] X, int n, int[] Y){
       if (n == 0){
           return;
       }
       Y[n-1] = X[X.length - n]; //left in X[] into the nth position from the right in Y[]
       reverseArray1(X, --n, Y);
   }

   // reverseArray2()
   // Places the rightmost n elements of X[] into the leftmost n positions in
   // Y[] in reverse order.
   static void reverseArray2(int[] X, int n, int[] Y){
     if (n == 0){
         return;
     }
     Y[X.length - n] = X[n-1]; //right in [X] into nth position from the left in Y[]
     reverseArray2(X, --n, Y);
   }
   
   // reverseArray3()
   // Reverses the subarray X[i...j].
   static void reverseArray3(int[] X, int i, int j){
     if (i >= j){
         return;
     }
     int temp = X[i];
     X[i] = X[j];
     X[j] = temp;
     reverseArray3(X, i+1, j-1);
   }
  
   // maxArrayIndex()
   // returns the index of the largest value in int array X
   static int maxArrayIndex(int[] X, int p, int r){
     if( r-p <= -1){ //if there is a single element
         return r;
     }
     int q = (p + r) / 2; //calculating middle element
     int left = maxArrayIndex(X, p, q); //computing index of max element in left subarray
     int right = maxArrayIndex(X, q+1, r); //computing index of max element in right subarray
     if (X[left] > X[right]){
         return left;
     }
     else{
         return right;
     }
   }
   
   // minArrayIndex()
   // returns the index of the smallest value in int array X
   static int minArrayIndex(int[] X, int p, int r){
       if( r-p <= 0){
           return r;
       }
       int q = (p+r) / 2;
       int left = minArrayIndex(X, p, q);
       int right = minArrayIndex(X, q+1, r);
       if (X[left] < X[right]){
           return left;
       }
       else{
           return right;
       }
   }
   
   // main()
   public static void main(String[] args){
      
      int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
      int[] B = new int[A.length];
      int[] C = new int[A.length];
      int minIndex = minArrayIndex(A, 0, A.length-1);
      int maxIndex = maxArrayIndex(A, 0, A.length-1);
      
      for(int x: A) System.out.print(x+" ");
      System.out.println(); 
      
      System.out.println( "minIndex = " + minIndex );  
      System.out.println( "maxIndex = " + maxIndex );  

      reverseArray1(A, A.length, B);
      for(int x: B) System.out.print(x+" ");
      System.out.println();
      
      reverseArray2(A, A.length, C);
      for(int x: C) System.out.print(x+" ");
      System.out.println();
      
      reverseArray3(A, 0, 3);
      for(int x: A) System.out.print(x+" ");
      System.out.println();  
      
   }
   
}
