package snake;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake implements ActionListener, KeyListener {
	
	public Direction direction;
	public Board board = new Board();
	public static Point apple = null; 
	public static ArrayList<Point> snakeBody = new ArrayList<Point>();
	public Point snakeHead;
	public static final int bodyPartSize = 12;
	public Timer timer = new Timer(5,this);
	public int time = 0;
	public int speed;
	Random rand = new Random();
//	Dimension dim;
	public Snake() {
		createGUI();
		speed = 50;
		for(int i = 0; i < 5; i++)  //5 is initial snake's size
			snakeBody.add(new Point(0, i*bodyPartSize));
		snakeHead = snakeBody.get(snakeBody.size()-1);
		direction = Direction.DOWN;
		board.addKeyListener(this);
//		timer.start();
	}
	
	public void createGUI() {
		JFrame myJFrame = new JFrame();
		myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myJFrame.setResizable(false);
		myJFrame.setSize(496,319);  //uzaleznic size od rozdzielczosci ekranu
		myJFrame.setLocation(100,100); //to tez uzaleznic
		//myJFrame.setLayout(new GridLayout(2,1,10,10));
		JPanel jpanel = new JPanel(new BorderLayout());
		myJFrame.add(jpanel);
//		myJFrame.add(board);
		jpanel.add(board, BorderLayout.CENTER);
		//jpanel.setSize(200, 200);
		
		var buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setFocusable(false);
		var sliderPanel = new JPanel(new FlowLayout());
		sliderPanel.setFocusable(false);
		var myPanel = new JPanel(new GridLayout(1,2));
		myPanel.setFocusable(false);
		myPanel.add(buttonPanel);
		myPanel.add(sliderPanel);
//		myJFrame.add(myPanel);
		myJFrame.add(myPanel, BorderLayout.PAGE_END);
		
		var startButton = new JButton("START");
		startButton.setFocusable(false);
		startButton.setFont(new Font("Arial", Font.PLAIN, 10));
		startButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent button) {
				// TODO Auto-generated method stub
				if(startButton.getText() == "START" || startButton.getText() == "RESUME") {
					startButton.setText("PAUSE");
					startGame();
				} else {
					startButton.setText("RESUME");
					pauseGame();
				}
			}
		});
		buttonPanel.add(startButton);
		var restartButton = new JButton("RESTART");
		restartButton.setFocusable(false);
		restartButton.setFont(new Font("Arial", Font.PLAIN, 10));
		restartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				restartGame();
			}
		});
		buttonPanel.add(restartButton);
		var exitButton = new JButton("EXIT");
		exitButton.setFocusable(false);
		exitButton.setFont(new Font("Arial", Font.PLAIN, 10));
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		buttonPanel.add(exitButton);
		
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 20, 100, 50);
		speedSlider.setFocusable(false);
//		speedSlider.setMinorTickSpacing(1000);
		speedSlider.setMajorTickSpacing(10);
		speedSlider.setPaintLabels(true);
		speedSlider.setPaintTicks(true);
		sliderPanel.add(new JLabel("Snake's speed"));
		sliderPanel.add(speedSlider);
		
		speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent slider) {
				// TODO Auto-generated method stub
				JSlider source = (JSlider)slider.getSource();				
				if(!source.getValueIsAdjusting()) {
					speed = source.getValue();
				}
			}
		});
		
		myJFrame.setVisible(true);
	}
	
	public void startGame() {
		timer.start();
	}
	
	public void pauseGame() {
		timer.stop();
	}
	
	public void restartGame() {
		timer.stop();
		snakeBody.clear();
		for(int i = 0; i < 5; i++)  //5 is initial snake's size
			snakeBody.add(new Point(0, i*bodyPartSize));
		snakeHead = snakeBody.get(snakeBody.size()-1);
		direction = Direction.DOWN;
		timer.start();
		
	}
	
	public void generateApple(Dimension dim) {
		int x = rand.nextInt(dim.width);
		int y = rand.nextInt(dim.height);
		boolean found = false;
		while(!found) {
			while(x % bodyPartSize != 0) {   //apple should be generated in the same multiple pixels as snake parts
				x = rand.nextInt(dim.width - bodyPartSize);
			}
			while(y % bodyPartSize != 0) {
				y = rand.nextInt(dim.height - bodyPartSize);
			}
			for(Point point : snakeBody) {
				if(point.x == x && point.y == y) {  //if apple's point collides with snake found is still false
					found = false;
					break;
				}
				found = true;
			}
		}
		apple = new Point(x,y);
	}

	@Override
	public void actionPerformed(ActionEvent timer) {
		// TODO Auto-generated method stub
		Dimension dim = board.getSize();
		if(null == apple) {
			generateApple(dim);
		}
		
		time++;
		if(time % (110 - speed) == 0) {
			if(direction == Direction.DOWN) {
				if(snakeHead.y + bodyPartSize >= dim.height)
					snakeBody.add(new Point(snakeHead.x, 0));
				else
					snakeBody.add(new Point(snakeHead.x, snakeHead.y+bodyPartSize));					
			}
			
			if(direction == Direction.UP) {
				if(snakeHead.y - bodyPartSize < 0)
					snakeBody.add(new Point(snakeHead.x, dim.height - bodyPartSize));
				else
					snakeBody.add(new Point(snakeHead.x, snakeHead.y-bodyPartSize));
			}
			
			if(direction == Direction.LEFT) {
				if(snakeHead.x - bodyPartSize < 0)
					snakeBody.add(new Point(dim.width - bodyPartSize, snakeHead.y));
				else
					snakeBody.add(new Point(snakeHead.x - bodyPartSize, snakeHead.y));
			}
			
			if(direction == Direction.RIGHT) {
				if(snakeHead.x + bodyPartSize >= dim.width)
					snakeBody.add(new Point(0, snakeHead.y));
				else
					snakeBody.add(new Point(snakeHead.x + bodyPartSize, snakeHead.y));
				
			}
		
			snakeHead = snakeBody.get(snakeBody.size()-1);  //move the head
			
			if(!snakeHead.equals(apple))   //if snake doesn't collide with an apple
				snakeBody.remove(0);       //shorten the tail
			else
				apple = null;
			
			for(int i = 0; i < snakeBody.size()-1; i++) {  
				if(snakeHead.equals(snakeBody.get(i))) {   //check if snake collides with itself
					endGame();
				}
			}
			
		}
		
		board.repaint();
		
		
		

		
//		snakeHead = snakeBody.get(snakeBody.size()-1);
//		snakeBody.remove(0);
		
			
	}
	
	public void endGame() {
		timer.stop();
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
