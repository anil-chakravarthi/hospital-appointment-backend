package in.careerit.main.services;

import java.util.List;

import in.careerit.main.entities.Patient;

public interface PatientService {
	
	Patient addPatient(Patient patient);
	Patient updatePatientById(Long patientId, Patient patient);
	boolean removePatient(Long patientId);
	List<Patient> getAllPatients();
	Patient getPatientById(Long patientId);
	List<Patient> searchPatientById(Long patientId);
}
