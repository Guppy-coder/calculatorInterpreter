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

    public Interpreter(String text) {
        this.text = text;
        this.pos = 0;
        this.currentChar = this.text.charAt(this.pos) + "";
        this.currentToken = null;
    }

    public void error() {
        throw new RuntimeException("Error parsing input");
    }

    public void expr() {

        this.currentToken = this.getnextToken();
    }

    private boolean isDigit(String c) {
        return c.matches("[0-9]");
    }

    private int integer() {
        StringBuilder result = new StringBuilder();
        while (this.currentChar != null && isDigit(this.currentChar)) {
            result.append(this.currentChar);
            this.advance();
        }
        return Integer.parseInt(result.toString());
    }

    private Token getnextToken() {

        while (this.currentChar != null || !this.currentChar.equals(" ")) {

            if (isDigit(this.currentChar)) {
                Token token = new Token(INTEGER, integer());
                this.advance();
                return token;
            }
            switch (currentChar) {
                case "+" -> {
                    this.advance();
                    return new Token(PLUS, "+");
                }
                case "-" -> {
                    this.advance();
                    return new Token(MINUS, "-");
                }
                case "*" -> {
                    this.advance();
                    return new Token(MUL, "*");
                }
                case "/" -> {
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
