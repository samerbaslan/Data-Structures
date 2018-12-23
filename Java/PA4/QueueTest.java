// Samer Baslan
// sbaslan
// CMPS 12B
// 08-09-2017
// QueueTest.java

class QueueTest{
   public static void main(String[] args){
      Queue A = new Queue();
	  System.out.println("This is the test client for the Queue ADT");
      System.out.println(A.isEmpty()); // prints out true
      System.out.println(A.length());  // prints 0
	  //A.enqueue(4);
	  //A.enqueue(5);
	  //A.enqueue(6);
	  //System.out.println(A);
      A.enqueue((int)3);             
      System.out.println(A);           // prints 3
      A.enqueue((int)5);
      A.enqueue((int)1);
      System.out.println(A.isEmpty()); // prints false
      A.enqueue((int)8);
      System.out.println(A);           // prints 5 5 1 8
      A.dequeue();
      System.out.println(A);           // prints 1 8
      System.out.println(A.length());  // prints 2
      System.out.println(A.peek());    // prints 3     
      A.dequeueAll();
      System.out.println(A.isEmpty()); // prints true;
   // System.out.println(A.peek());    throws exception
   
      
    }

}