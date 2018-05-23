package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;


public class Board extends JPanel {
	private Color snakeColor = Color.green;
	private Color appleColor = Color.red;
	public Board() {
		setOpaque(true);
		setBackground(Color.black);
		setFocusable(true);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void paintComponent(Graphics graphics) {
		// TODO Auto-generated method stub
		super.paintComponent(graphics);
		graphics.setColor(Color.WHITE);
		graphics.drawString("Score: " + Integer.toString(Snake.snakeBody.size()), 10, 10);
		graphics.setColor(snakeColor);
		for(Point part : Snake.snakeBody) {
			graphics.fillOval(part.x, part.y, Snake.bodyPartSize-1, Snake.bodyPartSize-1);
		}
		
		graphics.setColor(appleColor);
		if(null != Snake.apple) 
			graphics.fillOval(Snake.apple.x, Snake.apple.y, Snake.bodyPartSize-1, Snake.bodyPartSize-1);
	}
	
}
