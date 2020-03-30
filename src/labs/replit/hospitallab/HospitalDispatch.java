package labs.replit.hospitallab;
/**
 * Add your heading here
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
     *
     */
    public HospitalDispatch() {
        areaHospitals = new HashMap<>();
    }

    /**
     *
     */
    public boolean addHospital(Hospital newHospital) {
        return areaHospitals.put(newHospital, new TreeSet<>()) == null;
    }

    /**
     *
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
     *
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
     *
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

    /**
     *
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
            else
                break;

        }

        return null;
    }

    /**
     *
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
}