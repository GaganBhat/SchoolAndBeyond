package labs.maze;

/**
 * The Maze.java class consists of two major methods inherited from AbstractMaze.java
 * that are used by the MazeRunner.java class to create a random maze and to see
 * if it can be solved accordingly through the GridWorld library.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 1/16/2020
 */

import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.*;

public class Maze extends AbstractMaze
{

    private static final double PROBABILITY = .8;
    private static final int NUM_ROWS = 19;
    private static final int NUM_COLUMNS = 19;

    private Location startLocation;
    private Location endLocation;

    private static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    private static final Color WALL_COLOR = Color.DARK_GRAY;
    private static final Color START_COLOR = Color.GREEN;
    private static final Color VISIT_COLOR = Color.MAGENTA;
    private static final Color EXIT_COLOR = Color.RED;

    /**
     * The Solve Maze method overrides the abstract method to solve the randomly generated
     * maze created by the populateMaze() method.
     * @return true if end point was reached; false if stuck
     */
    @Override
    public boolean solveMaze(){
        Deque<Location> locationStacko = new LinkedList<>();
        locationStacko.push(startLocation);
        boolean isFinished = false;
        Location currentPosition = startLocation;
        locationStacko.pop();

        while (!isFinished){
            ArrayList<Location> mazeTrajectory =
                    getGrid().getValidAdjacentLocations(currentPosition);

            for(int i = mazeTrajectory.size() - 1; i >= 0; i--){
                if(getGrid().get(mazeTrajectory.get(i)).equals(WALL_COLOR)
                        || getGrid().get(mazeTrajectory.get(i)).equals(VISIT_COLOR)
                    || getGrid().get(mazeTrajectory.get(i)).equals(START_COLOR))
                    mazeTrajectory.remove(i);
            }

            if(mazeTrajectory.size() > 0){
                currentPosition = mazeTrajectory.get(mazeTrajectory.size() - 1);
                remove(currentPosition);
                add(currentPosition, VISIT_COLOR);
                locationStacko.push(currentPosition);
            } else {
                locationStacko.pop();
                if (!locationStacko.isEmpty())
                    currentPosition = locationStacko.peek();
            }
            if(locationStacko.isEmpty() || currentPosition.equals(endLocation))
                isFinished = true;

        }

        return !locationStacko.isEmpty();

    }


    /**
     * The populateMaze method creates a randomly generated maze with a default background
     * color of white. There may or may not be a path to the end point from the starting
     * point generated.
     */
    @Override
    public void populateMaze()
    {
        Grid<Color> grid = getGrid();
        for (int i = 0; i < NUM_ROWS; i++)
        {
            for (int j = 0; j < NUM_COLUMNS; j++)
                grid.put(new Location(i, j), DEFAULT_BACKGROUND_COLOR);
        }
        //Adds the 2 columns of walls
        for (int i = 0; i < NUM_ROWS; i++)
        {
            Location loc = new Location(i, NUM_COLUMNS - 1);
            grid.put(loc, WALL_COLOR);
            loc = new Location(i, 0);
            grid.put(loc, WALL_COLOR);
        }
        //Adds the 2 rows of walls
        for (int i = 0; i < NUM_COLUMNS; i++)
        {
            Location loc = new Location(NUM_ROWS - 1, i);
            grid.put(loc, WALL_COLOR);
            loc = new Location(0, i);
            grid.put(loc, WALL_COLOR);
        }

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                Location loc = new Location(i, j);
                if (Math.random() > PROBABILITY)
                    grid.put(loc, WALL_COLOR);
            }
        }


        startLocation = new Location((int) (Math.random() * NUM_COLUMNS), 0);
        endLocation = new Location((int) (Math.random() * NUM_COLUMNS), NUM_COLUMNS - 1);

        grid.put(startLocation, START_COLOR);
        grid.put(endLocation, EXIT_COLOR);

    }

}



