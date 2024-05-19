package br.com.igor.Exception;

public class ListIsEmpty extends RuntimeException {


    private String message;

    public ListIsEmpty(String msg)
    {
        super(msg);
        this.message = msg;
    }

}
