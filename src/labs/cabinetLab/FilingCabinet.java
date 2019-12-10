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

		DoubleNode<Student> temp = cabinet[getDrawer(stu)];

		DoubleNode<Student> newNode = new DoubleNode<>(stu, null,null);;

		if(temp == null)
			cabinet[getDrawer(stu)] = newNode;
		else if(temp.getValue().compareTo(stu) >= 0){
			newNode.setNext(temp);
			newNode.getNext().setPrevious(newNode);
			cabinet[getDrawer(stu)] = newNode;
		} else {
			while (temp.getNext() != null && temp.getNext().getValue().compareTo(stu) < 0)
				temp = temp.getNext();

			newNode.setNext(temp.getNext());
			if(temp.getNext() != null)
				newNode.getNext().setPrevious(newNode);
			temp.setNext(newNode);
			newNode.setPrevious(temp);
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