package snake;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyFrame extends JFrame {
	
	public MyFrame() {
//		super("Snake");   
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);
//		setSize(566,628);  //uzaleznic size od rozdzielczosci ekranu
//		setLocation(100,100); //to tez uzaleznic
//		setLayout(new GridLayout(2,1,10,10));
//		Snake.board = new Board();
//		add(Snake.board);
//		
//		var buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		buttonPanel.setFocusable(false);
//		var sliderPanel = new JPanel(new FlowLayout());
//		sliderPanel.setFocusable(false);
//		var myPanel = new JPanel(new GridLayout(1,2));
//		myPanel.setFocusable(false);
//		myPanel.add(buttonPanel);
//		myPanel.add(sliderPanel);
//		add(myPanel);
//		
//		var startButton = new JButton("START");
//		startButton.setFocusable(false);
//		buttonPanel.add(startButton);
//		var restartButton = new JButton("RESTART");
//		restartButton.setFocusable(false);
//		buttonPanel.add(restartButton);
//		var endButton = new JButton("END");
//		endButton.setFocusable(false);
//		buttonPanel.add(endButton);
//		
//		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 20, 100, 50);
//		speedSlider.setFocusable(false);
//		speedSlider.setMinorTickSpacing(1000);
//		speedSlider.setMajorTickSpacing(10);
//		speedSlider.setPaintLabels(true);
//		speedSlider.setPaintTicks(true);
//		sliderPanel.add(new JLabel("Snake's speed"));
//		sliderPanel.add(speedSlider);
//		
//		speedSlider.addChangeListener(new ChangeListener() {
//			
//			@Override
//			public void stateChanged(ChangeEvent arg0) {
//				// TODO Auto-generated method stub
//				JSlider source = (JSlider)arg0.getSource();				
//				if(!source.getValueIsAdjusting()) {
//					Snake.speed = source.getValue();
//				}
//			}
//		});
//		
//		setVisible(true);
	}
}
