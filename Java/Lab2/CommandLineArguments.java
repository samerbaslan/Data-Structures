/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samer
 */
// CommandLineArguments.java
class CommandLineArguments{
 public static void main(String[] args){
 int n = args.length;
 System.out.println("args.length = " + n);
 for(int i=0; i<n; i++) System.out.println(args[i]);
 }
}
