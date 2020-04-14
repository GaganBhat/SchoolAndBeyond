/**
 *  Your heading goes here
 */

import java.util.*;

public class BirdMigration
{
  /* a linked list of migrating birds in a "V" formation */
  private LinkedList<Bird> echelon;
  
  /* boolean to track if the leader should fall back to left or right end */
  private boolean fallBackLeft;
  
  /* track the leader */
  private Bird leader;

  /**
   * Constructs an echelon of birds
   */
  public BirdMigration()
  {
    echelon = new LinkedList<>();
    fallBackLeft = true;
    leader = null;
  }
  
  /**
   * Retrieves the number of birds in the flock
   * @return bird count 
   */
  public int size()
  {
    return echelon.size();
  }
  
  /**
   * Adds the given bird to alternating ends of the formation
   * @param newBird the bird we're adding  
   */
  public void addBird(Bird newBird)
  {
  }
  
  /**
   * Moves the current leader to alternating ends of the formation
   * @return the new leader of the echelon; null if empty
   */
  public Bird leaderFallBack()
  {
    return null;
  }
  
  /**
   * Remove the weakest bird in the flock
   * @return weakest bird that was removed; null if empty
   */
  public Bird removeWeakestBird()
  {
    return null;
 }
  
  /** 
   * Splits the flock in half by alternating removing from the ends.
   * Returns a new flock comprised of birds removed. 
   * If odd size, keeps the larger amount
   * @return flock of removed birds
   */
  public BirdMigration splitFormation()
  {
    return null;
  }
  
  /**
   * Retrieves list of birds from left end to right end 
   * @return birds from left to right of "V"
   */
  @Override
  public String toString()
  {
    return echelon.toString();
  }
}