package labs.cabinetLab;

public class FilingCabinet
{
	/** Number of filing cabinets (one for each letter A-Z) */
	private static final int NUM_CABINETS = 26;
	
	/** Array of DoubleNode objects for filing cabinet of Students */
	private DoubleNode<Student>[] cabinet;	

	@SuppressWarnings("unchecked")
	public FilingCabinet() 
	{
		cabinet = new DoubleNode[26];
	}

	/**
	 * Inserts a student at the front of the proper drawer.
	 * @param stu student to be added to the proper drawer.
	 */
	public void add(Student stu) 
	{
		DoubleNode<Student> front = cabinet[getDrawer(stu)];
		DoubleNode<Student> temp = front;

		if(temp == null) {
			cabinet[getDrawer(stu)] = new DoubleNode<>(stu, null, null);
			;return;
		}

		while (stu.compareTo(temp.getValue()) > 0 && temp!= null){
			if(temp.getNext() == null)
				break;
			temp = temp.getNext();
		}
		System.out.println("Stopped at = " + temp.getValue() + " Compare to = " + stu.compareTo(temp.getValue()) + " Inserting " + stu);


		if(temp == front) {
			if(stu.compareTo(front.getValue()) >= 0) {
				front.setNext(new DoubleNode<>(stu, null, front));
			}
			else
				cabinet[getDrawer(stu)] = new DoubleNode<>(stu, cabinet[getDrawer(stu)], null);
		}
		else if(temp.getNext() == null) {
			if(stu.compareTo(temp.getValue()) >= 0) {
				temp.setNext(new DoubleNode<>(stu, null, temp));
			}
			else {
				DoubleNode<Student> insertNode = new DoubleNode<>(stu, temp, temp.getPrevious());
				temp.getPrevious().setNext(insertNode);
				temp.setPrevious(insertNode);
			}


		}	
		else{
			DoubleNode<Student> insertNode = new DoubleNode<>(stu, temp, temp.getPrevious());
			temp.setPrevious(insertNode);
			temp.getPrevious().getPrevious().setNext(insertNode);
		}

	}
	
	public boolean contains(Student stu) 
	{
		DoubleNode<Student> frontTemp = cabinet[getDrawer(stu)];
		while (frontTemp != null){
			if(frontTemp.getValue().equals(stu))
				return true;
			frontTemp = frontTemp.getNext();
		}
		return false;
	}
	
	public void remove(Student stu) 
	{
	}

	/** @return filing cabinet as a string with each student on a separate line
	 */
	@Override
	public String toString() 
	{
		StringBuilder buf = new StringBuilder("Filing Cabinet:\n");
		DoubleNode<Student> drawer;
		for (int k = 0; k < cabinet.length; k++) 
		{
			drawer = cabinet[k];
			if (drawer != null)
				buf.append("Drawer " + (char) ('A' + k) + ":  ");
			while (drawer != null) 
			{
				buf.append(drawer.getValue().toString() + "\n");
				drawer = drawer.getNext();
				if (drawer != null) buf.append("           ");
			}
		}
		buf.append("**end**");
		return buf.toString();
	}
	
	/** @return filing cabinet as a string with each student in reverse alphabetical order
	 */
	public String reverseToString() 
	{
		StringBuilder buf = new StringBuilder("Reverse Filing Cabinet:\n");
		DoubleNode<Student> drawer;
		for (int k = cabinet.length-1; k >= 0; k--) 
		{
			drawer = cabinet[k];
			if (drawer != null)
			{
				buf.append("Drawer " + (char) ('A' + k) + ":  ");
			
				// go to end
				while (drawer.getNext() != null)
					drawer = drawer.getNext();
					
				while (drawer != null) 
				{
					buf.append(drawer.getValue().toString() + "\n");
					drawer = drawer.getPrevious();
					if (drawer != null) buf.append("           ");
				}
			}
		}
		buf.append("**end**");
		return buf.toString();
	}
	
	//--------------------------------------------------
	//	Private Methods
	//--------------------------------------------------
	
	/** Determines which drawer could contain stu
	 *   @return index of the drawer to which this student belongs;
	 *          (uses the 'Z' drawer if last name does not begin with a letter)
	 */
	private int getDrawer(Student stu) 
	{
		String lastName = stu.getLastName().toUpperCase();
		char letter = lastName.charAt(0);
		if (letter < 'A' || letter > 'Z') letter = 'Z';
		return letter - 'A';
	}
}