package gui;

/*
 * Holds the contact list for logged in users
 * Keeps track of previously sent messages
 */

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class Contact {
	
	String name,id; //user name and receiver ID
	JButton contact; //add contact button
	Message messages; //sent and received messages
	JPanel board,panel;
	JScrollPane pane;
	JTextField note;
	Contact next;
	JLabel person;
	int loc;
	
	public Contact(String Name, int pos, JFrame frame, JPanel window, ActionListener CC, String Id) {
		//Creating contact
		name = Name;
		id = Id;
		loc = 0;
		
		Border field = BorderFactory.createLineBorder(Color.decode("#c15847"), 3);
		Border margin = new EmptyBorder(10,20,10,10);
		
		contact = new JButton(name);
		contact.setBounds(10, pos, 380, 50);
		contact.setBackground(Color.decode("#d36452"));
		contact.setBorder(field);
		contact.addActionListener(CC);
		next = null;
		window.add(contact);
		
		board = new JPanel();
		frame.add(board);
		board.setVisible(false);
		board.setBounds(420, 10, 790, 750);
		board.setLayout(null);
		board.setBackground(Color.decode("#6f8896"));
		board.setBorder(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#6f8896"));
		
		pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		board.add(pane);
		pane.setBounds(0, 50, 790, 700);
		pane.setViewportView(panel);
		
		person = new JLabel(name);
		board.add(person);
		person.setBounds(0, 0, 790, 50);
		person.setBackground(Color.decode("#d36452"));
		person.setOpaque(true);
		person.setBorder(new CompoundBorder(field, margin));
		
		messages = null;
		
	}
	
	public static void AddC(String name, int pos, JFrame frame, JPanel window, Contact contacts, ActionListener CC, String id) {
		//populating contact list
		Contact curr = contacts;
		
		while(curr.next != null) {
			
			curr = curr.next;
			
		}
		
		curr.next = new Contact(name, pos, frame, window, CC, id);
		
	}
	
	public static Contact GetConName(String name, Contact contacts) {
		Contact curr = contacts;
		while(curr.name != name) {
			
			curr = curr.next;
			
		}
		return(curr);
		
	}
	
	public static Contact GetConId(String id, Contact contacts) {
		Contact curr = contacts;
		while(curr != null && !curr.id.contentEquals(id)) {
			
			curr = curr.next;
			
		}
		return(curr);
		
	}
	
}
