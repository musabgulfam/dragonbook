package lexer;

import java.io.*;
import java.util.*;

public class Lexer {
    public int line = 1;
    
    private char peek = ' ';

    // Lookahead character for 2.6.1 (b).
    private char lookahead = ' ';

    private HashMap<String, Word> words = new HashMap<>();

    void reserve(Word t) {
        words.put(t.lexeme, t);
    }

    public Lexer() {
        reserve(new Word(Tag.TRUE, "true"));
        reserve(new Word(Tag.FALSE, "false"));
    }

    private void ignoreLineComment() throws IOException {
        // read until '\n' is encountered.
        do {
            peek = (char)System.in.read();
        } while(peek != '\n');
    }

    private void ignoreBlockComment() throws IOException {
        // return until */ is encountered.
        do {
            peek = lookahead;
            lookahead = (char)System.in.read();
        } while(peek != '*' && lookahead != '/');
    }

    private Token scanRelop() throws IOException {
        if(peek == '<') {
            peek = (char)System.in.read();
            if(peek == '=') {
                return new Relop("<=");
            } else {
                return new Relop("<");
            }
        }  
        if(peek == '>') {
            peek = (char)System.in.read();

            if(peek == '=') {
                return new Relop(">=");
            } else {
                return new Relop(">");
            }
        }
        if(peek == '!') {
            peek = (char)System.in.read();

            if(peek == '=') {
                return new Relop("!=");
            } else {
                throw new IOException("Syntax Error. Expected: =");
            }
        }
        if(peek == '=') {
            peek = (char)System.in.read();

            if(peek == '=') {
                return new Relop("==");
            } else {
                throw new IOException("Syntax Error. Expected: =");
            }
        }

        return null;
    }

    public Token scan() throws IOException {
        for(;; peek = (char)System.in.read()) {
            if(peek == ' ' || peek == '\t') continue;
            else if(peek == '/') {
                peek = (char)System.in.read();
                if(peek == '/') {
                    // Exercise 2.6.1 (a) - Handle // 
                    this.ignoreLineComment();
                } else if(peek == '*') { 
                    // Exercise 2.6.2 (b) - Handle /* */
                    this.ignoreBlockComment();
                } 
                else {
                    throw new IOException("Unrecognized character. Expected start of comment.");
                }
            } else if(peek == '\n') line++;
            else break;
        }

        // Exercise 2.6.2 - Handle relational operators
        Token relopToken = this.scanRelop();
        if(relopToken != null) {
            return relopToken;
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
