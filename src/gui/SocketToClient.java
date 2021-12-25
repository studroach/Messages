package gui;

//	SocketToClient is the program that manipulates the 
//	object/data that is sent from the server to the client
 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class SocketToClient extends Thread{
	Socket socket;      //The Socket that server and all users connect to
	Ledger ledger;
	public SocketToClient(Socket socket, Ledger ledger) {  //When initialized, the socket used is sent to SocketToClient (STC)
		this.socket = socket;
		this.ledger = ledger;
	}
	SentMessage in;         //Initialize SenetMessage object and object input stream
	ObjectInputStream toUser;  
	public void run() {             //Main run
		
		try {
			toUser = new ObjectInputStream(socket.getInputStream());    //create object Input Stream
			
		}catch(Exception e) {
			System.out.println("LEss bees more stufFF...:" + e);
			
		}
		while(true) {	//run infinite loop
			try {  
				System.out.println("I AM loop");  
				in = (SentMessage) toUser.readObject();  //read from ObjectInputStream and store Object
				
				System.out.println("gotcha @ " + in.receiver);
				System.out.println(in.sender +":");
				System.out.println("Ledger id:" + ledger.id);
				
				if(ledger.id.equals(in.receiver)) {     //if the reciever is equal to the UserID of the client
					System.out.println(in.msg);         //then print the message
				    Ledger.IncomingMessage(in, ledger.contacts);         //display to GUI
				}
				
			} catch (Exception e) {
				System.out.println("BEEEEES or something...: " + e);
				continue; 
			}
			
		}   //End of catch block

	}   //End of run method

}   //End of Class