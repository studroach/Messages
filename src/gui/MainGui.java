/*
Creators: Oleg Kryachun, Nathan Kirsch, Jake Castedo, Tofik Khan, Caleb Hallier
Date: 06/10/2019
CSE 223 Programming Assignment 5

Summary: This program implements a Server-Client multithreading system which allows the Server and Client to communicate with each other simultaneously. The program sets up mutliple
threads for the Server and multiple threads for the Client. It uses Object and Input Streams to pass object of data back and forth between Client and Server. The main purpose of the
Server is to traffic control the messages from the Clients, the Server recieves messages and sends them to the correct Clients. 
*/

package gui;

public class MainGui {

	public static void main(String[] args) {
		
		new Init();//starts program
		
	}

}
