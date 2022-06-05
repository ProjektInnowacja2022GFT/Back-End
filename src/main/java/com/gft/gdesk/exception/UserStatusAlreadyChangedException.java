package com.gft.gdesk.exception;

public class UserStatusAlreadyChangedException extends Exception{
    public UserStatusAlreadyChangedException(String status){
        super("User status can't be changed. Current user status: \"" + status + "\".");
    }
}
