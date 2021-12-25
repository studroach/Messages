package gui;

import java.io.Serializable;

public class SentMessage implements Serializable{
	public String sender, receiver = null, msg;
	
	public SentMessage(String user, String rUser, String message) {
		sender = user;
		receiver = rUser;
		msg = message;
	}
	public SentMessage(String user){
		msg = user + " has logged in";
		sender = user;
	}
	
}
