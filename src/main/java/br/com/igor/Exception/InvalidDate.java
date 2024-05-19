package br.com.igor.Exception;

public class InvalidDate extends RuntimeException{


    private String message;

    public InvalidDate(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
