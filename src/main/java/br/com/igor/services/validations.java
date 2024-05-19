package br.com.igor.services;

import br.com.igor.Exception.InvalidDate;
import br.com.igor.Exception.ListIsEmpty;

import java.util.List;

public class validations {

    public static boolean isValidMonth(int numeric){
        if(numeric>0 && numeric <=12) return  true;
        throw new InvalidDate("The value of The Month is Invalid try a number 1 among 12");

    }    public static boolean isValidYear(Integer numeric){
        if(numeric.toString().length() ==4) return  true;
        throw new InvalidDate("The value of The year is Invalid");
    }

    public static<T> boolean ListNotEmpty(List<T> list){
        if(!list.isEmpty()) return  true;
        throw new ListIsEmpty("the list is empty");
    }













}