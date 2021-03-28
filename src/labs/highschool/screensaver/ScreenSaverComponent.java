package labs.highschool.screensaver;
/**
 * The Screen Saver Component java class is the JComponent extender
 * that produces, manages and generates the circles based on construction
 * parameters and random color and speed shifts.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 1/31/2020
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.geom.Ellipse2D;


public class ScreenSaverComponent extends JComponent
{
    // Starting color for drawing
    private static final Color STARTING_COLOR = Color.magenta;

    // Min and max values for translations of x and y
    private static final int MIN_CHANGE = 5;
    private static final int MAX_CHANGE = 50;

    private static final double maxAlpha = 1;
    private static final double minAlpha = 0.5;

    private Color color;
    private int queueLength;

    private int circleDiameter;
    private int xDirectionVector;
    private int yDirectionVector;

    private int dx;
    private int dy;
    private Queue<Circle> circleQueue;
    private Point previousPoint;

    /**
     * The Screen saver component constructor initializes all the instant variable fields
     * @param max maximum number of circles on the screen at once
     * @param diam diameter of circle
     * @param chgX initial x translation
     * @param chgY initial y translation
     */
    public ScreenSaverComponent(int max, int diam, int chgX, int chgY)
    {
        queueLength = max;
        circleDiameter = diam;
        dx = chgX;
        dy = chgY;
        xDirectionVector = 1;
        yDirectionVector = 1;
        previousPoint = null;
        circleQueue = new LinkedList<>();

    }

    /** Add a new circle to be drawn and then draw all circles and black background
     *  @param g the Graphics object for drawing in this component
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D gr2 = (Graphics2D) g;

        super.paintComponent(g);
        gr2.setColor(Color.BLACK);
        gr2.fillRect(0, 0, getWidth(), getHeight());

        addCircle();
        drawAll(gr2);
    }

    /**
     * Draws all the shape objects in the circle queue
     * @param gr graphics 2D object from paint component
     */
    private void drawAll(Graphics2D gr)
    {

        for(Circle tempCircle : circleQueue)
        {
            Point upperLeftPoint = tempCircle.getUpperLeft();
            Ellipse2D.Double ellipseCircle = new Ellipse2D.Double(
                    upperLeftPoint.getX(),
                    upperLeftPoint.getY(),
                    circleDiameter,
                    circleDiameter);

            (gr).setColor(tempCircle.getColor());
            (gr).fill(ellipseCircle);
        }

    }


    /**
     * The addCircle method is constantly called by the paint component
     * run loop
     */
    private void addCircle()
    {
        int maxWidth = getWidth();
        int maxHeight = getHeight();

        if (previousPoint == null)
        {
            previousPoint = new Point((maxWidth + circleDiameter) / 2,
                    (maxHeight + circleDiameter) / 2);
            color = STARTING_COLOR;
            circleQueue.add(new Circle(previousPoint, color));
        }
        else
        {

            if (circleQueue.size() >= queueLength)
            {
                circleQueue.poll();
            }

            if ((int) previousPoint.getX() + dx * xDirectionVector > getWidth() ||
                    (int) previousPoint.getX() + dx * xDirectionVector < 0)
            {
                xDirectionVector = -1 * xDirectionVector;
                dx = (int) (Math.random() * (MAX_CHANGE - MIN_CHANGE + 1)) + MIN_CHANGE;
                color = new Color(
                        (float)Math.random(),
                        (float)Math.random(),
                        (float)Math.random(),
                        (float) (Math.random() * (maxAlpha - minAlpha) + minAlpha)
                );
            }

            if ((int) previousPoint.getY() + dy * yDirectionVector > getHeight() ||
                    (int) previousPoint.getY() + dy * yDirectionVector < 0)
            {
                yDirectionVector = -1 * yDirectionVector;
                dy = (int) (Math.random() * (MAX_CHANGE - MIN_CHANGE + 1)) + MIN_CHANGE;
                color = new Color(
                        (float) Math.random(),
                        (float) Math.random(),
                        (float) Math.random(),
                        (float) (Math.random() * (maxAlpha - minAlpha) + minAlpha)
                );
            }

            previousPoint = new Point((int) previousPoint.getX() + dx * xDirectionVector,
                    (int) previousPoint.getY() + dy * yDirectionVector);

            circleQueue.add(new Circle(previousPoint, color));
        }

    }
}
