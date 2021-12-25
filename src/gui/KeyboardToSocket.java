package gui;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class KeyboardToSocket extends Thread {
	Socket userConnectSocket;//socket from client main
	Ledger ledger;
	ObjectOutputStream objToSend;//This is what we are going to send to the server
	public KeyboardToSocket(Socket userConnectSocket, Ledger ledger) {//Basic constructor
		this.userConnectSocket = userConnectSocket;
		this.ledger = ledger;
	}
	
	public void run(){//this is the thread
		while (true) {
			if(ledger.newMessage) {//This variable is a volatile variable from the UI ledger class, just checks to see if there is a new line of code.
				try {
					objToSend = new ObjectOutputStream(userConnectSocket.getOutputStream());//sets up the outputstream
					SentMessage messageToSend = new SentMessage(ledger.id, ledger.current.id, ledger.lastMessage);//This constructs an object of type SentMessage
					objToSend.writeObject(messageToSend);//write to the outputstream
					//System.out.println("Object Sent");
					ledger.newMessage = false;//reset our volatile flag
					objToSend.flush();//flush the outputstream
				} catch(Exception e) {
					System.out.println("Error: " + e);
				}
			}
		}
	}
	
}
		

