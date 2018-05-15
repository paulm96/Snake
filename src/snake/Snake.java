package snake;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.tools.DiagnosticCollector;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake implements ActionListener, KeyListener {
	
	public Direction direction;
	public Board board = new Board();
	public static ArrayList<Point> snakeBody = new ArrayList<Point>();
	public Point snakeHead;
	public static final int bodyPartSize = 10;
	public Timer timer = new Timer(5,this);
	public int time = 0;
	public int speed;

	public Snake() {
		createGUI();
		speed = 50;
		for(int i = 0; i < 5; i++)  //5 is initial snake's size
			snakeBody.add(new Point(0, i*bodyPartSize));
		snakeHead = snakeBody.get(snakeBody.size()-1);
		direction = Direction.DOWN;
		board.addKeyListener(this);
		timer.start();
	}
	
	public void createGUI() {
		JFrame myJFrame = new JFrame();
		myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myJFrame.setResizable(false);
		myJFrame.setSize(566,628);  //uzaleznic size od rozdzielczosci ekranu
		myJFrame.setLocation(100,100); //to tez uzaleznic
		myJFrame.setLayout(new GridLayout(2,1,10,10));		
		myJFrame.add(board);
		
		var buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setFocusable(false);
		var sliderPanel = new JPanel(new FlowLayout());
		sliderPanel.setFocusable(false);
		var myPanel = new JPanel(new GridLayout(1,2));
		myPanel.setFocusable(false);
		myPanel.add(buttonPanel);
		myPanel.add(sliderPanel);
		myJFrame.add(myPanel);
		
		var startButton = new JButton("START");
		startButton.setFocusable(false);
		buttonPanel.add(startButton);
		var restartButton = new JButton("RESTART");
		restartButton.setFocusable(false);
		buttonPanel.add(restartButton);
		var endButton = new JButton("END");
		endButton.setFocusable(false);
		buttonPanel.add(endButton);
		
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 20, 100, 50);
		speedSlider.setFocusable(false);
		speedSlider.setMinorTickSpacing(1000);
		speedSlider.setMajorTickSpacing(10);
		speedSlider.setPaintLabels(true);
		speedSlider.setPaintTicks(true);
		sliderPanel.add(new JLabel("Snake's speed"));
		sliderPanel.add(speedSlider);
		
		speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				JSlider source = (JSlider)arg0.getSource();				
				if(!source.getValueIsAdjusting()) {
					speed = source.getValue();
				}
			}
		});
		
		myJFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent timer) {
		// TODO Auto-generated method stub
		Dimension dim = board.getSize();
		time++;
		if(time % speed == 0) {
			if(direction == Direction.DOWN) {
				if(snakeHead.y + bodyPartSize == dim.height)
					snakeBody.add(new Point(snakeHead.x, 0));
				else
					snakeBody.add(new Point(snakeHead.x, snakeHead.y+bodyPartSize));
				
				snakeHead = snakeBody.get(snakeBody.size()-1);
				snakeBody.remove(0);
			}
			if(direction == Direction.UP) {
				snakeBody.add(new Point(snakeHead.x, snakeHead.y-bodyPartSize));
				snakeHead = snakeBody.get(snakeBody.size()-1);
				snakeBody.remove(0);
			}
			if(direction == Direction.LEFT) {
				snakeBody.add(new Point(snakeHead.x-bodyPartSize, snakeHead.y));
				snakeHead = snakeBody.get(snakeBody.size()-1);
				snakeBody.remove(0);
			}
			if(direction == Direction.RIGHT) {
				snakeBody.add(new Point(snakeHead.x+bodyPartSize, snakeHead.y));
				snakeHead = snakeBody.get(snakeBody.size()-1);
				snakeBody.remove(0);
			}
		}
		board.repaint();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		int code = key.getKeyCode();
		
		switch(code) {
		case KeyEvent.VK_UP:
			direction = Direction.UP;
			break;
		case KeyEvent.VK_DOWN:
			direction = Direction.DOWN;
			break;
		case KeyEvent.VK_LEFT:
			direction = Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			direction = Direction.RIGHT;
			break;	
		}
		
		board.repaint();
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
		int code = key.getKeyCode();
		
		switch(code) {
		case KeyEvent.VK_UP:
			direction = Direction.UP;
			break;
		case KeyEvent.VK_DOWN:
			direction = Direction.DOWN;
			break;
		case KeyEvent.VK_LEFT:
			direction = Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			direction = Direction.RIGHT;
			break;	
		}
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		int code = key.getKeyCode();
		
		switch(code) {
		case KeyEvent.VK_UP:
			direction = Direction.UP;
			break;
		case KeyEvent.VK_DOWN:
			direction = Direction.DOWN;
			break;
		case KeyEvent.VK_LEFT:
			direction = Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			direction = Direction.RIGHT;
			break;	
		}
	}
	
//	static void stopSnake() {
//		timer.stop();
//	}

}
