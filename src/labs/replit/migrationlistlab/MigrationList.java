package labs.replit.migrationlistlab;
/**
 *  Your heading goes here
 */


public class MigrationList
{
  /* Links to the left end, right end, and leader nodes of list of migrating birds */
  private Node<Bird> leftEnd;
  private Node<Bird> rightEnd;
  private Node<Bird> leaderNode;
  
  /* the number of birds in the list */
  private int numBirds;
  
  /* boolean to track if the leader should fall back to left or right end */
  private boolean fallBackLeft;

  private boolean addBirdToLeft;
  
  /**
   * Constructs an echelon of birds ("V" formation)
   */
  public MigrationList()
  {
    leftEnd = null;
    rightEnd = null;
    leaderNode = null;
    numBirds = 0;
    fallBackLeft = true;
    addBirdToLeft = true;
  }
  
  /**
   * Retrieves the number of birds in the flock
   * @return bird count 
   */
  public int size()
  {
    return numBirds;
  }
  
  /**
   * Adds the given bird to alternating ends of the formation
   * @param newBird the bird we're adding  
   */
  public void addBird(Bird newBird)
  {
      if(leaderNode == null) {
          newBird.makeLeader();
          leftEnd = rightEnd = leaderNode = new Node<>(newBird, null, null);
          numBirds++;
      } else if (addBirdToLeft)
          addLeftEnd(newBird);
      else
          addRightEnd(newBird);
  }
  
  /**
   * Moves the current leader to alternating ends of the formation
   * @return the new leader of the echelon; null if empty
   */
  public Bird leaderFallBack()
  {
      if(leaderNode == null)
          return null;
      removeNode(leaderNode);
      leaderNode.getValue().makeFollower();
      if(fallBackLeft)
          addLeftEnd(leaderNode.getValue());
      else
          addRightEnd(leaderNode.getValue());

      leaderNode = locateMiddleNode();
      leaderNode.getValue().makeLeader();

      fallBackLeft = !fallBackLeft;

      return leaderNode.getValue();
  }
  
  /**
   * Remove the weakest bird in the flock
   * @return weakest bird that was removed; null if empty
   */
  public Bird removeWeakestBird()
  {
      if(leaderNode == null)
          return null;

      Node<Bird> weakestNode = leftEnd;
      Node<Bird> tmpNode = leftEnd;
      while (!(tmpNode.getNext() == null)){
          if(tmpNode.getValue().getStrengthLevel()
                  < weakestNode.getValue().getStrengthLevel())
              weakestNode = tmpNode;
          tmpNode = tmpNode.getNext();
      }

      removeNode(weakestNode);

      leaderNode.getValue().makeFollower();
      leaderNode = locateMiddleNode();
      leaderNode.getValue().makeLeader();


      Bird weakestBird = weakestNode.getValue();
      weakestBird.makeFollower();
      return weakestBird;
  }
  
  /** 
   * Splits the flock in half by alternating removing from the ends.
   * Returns a new flock comprised of birds removed. 
   * If odd size, keeps the larger amount
   * @return flock of removed birds
   */
  public MigrationList splitFormation()
  {
      fallBackLeft = true;
      MigrationList newMigration = new MigrationList();
      if(leftEnd == null)
          return newMigration;
      int i = numBirds / 2;
      for (int k = 0; k < i; k++) {
          if (fallBackLeft)
              newMigration.addBird(removeLeftEnd());
          else
              newMigration.addBird(removeRightEnd());
          fallBackLeft = !fallBackLeft;
      }

      return newMigration;
  }
  
  /**
   * Retrieves list of birds from left end to right end 
   * @return birds from left to right of "V"
   */
  @Override
  public String toString()
  {
  	StringBuilder list = new StringBuilder("[");
  	Node<Bird> travel = leftEnd;
  	while (travel != null)
  	{
  		if (travel != leftEnd)
  			list.append(", ");
  		list.append(travel.getValue());
  		travel = travel.getNext();
  	}
  	list.append("]");
    return list.toString();
  }
    
  /**
   * Retrieves list of birds in reverse order from right end to left end 
   * @return birds from right to left of "V"
   */
  public String reverseString()
  {
  	StringBuilder list = new StringBuilder("[");
  	Node<Bird> travel = rightEnd;
  	while (travel != null)
  	{
  		if (travel != rightEnd)
  			list.append(", ");
  		list.append(travel.getValue());
  		travel = travel.getPrevious();
  	}
  	list.append("]");
    return list.toString();
  }
  
  /**
   * Locates the middle node in the list
   * @return the middle node that should be the leader node
   */
  private Node<Bird> locateMiddleNode()
  {
    Node<Bird> findMiddle = leftEnd;
    int loc = numBirds / 2;
    for (int k = 0; k < loc; k++) 
    {
    	findMiddle = findMiddle.getNext();
    }
    return findMiddle;
  }

  /**
   * Adds a bird to the left end of the "V" formation (first)
   * @param bird the bird to add at left end of the list
   */
  private void addLeftEnd(Bird bird)
  {
      bird.makeFollower();
      leftEnd = new Node<>(bird, leftEnd, null);
      leftEnd.getNext().setPrevious(leftEnd);
      numBirds++;
      addBirdToLeft = !addBirdToLeft;
  }
  
  /**
   * Adds a bird to the right end of the "V" formation (last)
   * @param bird the bird to add at right end of the list
   */
  private void addRightEnd(Bird bird)
  {
      bird.makeFollower();
      rightEnd = new Node<>(bird, null, rightEnd);
      rightEnd.getPrevious().setNext(rightEnd);
      numBirds++;
      addBirdToLeft = !addBirdToLeft;
  }
  
  /** 
   * Removes node from the list
   * @param node a valid node in the list
   */
  private void removeNode(Node<Bird> node)
  {
      Node<Bird> tmpNode = leftEnd;
      while (!tmpNode.equals(node))
          tmpNode = tmpNode.getNext();

      tmpNode.getPrevious().setNext(tmpNode.getNext());
      tmpNode.getNext().setPrevious(tmpNode.getPrevious());
      numBirds--;
  }

  /**
   * Removes bird at the left end of the "V" formation (first)
   * @return bird from left end
   */
  private Bird removeLeftEnd()
  {
      Bird leftBird = leftEnd.getValue();
      leftEnd = leftEnd.getNext();
      leftEnd.setPrevious(null);
      numBirds--;
      return leftBird;
  }

  /**
   * Removes bird at the right end of the "V" formation (last)
   * @return bird from right end
   */
  private Bird removeRightEnd()
  {
      Bird rightBird = rightEnd.getValue();
      rightEnd = rightEnd.getPrevious();
      rightEnd.setNext(null);
      numBirds--;
      return rightBird;
  }
}