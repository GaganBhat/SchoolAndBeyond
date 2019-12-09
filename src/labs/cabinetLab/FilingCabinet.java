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
	}	

	public void add(Student stu) 
	{		
	}
	
	public boolean contains(Student stu) 
	{
		return true;
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