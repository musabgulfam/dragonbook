/**
 * Exercise 2.4.1b
 * 
 * Author: Roger Ngo
 * 
 * Matches the grammar: S -> S ( S ) S
 * 
 * This one is left recursive, so we will need to convert to the form:
 * A -> Aα | β
 * 
 * This can also be written as:
 * A -> βR
 * R -> αR
 * 
 * If β is ϵ, and A is S, then we can rewrite:
 * 
 * S -> R
 * R -> αR | ϵ
 * 
 * S -> R
 * R -> ( S ) S | ϵ
 */

 import java.util.*;

 public class Main {
     public static String input;
     public static int index = 0;
     public static char lookahead; 

     public static void s() throws Exception {
        r();
     }

     public static void r() throws Exception {
        if(lookahead != '(') {
            return; // epsilon
        }

        match('(');
        s();

        if(lookahead != ')') {
            throw new Exception("Syntax Error");
        }

        match(')');
        s();
     }

     public static void match(char terminal) throws Exception {
        if(lookahead != terminal) {
            throw new Exception("Syntax error.");
        }

        if(lookahead != ')' && index == input.length() - 1) {
            throw new Exception("Syntax error.");
        }

        lookahead = nextTerminal();
     }

     public static char nextTerminal() {
         return input.charAt(++index);
     }

     public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("The grammar: "); 
        System.out.println("\tS -> R ");
        System.out.println("\tR -> ( S ) S | ϵ");

        System.out.print("Input to parse: ");
        
        input = scan.nextLine();
        input += '\0';

        lookahead = input.charAt(index);

        try {
            s();
            System.out.println("DONE.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
     }
 }
