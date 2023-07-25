package com.contact.helpr;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class GenerateOtp {

	public static void generateOtp(String subject,String message,String to,String from)
	{
String host="smtp.gmail.com";
    	
    	Properties property=System.getProperties();
    	property.put("mail.smtp.host", host);
    	property.put("mail.smtp.port", "465");
    	property.put("mail.smtp.ssl.enable","true");
    	property.put("mail.smtp.auth", "true");
    	
    	Session session=Session.getInstance(property, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("mjtech8169569382@gmail.com", "jkqlfbhujpaginxs");
			}
    		
    		
		});
    	
    	session.setDebug(true);
    	
    	MimeMessage m = new MimeMessage(session);
    	try
    	{
    		m.setFrom(from);
    		
    		m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		
    		m.setSubject(subject);
    		
    		m.setContent(message,"text/html; charset=utf-8");
    		
    		Transport.send(m);
    		System.out.println("Otp send successfuly");
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
}
