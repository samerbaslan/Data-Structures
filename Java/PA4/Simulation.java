//Samer Baslan
//sbaslan
//CMPS 12B
//PA4
//08-09-2017
//Simulation.java

import java.io.*;
import java.util.Scanner;

public class Simulation{
   public static void main(String[] args) throws IOException{
      // declare and initialize variables
      Scanner in = null;
      PrintWriter trace = null;
      PrintWriter report = null;
      Queue Q = new Queue();
      int m, n, time;
      int small = 0;

      // check command line arguments
      if(args.length < 1){
         System.err.println("Usage: Simulation inputfile");
         System.exit(1);
      }
     
      // open files for reading and writing
      in = new Scanner(new File(args[0]));
      trace = new PrintWriter(new FileWriter(args[0]+".trc"));
      report = new PrintWriter(new FileWriter(args[0]+".rpt"));
      
      // read in m jobs from input and print out trace
      m = numJobs(in);
      while(in.hasNext()){
         Q.enqueue((Job)getJob(in));
      }
      trace.println("Trace file: "+args[0]+".trc");
      trace.println(m+" Jobs:");
      trace.println(Q);
      
      // run simulation with n processors for n=1 to n=m-1
      for(n = 1; n < m; n++){
         trace.println();
         int count = m;
         int numProcess = n;
         time = 0;
         //declare and initialize an array of n processor Queues and any necessary storage Queues
         Queue[] processor = new Queue[n+1];
         for(int i = 1; i < n+1; i++){
            processor[i] = new Queue();
         } 
         processor[0] = Q; 
         
         trace.println("*****************************"); 
         if(numProcess == 1){
            trace.println("1 processor: ");
         }
         else{
            trace.println(numProcess+ " processors: ");
         }
         trace.println("*****************************");
         trace.println("time="+time);
         trace.println("0: "+Q);
         for(int i = 1; i < numProcess+1; i++){
            trace.println(i+": "+processor[i]);
         }

		 // while unprocessed jobs remain
         while(count > 0){ 
            int index = 1;
            Job t1 = null;
            Job t2 = null;
            int t3 = -1;
            int t4 = -1;
            int length = -1;
            int finalLength = -1;
            int comp = -1;
            Job h = null;
            // determine the time of the next arrival or finish event and update time
            
            if((!processor[0].isEmpty())){
               if(((Job)processor[0].peek()).getFinish() == -1){
                  t1 = (Job)processor[0].peek();
                  t4 = t1.getArrival();
               }
            }
            
            
            for(int i = 1; i < numProcess+1; i++){
               if(processor[i].length() != 0){  
                  t2 = (Job)processor[i].peek();
                  t3 = t2.getFinish();
                  if(t3 != -1){
                     if(t4 == -1){
                        t4 = t3;
                        index = i;
                        
                     }
                     else if(t3 < t4){
                        t4 = t3;
                     }
                  }
               }
               time = t4;
            }
           
          
            //enque arrival
            int temparriv = 0;
            if(processor[0].length() != 0){
               for(int z = 0; z < processor[0].length(); z++){
                  if(((Job)processor[0].peek()).getArrival() == time){
                     small = findLine(processor);
                     processor[small].enqueue(processor[0].dequeue());
                  }
               }
                   
            } 
                          
            // return finished jobs to queue
            for(int i = 1; i < numProcess+1; i++){
               if(processor[i].length() != 0){
                  if(((Job)processor[i].peek()).getFinish() == time){
                     processor[0].enqueue(processor[i].dequeue());
                     count--;
                  }
               }
            }
            // Find all finish times
            for(int i = 1; i < numProcess+1; i++){
               if(processor[i].length() != 0){
                  if(((Job)processor[i].peek()).getFinish() == -1){
                     ((Job)processor[i].peek()).computeFinishTime(time);
                  }
               }
            }
           
            trace.println();
            trace.println("time=" +time);
            trace.println("0: " +processor[0]);
            for(int x = 1; x < numProcess + 1; x++){
               trace.println(x + ": " +processor[x]);
            }
                   
         }
         if(n==1){
            report.println("Report file: "+args[0]+ ".rpt");
            report.println(m + " Jobs:");
            report.println(processor[0] +"\n");
            report.println("***********************************************************");
         }
         
         float averageWait = 0;
         int totalWait = 0;
         int maxWait = 0;
         int max = 0;
         int j = processor[0].length();
         Job[] reset = new Job[j];
         
         // sort the array
         for(int i = 0; i < j; i++){
            Job r = (Job)processor[0].dequeue();
            reset[i] = r;
            max = reset[i].getWaitTime();
            if(maxWait < max){
               maxWait = max;
            }
            totalWait += reset[i].getWaitTime();
         }
         Job tempNum = null;
         boolean swapped = true;
         while(swapped){
            swapped = false;
            for(int x = 0; x < reset.length - 1; x++){
               if(reset[x].getArrival() > reset[x+1].getArrival()){
                  tempNum = reset[x];
                  reset[x] = reset[x+1];
                  reset[x+1] = tempNum;
                  swapped = true;
               }
            }
         }
         Queue holdQueue = new Queue();
  
         averageWait = (float)totalWait/m;
         report.println( n + " processor: totalWait="+ totalWait+ " maxWait="+maxWait+" averageWait="+averageWait);

         for(int i = 0; i<reset.length; i++){
            reset[i].resetFinishTime();
            Q.enqueue(reset[i]);
         } 
      }
      in.close();
      report.close();
      trace.close();   
   }
  
   // getJob()
   public static Job getJob(Scanner in){
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }
   // findLine(
   // find processor with shortest length
   public static int findLine(Queue[] a){
      int temp = 1;
      for(int i = 1; i < a.length; i++){
         if(a[i].length() < a[temp].length()){
            temp = i;
         }
         if(a[i].length() == a[temp].length()){
            temp = temp;
         }
       }
       return temp;
   }
   
   public static int numJobs(Scanner in){
      int num;
      String s = in.nextLine();
      num = Integer.parseInt(s);
      return num;
   }
}     
