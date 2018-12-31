/**
 * Exercise 2.4.1c
 * 
 * Author: Roger Ngo
 * 
 * Parser for S -> 0 S 1 | 0 1
 * 
 * This one is also left recursive so we must rewrite it to eliminate potential 
 * infinite recursion.
 * 
 * S -> 0 R
 * R -> S 1 | 1
 */

 import java.util.*;

 public class Main {
     public static String input;
     public static int index = -1;
     public static char lookahead;

     public static void s() throws Exception {
        match('0');
        r();
     }

     public static void r() throws Exception {
        if(lookahead == '1') {
            match('1');
            return;
        }
        
        s();
        match('1');
     }

     public static void match(char terminal) throws Exception {
        if(lookahead != terminal) {
            throw new Exception("Syntax error.");
        }
        lookahead = nextTerminal();
     }

     public static char nextTerminal() {
         return input.charAt(++index);
     }
     public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("This parser matches the grammar: S -> 0 S 1  | 0 1");
        System.out.print("Enter input: ");

        input = scan.nextLine();
        input += '\0';

        lookahead = nextTerminal();

        try {
            s();
            System.out.println("DONE.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
     }
 }
