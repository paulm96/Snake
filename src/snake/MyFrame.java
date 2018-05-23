package snake;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyFrame extends JFrame {
	
	public MyFrame(String statement) {
		
		setLayout(new GridLayout(2,1));
		
		setResizable(false);
		setSize(100,100);  //uzaleznic size od rozdzielczosci ekranu
		setLocation(200,150); //to tez uzaleznic
		
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
