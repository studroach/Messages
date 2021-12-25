package gui;

import java.awt.Color;
import javax.swing.*;

public class Gui {
	
	JFrame frame;
	Ledger ledger;
	
	public Gui(String id) {
		
		//initializes main jframe
		frame = new JFrame(id);
		frame.setVisible(true);
		frame.setSize(1235, 860);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.decode("#68625a"));
		
		//initializes the structure that stores ALL client side data
		ledger = new Ledger(frame);
		ledger.id = id;
		
		ClientMain client = new ClientMain(ledger);    //Client side of program is initiated here with the user's ID
		client.run();            //Client program is started
		
	}
	
	
	
}
