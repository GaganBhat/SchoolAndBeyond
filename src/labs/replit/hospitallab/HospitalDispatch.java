package labs.replit.hospitallab;
/**
 * The HospitalDispatcher class manages the dispatching of
 * new patients as well as discharging of already existing
 * patients based on closest hospital location first. It uses
 * the Hospital and Patient classes to represent and
 * calculate the requirements needed and decides which hospital
 * to put the patient in accordingly.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 3/31/2020
 */
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

public class HospitalDispatch {
    /**
     * mapping of hospitals to their set of patients
     */
    private HashMap<Hospital, TreeSet<Patient>> areaHospitals;

    /**
     * Initializes the areaHospitals HashMap to empty
     */
    public HospitalDispatch() {
        areaHospitals = new HashMap<>();
    }

    /**
     * Adds the hospital object to map with no patients.
     * @param newHospital Hospital instance to add
     * @return true if hospital did not exist before, false if it did
     */
    public boolean addHospital(Hospital newHospital) {
        return areaHospitals.put(newHospital, new TreeSet<>()) == null;
    }

    /**
     * Admits a patient based on requirements and availabilities.
     * Admits to closest hospital to the patient (if possible).
     * @param newPatient patient to admit to a hospital
     * @return hospital where the patient was admitted, null if patient was not admitted
     */
    public Hospital admitPatient(Patient newPatient) {
        Hospital admitHospital = findHospital(newPatient);
        if(admitHospital != null) {
            if(newPatient.needsBed())
                admitHospital.fillBed();
            if(newPatient.needsVentilator())
                admitHospital.useVentilator();
            areaHospitals.get(admitHospital).add(newPatient);
        }

        return admitHospital;
    }

    /**
     * Discharges the patient from their existing hospital and frees
     * resources for other patients.
     * @param patient patient to discharge
     * @return true if patient was successfully discharged,
     *          false if patient was not found
     */
    public boolean dischargePatient(Patient patient) {

        for (Entry<Hospital, TreeSet<Patient>> entry : areaHospitals.entrySet()) {
            Hospital hospital = entry.getKey();
            TreeSet<Patient> patients = entry.getValue();
            if(patients.contains(patient)){
                patients.remove(patient);
                if(patient.needsBed())
                    hospital.emptyBed();
                if(patient.needsVentilator())
                    hospital.returnVentilator();
                return true;
            }

        }

        return false;
    }


    /**
     * Helper method that finds the hospital for
     * the patient to be admitted to based on available resources.
     * @param pat patient to check hospital resources and location against
     * @return hospital that the patient can be successfully admitted to.
     */
    private Hospital findHospital(Patient pat) {
        for (Entry<Hospital, TreeSet<Patient>> entry : areaHospitals.entrySet()) {
            Hospital hospital = entry.getKey();
            if(hospital.equals(new Hospital(pat.getClosestHospital())))
                if(hasResources(pat, hospital))
                    return hospital;
                else
                    break;

        }

        for (Entry<Hospital, TreeSet<Patient>> entry : areaHospitals.entrySet()) {
            Hospital hospital = entry.getKey();
            if(hasResources(pat, hospital))
                return hospital;
        }

        return null;
    }

    /**
     * Checks if the given patient can be admitted to the given
     * hospital based on the resources needed and available beds
     * and ventilators the hospital has.
     * @param patient patient to check resources for
     * @param hospital hospital to make sure has the availability to admit
     * @return true if the hospital has resources to admit patient, false otherwise.
     */
    private boolean hasResources(Patient patient, Hospital hospital) {
        boolean bedCheck = false;
        boolean ventilatorCheck = false;

        if(patient.needsBed()) {
            if (hospital.hasBedAvailable())
                bedCheck = true;
        }
        else
            bedCheck = true;

        if(patient.needsVentilator()) {
            if (hospital.hasVentilatorAvailable())
                ventilatorCheck = true;
        }
        else
            ventilatorCheck = true;

        return bedCheck && ventilatorCheck;
    }

    /**
     * Returns the number of hospitals, the available resources and
     * the names of patients in the hospital in a clear string manner.
     * @return the string with the above information built by string builder.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Number of mappings: ").append(areaHospitals.size()).append("\n");

        for (Entry<Hospital, TreeSet<Patient>> entry : areaHospitals.entrySet()) {
            Hospital hospital = entry.getKey();
            TreeSet<Patient> patients = entry.getValue();
            sb.append(hospital).append(" => ");
            sb.append(patients).append("\n");
        }

        return sb.toString();
    }

}