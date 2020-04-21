package labs.replit.patienttriagelab;
/**
 * This method returns the list of patients on the triage list
 * in the order of priority.
 *
 * POST CONDITION:
 * All patients in patients list at the start of the method are still in
 * the patientList AND the heap still maintains the order.
 * Note: While the heap order is maintained, the locations of patients in the
 * heap might change after this method is completed.
 *
 * @return the list of patients on the triage list in the order of priority.
 */


import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

/**
 * The PatientTriage class manages the order patients are served
 * at a hospital when resources are limited. The class maintains
 * a Priority Queue of Patient objects.
 *
 * @author Jonathan Yee, Tracy Ishman, Ann Horton
 * Date: 4/19/2020
 */
public class PatientTriage {
	/* List of patient waiting to be processed*/
	private Queue<Patient> patientList;

	/**
	 *
	 */
	public PatientTriage() {
		patientList = new PriorityQueue<>();
	}

	/**
	 *
	 */
	public void addPatient(Patient p) {
		patientList.add(p);
	}

	/**
	 *
	 */
	public Patient processPatient() {
		return patientList.poll();
	}

	/**
	 *
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Patient> copy = new PriorityQueue<>(patientList);
		while (!copy.isEmpty())
			sb.append(copy.poll()).append("\n");

		return sb.toString();
	}

	/**
	 * This method returns the heap of Patients in its raw form.
	 *
	 * @return the heap of Patients in its raw form
	 */
	public Queue<Patient> getPQueue() {
		return patientList;
	}

}