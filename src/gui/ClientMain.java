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
		ObjectOutputStream loginPKG;//This is a package we are going to send to the sever telling it that the user has logged on
		try {
			socket = new Socket("localhost", 1221);
			loginPKG = new ObjectOutputStream(socket.getOutputStream());
			loginPKG.writeObject(new SentMessage(ledger.id));
			loginPKG.flush();
		}catch(Exception e) {
			System.out.println("Error: " + e);
			return;
		}
		
		KeyboardToSocket thread1 = new KeyboardToSocket(socket, ledger);//Create and start the KeyboardToSocket Thread;
		thread1.start();

		SocketToClient thread2 = new SocketToClient(socket, ledger);//Create and star the SocketToClient thread
		thread2.start();
	
	}

}


