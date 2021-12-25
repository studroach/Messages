package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;

public class Ledger {
	
	JPanel window,screen;
	Contact contacts,current;
	JButton newContact,logout;
	JTextField message;
	AddContact addCon;
	ActionListener AC,NC,CC,PE,LO;
	int list;
	String id, lastMessage;
	boolean isRunning; 
	volatile boolean newMessage = false;
	
	public Ledger(JFrame frame) {
		
		list = 10;	//place holder for new contact positions
		
		//vars for client threads
		isRunning = true;
		//newMessage = false;
		
		//pointers that are empty at start
		contacts = null;
		current = null;
		
		//borders for buttons and fields
		Border field = BorderFactory.createLineBorder(Color.decode("#c15847"), 3);
		Border margin = new EmptyBorder(10,20,10,10);
		
		//temporary jpanel for esthetics
		screen = new JPanel();
		frame.add(screen);
		screen.setBounds(420, 10, 790, 750);
		screen.setLayout(null);
		screen.setBackground(Color.decode("#6f8896"));
		
		//jpanel that has contact buttons
		window = new JPanel();
		frame.add(window);
		window.setBounds(10, 10, 400, 800);
		window.setLayout(null);
		window.setBackground(Color.decode("#4b6777"));
		
		//init input field
		message = new JTextField();
		frame.add(message);
		message.setBounds(420, 760, 790, 50);
		message.setBorder(margin);
		message.setEditable(false);
		
		//init the new contact button
		newContact = new JButton("New Contact");
		window.add(newContact);
		newContact.setBounds(10, 10, 380, 50);
		newContact.setBackground(Color.decode("#d36452"));
		newContact.setBorder(field);
		
		//init the logout button
		logout = new JButton("Logout");
		window.add(logout);
		logout.setBounds(10, 740, 380, 50);
		logout.setBackground(Color.decode("#d36452"));
		logout.setBorder(field);
		
		//add contact listener
		AC = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				newContact.setBounds(10, list+60, 380, 50);
				
				if(contacts == null) {
					
					contacts = new Contact(addCon.name.getText(), list, frame, window, CC, addCon.id.getText());
					
				}else {
				
					Contact.AddC(addCon.name.getText(), list, frame, window, contacts, CC, addCon.id.getText());
					
				}
				
				list = list + 60;
				addCon.jop.setVisible(false);
				addCon.name.setText("");
				
			}
		};
		
		//press enter listener for text fields
		PE = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if main input was source
				if (e.getSource() == message) {
					
					if(current.messages == null && !message.getText().isEmpty()) {	//if this is first message
						
						current.messages = new Message(message.getText(), current, 0, current.loc);
						
						lastMessage = message.getText();
						
						newMessage = true;
						
					}else if(!message.getText().isEmpty()){		//for future messages
						
						Message temp = Message.GetLast(current.messages);
						temp.next = new Message(message.getText(), current, 0, current.loc);
						
						lastMessage = message.getText();
						
						newMessage = true;
						
					}
					
					message.setText("");
				
				//if source was id field in add contact window
				}else if(e.getSource() == addCon.id && !((JTextComponent) e.getSource()).getText().isEmpty()){
					
					newContact.setBounds(10, list+60, 380, 50);
					
					if(contacts == null) {	//if first contact
						
						contacts = new Contact(addCon.name.getText(), list, frame, window, CC, addCon.id.getText());
						
					}else {	//future contacts
					
						Contact.AddC(addCon.name.getText(), list, frame, window, contacts, CC, addCon.id.getText());
						
					}
					
					list = list + 60;
					addCon.jop.setVisible(false);	//hides the add contact window
					addCon.name.setText("");
					addCon.id.setText("");
				
				//if source was name field in add contact window
				}else if(!((JTextComponent) e.getSource()).getText().isEmpty()){
					
					addCon.id.requestFocus();
					
				}
				
			}
		};
		
		//creates the add contact window
		addCon = new AddContact(AC, PE);
		
		//action listener for the add contact button that opens the add contact window
		NC = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addCon.jop.setVisible(true);
				
			}
		};
		
		//action listener for the contact buttons that toggles the messages when switching contacts
		CC = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//hides temporary panel and allows editing of the input field
				screen.setVisible(false);
				message.setEditable(true);
				
				if(current != null) {
					
					//hides the current message window
					current.board.setVisible(false);
					
				}
				
				//gets the new contact and displays the visuals for that persons messages
				current = Contact.GetConName(((AbstractButton) e.getSource()).getText(), contacts);
				current.board.setVisible(true);
				
			}
		};
		
		//action listener for logout button
		LO = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				isRunning = false;
				System.exit(0);
				
			}
		};
		
		//adds action listeners to proper things
		newContact.addActionListener(NC);
		message.addActionListener(PE);
		logout.addActionListener(LO);
		
	}
	
	//method for incoming messages to be handled and stored by the gui
	public static void IncomingMessage(SentMessage obj, Contact cont) {

        Contact tempC = Contact.GetConId(obj.sender, cont);

        if(tempC != null) {
        	if(tempC.messages == null) {
        		tempC.messages = new Message(obj.msg, tempC, 1, tempC.loc);
        	}else {
            	Message temp = Message.GetLast(tempC.messages);
            	temp.next = new Message(obj.msg, tempC, 1, tempC.loc);
        	}

        }else {

            System.out.println("message gui did not work, couldnt find contact with id: " + obj.sender);

        }

    }
	
}
