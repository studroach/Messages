package gui;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Handles ServerThreading

import java.net.Socket;
import java.util.Arrays;

public class MyServerThread extends Thread
{
    /*
    * Server Thread, handles multiple users and relays information between clients.
    * Worked by Oleg and Khan.
    */
	
	ObjectInputStream clientInput; //Collects input packets from client through socket
	ObjectOutputStream clientOutput; //pushes message to the socket
	Socket socket; //socket on the server (localhost 1221)
	SentMessage sm; //package container for UserID, recipientID, and message string.
	
	String[] userLog = new String[100]; //list of names connected to Server
	Integer i = 0; //index for parsing username list (userLog)
	
	public MyServerThread(Socket mySocket)
	{
	   /*
	    * Initialize MyServerThread with refrence to the socket 
	    * Required to keep track of which socket to listen and write to.
	    */
		System.out.println("Constructing Server Thread");
		socket = mySocket; //establish socket from the ServerMain class
	}
	
	public void run ()
	{
		
		try
		{
			clientInput = new ObjectInputStream(socket.getInputStream()); //start an input stream, to get packets from user
			clientOutput = new ObjectOutputStream(socket.getOutputStream()); //start an output stream, to send packets to users
		}
		catch (Exception e){} //This will never throw an exception so we're too lazy to take that into account.
		while (true) {		
			
			try {
		
				sm = (SentMessage) clientInput.readObject();    // Sent message variable is taken from the input of the socket stream
				
				System.out.println(sm.sender +":");     //Sender of the package is shown
				System.out.println(sm.msg);             //Whatever the user typed shows up here
				
				clientOutput.writeObject(sm);           //The object that was passed to server is then pushed to the other client
				clientOutput.flush();
				
				clientInput = new ObjectInputStream(socket.getInputStream());   //Input stream grabs again from the socket 
				if(sm.receiver==null) {             //If new client joins, then the sent reciever is set to null
					userLog[i++] = sm.sender;       //The new client is added to an array of users available to message
					
				}else if(Arrays.asList(userLog).contains(sm.receiver)) {        //Checks if the reciever is contained within the array of possible recipients
					clientOutput.writeObject(sm);               //Write the object/message and write it out to client
					clientOutput.flush();
					
				}else{
					SentMessage error = new SentMessage(sm.sender,sm.sender,"User doesn't exist :(");   //If the user is no not from either of the above scenarios
					clientOutput.writeObject(error);                                                   //Inform of illeagel user and push the error message to client
					clientOutput.flush();
				}
				
			}catch (Exception e) {continue;}
		}
	}	
}