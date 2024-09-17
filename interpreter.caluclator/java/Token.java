public class Token {

    String type;
    int value;
    String stringValue;

    Token(String type, int value) {
        this.type = type;
        this.value = value;
    }

    Token(String type, String value) {
        this.type = type;
        this.stringValue = value;
    }

    public String toString() {
        return "Token(" + this.type + ", " + this.value + ")";
    }

}
