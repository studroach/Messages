package gui;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain 
{
	public ServerMain()
	{
		//Initialize serverSocket and Socket
		ServerSocket serverSocket;
		Socket socket;
		
		try
		{
			serverSocket = new ServerSocket(1221);	//Try to connect to serverSocket port 1221
		}
		catch (Exception e) {return;}	//else return
		
		while (true)
		{
			try {
				socket = serverSocket.accept();	//Try to accept the socket connection, wait for connection
				System.out.println("accepted");
			} catch (Exception e) {
				
				System.out.println("Error: " + e);
				return;
				
			}	//return if can't connect
			
			MyServerThread serverThread = new MyServerThread(socket);	//create a new server object
			Thread newThread = new Thread (serverThread);	//create a separte thread for the new server object
			newThread.start();	//start the thread
		}
	}
}