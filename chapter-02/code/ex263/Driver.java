import lexer.*;

public class Driver {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();

        System.out.println("Enter input: ");
        try {
            Token t = lexer.scan();
            System.out.println("Token tag: " + t);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
