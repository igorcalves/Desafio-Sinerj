package br.com.igor.Exception;

import java.time.LocalDateTime;
public class ThereWereNoSales extends RuntimeException {

    private String message;

    public ThereWereNoSales(String msg)
    {
        super(msg);
        this.message = msg;
    }


}
