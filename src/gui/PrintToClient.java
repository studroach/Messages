package gui;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PrintToClient extends Thread{

	PrintWriter clientOutput;
	Scanner input;
	Socket sock;
	
	public PrintToClient(Socket socket) {
		sock = socket;
		
	}
	
	public void run() {
		System.out.println("Inside Run of printer");

		try {
			System.out.println("Connecting to socket");
			clientOutput = new PrintWriter(sock.getOutputStream());
		}catch(Exception e ) {
			System.out.println("Print to Client error");
			return;
		}
		
		input = new Scanner(System.in);
		while(input.hasNextLine()) {
			clientOutput.println("Sent: " + input.nextLine());
			clientOutput.flush();
		}
	}
}
