package gui;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Init {
	
	JFrame window;
	JButton server, client;
	JLabel instruct;
	JTextField id;
	ActionListener SS,SC,PE;
	Gui gui;
	
	public Init() {
		
		Border margin = new EmptyBorder(10,10,10,10);
		Border field1 = BorderFactory.createLineBorder(Color.decode("#57717f"), 3);
		Border field2 = BorderFactory.createLineBorder(Color.decode("#c15847"), 3);
		
		window = new JFrame("Start-up");
		server = new JButton("Start in Server Mode");
		client = new JButton("Start Client");
		instruct = new JLabel("Enter your user ID");
		id = new JTextField();
		
		window.setSize(510, 470);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		
		window.add(server);
		server.setBounds(100, 70, 300, 50);
		server.setBackground(Color.decode("#6f8896"));
		server.setBorder(field1);
		
		window.add(instruct);
		instruct.setBounds(100, 170, 300, 50);
		instruct.setBorder(margin);
		instruct.setHorizontalAlignment(0);
		
		window.add(id);
		id.setBounds(100, 220, 300, 50);
		id.setBorder(margin);
		
		window.add(client);
		client.setBounds(200, 300, 100, 50);
		client.setBackground(Color.decode("#e26f5a"));
		client.setBorder(field2);
		
		SS = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				client.setVisible(false);
				id.setVisible(false);
				instruct.setText("In Server Mode");
				server.setVisible(false);
				
				Thread server = new Thread(new ServerGui());
				server.start();
				
			}
		};
		
		SC = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!id.getText().isEmpty()) {
					
					window.setVisible(false);
					gui = new Gui(id.getText());
					
				}
				
			}
		};
		
		PE = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!id.getText().isEmpty()) {
					
					window.setVisible(false);
					gui = new Gui(id.getText());
					
				}
				
			}
		};
		
		server.addActionListener(SS);
		client.addActionListener(SC);
		id.addActionListener(PE);
		
	}
	
}
