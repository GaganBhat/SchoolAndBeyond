package labs.screensaver;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.JComponent;

public class ScreenSaverComponent extends JComponent
{
	// Starting color for drawing
	private static final Color STARTING_COLOR = Color.magenta;
	
	// Min and max values for translations of x and y
	private static final int MIN_CHANGE = 5;
	private static final int MAX_CHANGE = 50;
	private int maxCircles;
	private int diameter;
	private int chgX;
	private int chgY;

	private int prev_x;
	private int prev_y;

	private Color randomColor;

	private Queue<Circle> circleQueue;
	private boolean initialStartup = true;

	public ScreenSaverComponent(int max, int diameter, int chgX, int chgY) 
	{
		this.maxCircles = max;
		this.diameter = diameter;
		this.chgX = chgX;
		this.chgY = chgY;
		circleQueue = new LinkedList<>();
		setRandomColor();
	}

	/** Add a new circle to be drawn and then draw all circles.
	 *  @param g the Graphics object for drawing in this component
	 */
	@Override
	public void paintComponent(Graphics g) 
	{
		Graphics2D gr2 = (Graphics2D) g;
		int maxWidth = getWidth();
		int maxHeight = getHeight();
		
		addCircle();
		drawAll(gr2);
	}
	
	private void drawAll(Graphics2D gr)
	{
		for(Circle circle : circleQueue)
			drawCenteredCircle(gr,circle.getUpperLeft().x, circle.getUpperLeft().y,
					diameter, circle.getColor());
	}

	private void drawCenteredCircle(Graphics2D g, int xTransformed, int yTransformed, int r, Color color) {
		xTransformed = xTransformed-(r/2);
		yTransformed = yTransformed-(r/2);
		prev_x = xTransformed;
		prev_y = yTransformed;

		g.setColor(color);
		g.fillOval(xTransformed,yTransformed,r,r);
	}
	
	private void addCircle() 
	{
		if(initialStartup){
			initialStartup = false;
			circleQueue.add(
					new Circle(
							new Point(getWidth()/2, getHeight()/2),
							randomColor
					)
			);
		} else {
			circleQueue.add(
					new Circle(
							new Point(prev_x + chgX, prev_y + chgY),
							randomColor
					)
			);
		}

	}

	private void setRandomColor(){
		Random randomGenerator = new Random();
		int red = randomGenerator.nextInt(256);
		int green = randomGenerator.nextInt(256);
		int blue = randomGenerator.nextInt(256);
		randomColor = new Color(red,green,blue);
	}

}