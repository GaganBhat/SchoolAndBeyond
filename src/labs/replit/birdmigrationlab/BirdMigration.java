package labs.replit.birdmigrationlab; /**
 *  Your heading goes here
 */

import java.util.*;

public class BirdMigration
{
  /* a linked list of migrating birds in a "V" formation */
  private LinkedList<Bird> echelon;
  
  /* boolean to track if the leader should fall back to left or right end */
  private boolean fallBackLeft;

  private boolean addBirdLeft;
  
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
    addBirdLeft = true;
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
      if(leader == null) {
          leader = newBird;
          leader.makeLeader();
          echelon.add(leader);
      }
      else if (addBirdLeft){
          echelon.addFirst(newBird);
          addBirdLeft = false;
      } else {
          echelon.addLast(newBird);
          addBirdLeft = true;
      }
  }
  
  /**
   * Moves the current leader to alternating ends of the formation
   * @return the new leader of the echelon; null if empty
   */
  public Bird leaderFallBack()
  {
      if(echelon.isEmpty())
          return null;

      Bird currentBird = null;
      ListIterator<Bird> iter = echelon.listIterator();
      while (iter.hasNext()){
          currentBird = iter.next();
          if(currentBird.equals(leader)){

              iter.remove();
              if(fallBackLeft) {
                  leader = iter.previous();
                  iter.next();
              }
              else {
                  leader = iter.next();
              }

              leader.makeLeader();
              break;
          }
      }

      currentBird.makeFollower();
      if(fallBackLeft)
          echelon.addFirst(currentBird);
      else
          echelon.addLast(currentBird);

      fallBackLeft = !fallBackLeft;
      return leader;
  }
  
  /**
   * Remove the weakest bird in the flock
   * @return weakest bird that was removed; null if empty
   */
  public Bird removeWeakestBird()
  {
      if(echelon.isEmpty())
        return null;
      Bird weakest = new Bird("test", Integer.MAX_VALUE);
      Bird currentBird;
      ListIterator<Bird> iter = echelon.listIterator();
      while (iter.hasNext()){
          currentBird = iter.next();
          if(currentBird.getStrengthLevel() < weakest.getStrengthLevel())
              weakest = currentBird;
      }

      if(weakest.equals(leader)){
          Bird currBird;
          ListIterator<Bird> iterator = echelon.listIterator();
          while (iterator.hasNext()){
              currBird = iterator.next();
              if(currBird.equals(leader)) {
                  iterator.previous();
                  leader = iterator.previous();
                  leader.makeLeader();
                  iterator.next();
                  iterator.next();
                  iterator.remove();
              }
          }
          leader.makeLeader();
          weakest.makeFollower();
      } else {
          echelon.remove(weakest);
      }


      return weakest;
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