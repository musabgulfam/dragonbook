/**
 * Exercise 2.4.1a
 * 
 * Author: Roger Ngo
 * 
 * Matches the grammar: S -> +SS | -SS | a
 */

import java.util.*;

public class Main {
    public static String input;

    public static int index = 0;
    public static char previous;
    public static char lookahead;
    public static boolean done = false;

    public static void s() throws Exception {
        if(lookahead == '+' || lookahead == '-') {
            match(lookahead);
            s();
            s();
        } else if(lookahead == 'a') {
            if(index + 1 < input.length()) {
                match(lookahead);
            } else if (previous == 'a' && lookahead == 'a') {
                done = true;
            } else {
                throw new Exception("Syntax error.");
            }
        } else {
            throw new Exception("Syntax error.");
        }
    }
    
    public static void match(char terminal) {
        previous = lookahead;
        lookahead = input.charAt(++index);
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Grammar: S -> +SS | -SS | a ");
        System.out.print("Enter input to match grammar: ");
        input = scan.nextLine();

        lookahead = input.charAt(index);

        try {
            while(!done) {
                s();
            }

            System.out.println("Matched.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
}
