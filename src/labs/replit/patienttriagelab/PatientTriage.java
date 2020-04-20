package labs.replit.patienttriagelab;
/**
 * Your heading goes here...
 * Remember your javadocs.
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
		for(Patient patient : patientList)
			sb.append(patient).append("\n");

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