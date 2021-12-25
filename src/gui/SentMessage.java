package gui;

import java.io.Serializable;

public class SentMessage implements Serializable{
	public String sender, receiver = null, msg; 
	    //Sender = id of Client sending message
	    //reciever = id of Client receiving message
	    //msg = message string
	
	public SentMessage(String user, String rUser, String message) {
	    /*
	     * Initialize a message container with useful IDs 
	     */
	     
		sender = user;
		receiver = rUser;
		msg = message;
	} //end of SentMessage
	
	public SentMessage(String user){
	    /*
	     * Initial message, sent to server when a client logged in
	     */
		msg = user + " has logged in";
		sender = user;
	} //end of SentMessage(user)
	
}//end of class
