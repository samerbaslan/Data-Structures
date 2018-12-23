/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public class FileReverse {
    
    public static String stringReverse(String s, int n){
        char c[] = s.toCharArray();
        if ((s == null) || (s.length() <= 1)){ 
         return s;
        }
    return stringReverse(s.substring(1), c.length - 1) + s.charAt(0);
    }
    
    public static void main(String[] args) throws IOException {
        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        String [] token = null;
        int i, n, lineNumber = 0;
        // check number of command line arguments is at least 2
        if(args.length < 2){
               System.out.println("Usage: FileCopy <input file> <output file>");
               System.exit(1);
        }
        // open files
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));
        
        while(in.hasNextLine()){
            lineNumber++;
            line = in.nextLine().trim() + " ";
            token = line.split("\\s+");
            n = token.length;
            for( String s: token){
                s = stringReverse(s, n);
                out.println(s);
            }
            
        }
        in.close();
        out.close();
    }
    
}
