package Mortgage_Calculator.exceptions;

public class MortgageException extends RuntimeException{
    public MortgageException() {
        super("Case not handled");
    }
}
