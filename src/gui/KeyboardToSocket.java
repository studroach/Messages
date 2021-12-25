package gui;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class KeyboardToSocket extends Thread {
	Socket userConnectSocket;
	Ledger ledger;//This is a passed class that has a bunch of useful variable such as the user id
	public KeyboardToSocket(Socket userConnectSocket, Ledger ledger) {//Constructor 
		this.userConnectSocket = userConnectSocket;//Gets the socket
		this.ledger = ledger;//Gets the ledger
	}
	
	public void run(){
		
		ObjectOutputStream fromUser;//This is the message object from the user;
		
		try {
			fromUser = new ObjectOutputStream(userConnectSocket.getOutputStream());//Open's the output stream for the object
			
			while(ledger.isRunning) {//checks to see if the program is running
				if (ledger.newMessage) {//this flag gets set to true when the user presses enter
					fromUser.writeObject(new SentMessage(ledger.id, ledger.current.id, ledger.message.getText()));
					fromUser.flush();
					ledger.newMessage = false;//resets our flag
				}
			}

		}catch(Exception e) {
			System.out.println("Error: " + e);
		}
		
	}
}
