package snake;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultFrame extends JFrame {
	
	public ResultFrame(String statement) {
		
		setLayout(new GridLayout(2,1));
		
		setResizable(false);
		setSize(100,100);
		setLocation(200,150);
		
		JPanel myPanel = new JPanel();
		add(myPanel);
		myPanel.setLayout(new GridBagLayout());
		
		myPanel.add(new JLabel(statement));
		JButton endButton = new JButton("OK");
		add(endButton);
		endButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent button) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
//		
	}
}
