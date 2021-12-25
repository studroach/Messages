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
		//PrintToClient ptc = new PrintToClient(socket);
		//Thread thread = new Thread (ptc);
		//thread.start();
		
		try
		{
			clientInput = new ObjectInputStream(socket.getInputStream()); //start an input stream, to get packets from user
			clientOutput = new ObjectOutputStream(socket.getOutputStream()); //start an output stream, to send packets to users
		}
		catch (Exception e){} //This will never throw an exception so we're too lazy to take that into account.
				
		try {
			while(clientInput.available()>0) //
			{
				sm = (SentMessage) clientInput.readObject();
			
				if(sm.receiver==null) {
					userLog[i++] = sm.sender;
					
				}else if(Arrays.asList(userLog).contains(sm.receiver)) {
					clientOutput.writeObject(sm);
					clientOutput.flush();
					
				}else{
					SentMessage error = new SentMessage(sm.sender,sm.sender,"User doesn't exist :(");
					clientOutput.writeObject(error);
					clientOutput.flush();
				}
			}

		}catch (Exception e) {return;}
	}	
}