package com.contact.helpr;

public class UserFoundException extends Exception{

	public UserFoundException()
	{
		super("User Already registered");
	}
	
	public UserFoundException(String message)
	{
		super(message);
	}
}
