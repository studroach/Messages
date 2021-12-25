package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Message {
	JTextArea box;
	Message next;
	int height;
	int from;	//0 - self, 1 - other
	
	public Message(String note, Contact current, int source, int pos) {
		
		Border margin = new EmptyBorder(10,10,10,10);
		
		from = source;
		height = 40;
		if(note.length() > 38) {
			
			height = (note.length()/38)*20;
			
		}
		
		box = new JTextArea(note);
		current.panel.add(box);
		box.setBorder(margin);
		box.setOpaque(true);
		box.setLineWrap(true);
		box.setWrapStyleWord(true);
		
		if(pos == 0) {
		
			if(from == 0) {
				box.setBounds(360, 680 - height, 400, height);
				box.setBackground(Color.decode("#e28171"));
			}else {
				box.setBounds(30, 680 - height, 400, height);
				box.setBackground(Color.WHITE);
			}
			
			box.setSize(box.getPreferredSize());
			box.setEditable(false);
			height = box.getHeight();
			
			if(from == 0) {
				box.setBounds(360, 680 - height, 400, height);
			}else {
				box.setBounds(30, 680 - height, 400, height);
			}
			
			current.loc = 690;
		
		}else {
			
			if(from == 0) {
				box.setBounds(360, pos, 400, height);
				box.setBackground(Color.decode("#e28171"));
			}else {
				box.setBounds(30, pos, 400, height);
				box.setBackground(Color.WHITE);
			}
			
			box.setSize(box.getPreferredSize());
			box.setEditable(false);
			height = box.getHeight();
			
			if(from == 0) {
				box.setBounds(360, pos, 400, height);
			}else {
				box.setBounds(30, pos, 400, height);
			}
			
			current.loc = pos + height + 10;
			
		}
		
		current.panel.setPreferredSize(new Dimension(790, current.panel.getHeight() + box.getHeight() + 10));
		current.pane.getViewport().revalidate();
		JScrollBar vertical = current.pane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		
	}
	
	public static Message GetLast(Message chain) {
		Message curr = chain;
		while(curr.next != null) {
			
			curr = curr.next;
			
		}
		return(curr);
		
	}

	
}
