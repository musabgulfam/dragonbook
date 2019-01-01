package lexer;

public class Token {
    public final int tag;
    public Token(int t) { tag = t; }

    public String toString() {
        switch(tag) {
            case Tag.NUM:
                return "NUM";
            case Tag.ID:
                return "ID";
            case Tag.TRUE:
                return "TRUE";
            case Tag.FALSE:
                return "FALSE";
            case Tag.RELOP:
                return "RELOP";
            default:
                return "UNKNOWN";
        }
    }
}
