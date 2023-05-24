package com.driver;

public class MovieNotPresentException extends RuntimeException{
    public MovieNotPresentException(String str)
    {
        super(str);
    }
}
