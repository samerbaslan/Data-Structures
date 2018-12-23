//Samer Baslan
//sbaslan
//CMPS 12M
//08-17-2017
//BST implementation of Dictionary.java

public class Dictionary implements DictionaryInterface{

   // inner private class Node
   
   private class Node{
      String key;
      String value;
      Node left;
      Node right;
   
      // Node constructor
      Node(String key, String value){
         this.key = key;
         this.value = value;
         left = null;
         right = null;
      }
   }

   // private fields for dictionary class
   private Node root;
   private int numPairs;

   // findKey()
   private Node findKey(Node R, String k){
      if(R == null || k.compareTo(R.key) == 0){
         return R;
      }
      if(k.compareTo(R.key) < 0){
         return findKey(R.left, k);
      }
      else{
         return findKey(R.right, k);
      }
   }

   // findParent()
   private Node findParent(Node N, Node R){
      Node P = null;
      if(N != R){
         P = R;
         while(P.left != N && P.right != N){
            if(N.key.compareTo(P.key) < 0){
               P = P.left;
            }
            else{
               P = P.right;
            }
         }
      }
      return P;
   }

   // findLeftmost()
   private Node findLeftmost(Node R){
      Node L = R;
      if(L != null) for( ; L.left != null; L = L.left) ;
      return L;
   }

   void printInOrder(Node R){
      if(R!=null){
         printInOrder(R.left);
         System.out.println(R.key + " " +R.value);
         printInOrder(R.right);
      }
   }
   
   // deleteAll()
   void deleteAll(Node N){
      if(N != null){
         deleteAll(N.left);
         deleteAll(N.right);
       }
   }

   // public functions ----------------------------------------------------------
   // Dictionary Constructor
   public Dictionary(){
      root = null;
      numPairs = 0;
   }

   // isEmpty()
   // pre: none
   // returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
      if(numPairs == 0){
         return true;
      }
      else{
         return false;
      }
   }
            
   // size()
   // pre: none
   // returns the number of entries in this Dictionary
   public int size(){
      return numPairs;
   }

   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key){
      Node N;
      N = findKey(root, key);
      return(N == null ? null : N.value);
   }

   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException{
      if(lookup(key) != null){
         throw new DuplicateKeyException("Dictionary Error: cannot insert() duplicate key: "+key);
      }
      else{
         Node N = new Node(key, value);
         Node B = null;
         Node A = root;
         while(A != null){
            B = A;
            if(key.compareTo(A.key) < 0){
               A = A.left;
            }
            else{
               A = A.right;
            }
         }
         if(B == null){
            root = N;
         }
         else if(key.compareTo(B.key) < 0){
            B.left = N;
         }
         else{
            B.right = N;
         }
         numPairs++; 
      }
   }
         

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException{
      Node N = findKey(root, key);
      if(lookup(key) == null){
         throw new KeyNotFoundException("Dictionary Error: cannot delete() non-existent key: "+key);
      }
      else{
         if(N.left == null && N.right == null){ // case 1
            if(N == root){
               root = null;
            }
            else{
               Node P = findParent(N, root);
               if(P.right == N){
                  P.right = null;
               }
               else{
                  P.left = null;
               }
            }
         }
         else if(N.right == null){ //case 2 left child only
            if(N == root){
               root = N.left;
            }
            else{
               Node P = findParent(N, root);
               if(P.right == N){
                  P.right = N.left;
               }
               else{
                  P.left = N.left;
               }
            }
         }
         else if(N.left == null){ //case 2 right child only
            if(N == root){
               root = N.right;
            }
            else{
               Node P = findParent(N, root);
               if(P.right == N){
                  P.right = N.right;
               }
               else{
                  P.left = N.right;
               }
            }
         }
         else{                    //case 3: 2 children
            Node S = findLeftmost(N.right);
            N.key = S.key;
            N.value = S.value;
            Node P = findParent(S, N);
            if(P.right == S){
               P.right = S.right;
            }
            else{
               P.left = S.right;
            }
         }
         numPairs--;
      }
   }
 
   // makeEmpty()
   // pre: none
   public void makeEmpty(){
      deleteAll(root);
      root = null;
      numPairs = 0;
   }
      
   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
   public String toString(){
      String x = "";
      printInOrder(root);
      return x;
   }
}  
