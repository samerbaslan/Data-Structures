//Samer Baslan
//sbaslan
//CMPS 12B
//08-09-2017
//Queue.java
public class Queue implements QueueInterface{
 
   // Inner private class Node
   // constructs the queue
   private class Node{
      Object item;
      Node next;

      // Node constructor
      Node(Object item){
         this.item = item;
         next = null;
      }
   }

   // private fields
   private Node front;
   private Node back;
   private int numItems;
   
   // Queue constructor
   Queue(){
      front = null;
      back = null;
      numItems = 0;
   }
  
   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise 
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // length()
   // pre: none
   // post: returns the length of this Queue.
   public int length(){
      return numItems;
   }
   
   // enqueue()
   // adds newItem to back of this Queue
   // pre: none
   // post: !isEmpty()
   public void enqueue(Object newItem){
      if(front == null){
         front = new Node(newItem);
         numItems++;
      }
      else{
         Node H = front;
         while(H.next != null){
            H = H.next;
         }
         H.next = new Node(newItem);
         back = H.next;
         numItems++;
      } 
   }
  
   // dequeue()
   // deletes and returns item from front of this Queue // pre: !isEmpty()
   // post: this Queue will have one fewer element 
   public Object dequeue() throws QueueEmptyException{
      if(isEmpty()){
         throw new QueueEmptyException("Usage: using dequeue() on empty queue");
      }
      else{
         Node H = front;
         front = H.next;
         numItems--;
         return H.item;
      }
   }
   
   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public Object peek() throws QueueEmptyException{
      if(isEmpty()){
         throw new QueueEmptyException("Usage: using peek() on empty queue");
      }
      else{
         return front.item;
      }
   }
                               
   // dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException{
      Node H = front;
      if(isEmpty()){
         throw new QueueEmptyException("Usage: using dequeueAll() on empty queue");
      }
      else{ 
         front = null;
         back = null;
         numItems = 0;
      }
   }
   
   // toString()
   // overrides Object's toString() method
   public String toString(){
      String s = "";
      Node H = front;
      while(H != null){
         s += H.item+" ";
         H = H.next;
      }
      return s;
   }
}
