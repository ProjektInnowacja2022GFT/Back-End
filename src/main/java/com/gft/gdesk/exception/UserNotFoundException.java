package com.gft.gdesk.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(long id){
        super("User with id: \"" + id + "\" doesn't exist.");
    }
}
