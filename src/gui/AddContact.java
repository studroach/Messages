package gui;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class AddContact {
	JFrame jop;
	JTextField name,id;
	JLabel quest, questId;
	JButton submit;
	
	public AddContact(ActionListener AC, ActionListener PE) {
		
		Border margin = new EmptyBorder(10,20,10,10);
		
		name = new JTextField();
		id = new JTextField();
		quest = new JLabel("Enter contact name");
		questId = new JLabel("Enter contact ID");
		submit = new JButton("Add");
		jop = new JFrame("Add Contact");
		
		jop.setVisible(false);
		jop.setSize(510, 400);
		jop.setLocationRelativeTo(null);
		jop.setLayout(null);
		jop.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jop.add(quest);
		jop.add(questId);
		jop.add(submit);
		jop.add(name);
		jop.add(id);
		
		quest.setBounds(150, 30, 200, 40);
		quest.setHorizontalAlignment(0);
		
		name.setBounds(100, 80, 300, 40);
		name.setBorder(margin);
		name.addActionListener(PE);
		
		questId.setBounds(150, 140, 200, 40);
		questId.setHorizontalAlignment(0);
		
		id.setBounds(100, 190, 300, 40);
		id.setBorder(margin);
		id.addActionListener(PE);
		
		submit.setBounds(200, 270, 100, 40);
		submit.setBackground(Color.decode("#e26f5a"));
		submit.setBorder(null);
		submit.addActionListener(AC);
		
	}
	
}
