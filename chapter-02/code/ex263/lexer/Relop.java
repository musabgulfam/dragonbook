package lexer;

public class Relop extends Token {
    public final String lexeme;
    public Relop(String lexeme) {
        super(Tag.RELOP);
        this.lexeme = lexeme;
    }
}
