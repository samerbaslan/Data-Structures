/* Samer Baslan
 * sbaslan
 * CMPS 12B
 * 07/23/2017
 * PA3
 */

public class Dictionary implements DictionaryInterface{
  
//private inner Node class
  private class Node{
    String key;
    String value;
    Node next;
    
    //node constructor
    Node(String key, String value){
      this.key = key;
      this.value = value;
      next = null;
    }
  }
  
  //private fields for the Dictionary class   
  private Node head;
  private int numItems;
  
  //dictionary constructor initialize head and numItems
  public Dictionary(){
    head = null;
    numItems = 0;
  }
  
  //isEmpty
  //pre: none 
  //pos: returns true if this Dictionary is empty, false otherwise
  public boolean isEmpty(){
    return (numItems==0);
  }
  
  //size
  //pre: none
  //pos: returns the number entries in this Dictionary
  public int size(){
    return numItems;
  }
  
  //lookup
  //pre: none
  //Pos: returns value associated key, or null reference if no such key exists
  public String lookup(String key){
    Node N = head;
    while( N != null){
      if( N.key.equals(key)){
        return N.value;
      }
      N = N.next;
    }
    return null;
  }
  
  //insert
  //pre: lookup(key) == null 
  //pos: inserts new (key, value) pair into this Dictionary
  public void insert(String key, String value) throws DuplicateKeyException{
    if( lookup(key) != null){
      throw new DuplicateKeyException("cannot insert duplicate keys");
    }
    else{
      if( head == null){
        Node N = new Node(key,value);
        head = N;
        numItems++;
      }
      else{
        Node N = head;
        while( N != null){
          if(N.next == null){
            break;
          }
          N = N.next;
        }
        N.next = new Node(key,value);
        numItems++;
      }
    }
  }

   //delete()
   //pre: lookup(key)!=null
   //pos: deletes pair with the given key
  public void delete(String key) throws KeyNotFoundException{
    if( lookup(key) == null){
      throw new KeyNotFoundException("cannot delete non-existent key");
    }
    else{
      if(numItems <= 1){
        Node N = head;
        head = head.next;
        N.next = null;
        numItems--;
      }
      else{
        Node N = head;
        if(N.key.equals(key)){
          head = N.next;
          numItems--;
        }
        else{
          while(!N.next.key.equals(key)){
            N = N.next;
          }
          N.next = N.next.next;
          numItems--;
        }
      }
    }
  }
  
  //makeEmpty
  //pre: none
  //pos: clears linked list
  public void makeEmpty(){
    head = null;
    numItems =0;
  }

  //toString
  //pre: none
  //pos: returns a String representation of this Dictionary
  public String toString(){
    String s = "";
    Node N = head;
    while( N != null){
      s += N.key + " " + N.value + "\n"; 
      N = N.next;
    }
    return s;
  }
  
  //findKey
  //pre: takes in a String key
  //pos: return reference to the Node containing its argument key,
  //or return null if no such Node exists
  private  Node findKey(String key){
    Node N = head;
    while(N != null){
      if(N.key.equals(key)){
        return N; 
      }
      else{
        N = N.next; 
      }
    }
    return null;
  }
  
}
