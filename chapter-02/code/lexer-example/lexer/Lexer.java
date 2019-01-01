package lexer;

import java.io.*;
import java.util.*;

public class Lexer {
    public int line = 1;
    
    private char peek = ' ';
    private HashMap<String, Word> words = new HashMap<>();

    void reserve(Word t) {
        words.put(t.lexeme, t);
    }

    public Lexer() {
        reserve(new Word(Tag.TRUE, "true"));
        reserve(new Word(Tag.FALSE, "false"));
    }

    public Token scan() throws IOException {
        for(;; peek = (char)System.in.read()) {
            if(peek == ' ' || peek == '\t') continue;
            else if(peek == '\n') line++;
            else break;
        }

        if(Character.isDigit(peek)) {
            int v = 0;
            do {
                v = (v * 10) + Character.digit(peek, 10);
                peek = (char)System.in.read();
            } while(Character.isDigit(peek));

            return new Num(v);
        }
        if(Character.isLetter(peek)) {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append(peek);
                peek = (char)System.in.read();
            } while(Character.isLetterOrDigit(peek));

            String s = sb.toString();
            Word w = words.get(s);

            if(w != null) {
                return w;
            }

            w = new Word(Tag.ID, s);
            words.put(s, w);

            return w;
        }

        Token t = new Token(peek);
        peek = ' ';
        return t;
    }
}
