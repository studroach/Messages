package gui;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain {
	public Ledger ledger;//This holds information about the current user using this client
	
	public ClientMain(Ledger ledger) {
		this.ledger = ledger;//gets the ledger
	}

	public void run() {
		Socket socket;
		ObjectOutputStream loginPKG; //This is a package we are going to send to the sever telling it that the user has logged on
		try {
			socket = new Socket("localhost", 1221); //set up socket to connect to the localhost and port 1221
			loginPKG = new ObjectOutputStream(socket.getOutputStream());    //Create ObjectOuputStream for the object
			SentMessage loginUser = new SentMessage(ledger.id);     //create message object
			loginPKG.writeObject(loginUser);        //write the object to the stream output
			System.out.println(loginUser.sender);   
			loginPKG.flush();   //flush the output stream
		}catch(Exception e) {
			System.out.println("Error: " + e);
			return;
		}

		
		SocketToClient thread2 = new SocketToClient(socket, ledger);//Create and start the SocketToClient thread
		KeyboardToSocket thread1 = new KeyboardToSocket(socket, ledger);//Create and start the KeyboardToSocket Thread;
		thread2.start();    //start the new threats
		thread1.start();
		
	
	}

}


