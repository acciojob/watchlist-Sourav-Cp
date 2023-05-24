package com.driver;

public class DirectorNotPresentException extends RuntimeException{
    public DirectorNotPresentException(String str)
    {
        super(str);
    }
}
