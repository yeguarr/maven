package Exceptions;

public class EndOfFile extends RuntimeException {
    public EndOfFile(String errorMessage) {
        super(errorMessage);
    }
}
