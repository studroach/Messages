package gui;

//	SocketToClient is the program that manipulates the 
//	object/data that is sent from the server to the client
 
import java.io.ObjectInputStream;
import java.net.*;

public class SocketToClient extends Thread{
	Socket socket;      //The Socket that server and all users connect to
	Ledger ledger;
	public SocketToClient(Socket socket, Ledger ledger) {  //When initialized, the socket used is sent to SocketToClient (STC)
		this.socket = socket;
		this.ledger = ledger;
	}
	
	public void run() {             //Main run
		ObjectInputStream toUser;   //ObjectInputStream is the literal stream passed to STC
		SentMessage in;             //'in' is the object that one user has passed to the other user
		
		try {                       
			toUser = new ObjectInputStream(socket.getInputStream());    //Attempt to create an ObjectInputStream through socket
			                                                           //This is the reason for the Try-Catch Block
			
			while(toUser.available()>0) {                       //Checks that the user is still passing information through the stream
				in = (SentMessage) toUser.readObject();         //Object stream is parsed back to object form to be utilized
				
				if(ledger.id.equals(in.receiver)) {            //if the reciever matches the user ID of the client
				    ledger.IncomingMessage(in);                //Display this message
				}
			}
			
			try {                                               
				socket.close();                                 //Attempt to close the socket
//				System.out.println("Closing down my guy!");     //Farewell text
			} catch (Exception e) {                             //If it somehow fails,
//				System.out.println(e + "UMM idk");              //print this...
			}
			
		} catch (Exception e) {                             //If the toUser ObjectInputStream could not be constructed
//			System.out.println("No good!");                 //Inform the user as such
		}   //End of catch block

	}   //End of run method

}   //End of Class