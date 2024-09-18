import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    String text;
    int pos;
    String currentChar;
    Token currentToken;
    String PLUS = "PLUS";
    String MINUS = "MINUS";
    String INTEGER = "INTEGER";
    String EOF = "EOF";
    String MUL = "MUL";
    String DIV = "DIV";
    List<Token> tokens = new ArrayList<>();

    public Interpreter(String text) {
        this.text = text;
        this.pos = 0;
        this.currentChar = this.text.charAt(this.pos) + "";
        this.currentToken = null;
    }

    public void error() {
        throw new RuntimeException("Error parsing input");
    }

    public int expr() {
        for(int i= 0; i < text.length(); i++){
            this.getnextToken();
        }
        Token left = tokens.get(0);
        String op = "";
        int result = left.value;
        for (int i = 1; i < tokens.size(); i++) {
            if (tokens.get(i).type.equals(PLUS) || tokens.get(i).type.equals(MINUS) || tokens.get(i).type.equals(MUL) || tokens.get(i).type.equals(DIV)) {
                op = tokens.get(i).type;
            }
            if (tokens.get(i).type.equals(INTEGER)) {
                Token right = tokens.get(i);
                if (op.equals(PLUS)) {
                    result =  result + right.value;
                } else if (op.equals(MINUS)) {
                    result =  result - right.value;
                } else if (op.equals(MUL)) {
                    result =  result * right.value;
                } else if (op.equals(DIV)) {
                    result =  result / right.value;
                }
            }
        }
        return result;
    }

    private boolean isDigit(String c) {
        return c.matches("[0-9]");
    }

    private int integer() {
        StringBuilder result = new StringBuilder();
        try {
            while (isDigit(this.currentChar)) {
                result.append(this.currentChar);
                this.advance();
            }
        }catch (NullPointerException e){
                this.currentChar = null;
        }
        return Integer.parseInt(result.toString());
    }

    private Token getnextToken() {

        while (this.currentChar != null) {

            if (this.currentChar.equals(" ")) {
                this.advance();
                continue;
            }

            if (isDigit(this.currentChar)) {
                Token token = new Token(INTEGER, integer());
//                this.advance();
                this.tokens.add(token);
                return token;
            }
            switch (currentChar) {
                case "+" -> {
                    this.advance();
                    Token token = new Token(PLUS, "+");
                    tokens.add(token);
                    return new Token(PLUS, "+");
                }
                case "-" -> {
                    Token token = new Token(MINUS, "-");
                    this.tokens.add(token);
                    this.advance();
                    return new Token(MINUS, "-");
                }
                case "*" -> {
                    Token token = new Token(MUL, "*");
                    this.tokens.add(token);
                    this.advance();
                    return new Token(MUL, "*");
                }
                case "/" -> {
                    Token token = new Token(DIV, "/");
                    this.tokens.add(token);
                    this.advance();
                    return new Token(DIV, "/");
                }
            }
        }

        return new Token(EOF, null);
    }

    private void advance() {
        this.pos++;
        if (this.pos > this.text.length() - 1) {
            this.currentChar = null;
        } else {
            this.currentChar = this.text.charAt(this.pos) + "";
        }
    }
}
