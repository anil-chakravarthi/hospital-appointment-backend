package in.careerit.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.careerit.main.entities.Patient;
import in.careerit.main.repositories.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public Patient updatePatientById(Long patientId, Patient patient) {
		Patient existingPatient = patientRepository.findById(patientId).orElse(null);
		if(existingPatient == null) {
			return null;
		}
		existingPatient.setName(patient.getName());
		existingPatient.setEmail(patient.getEmail());
		existingPatient.setMobile(patient.getMobile());
		
		return patientRepository.save(existingPatient);
	}

	@Override
	public boolean removePatient(Long patientId) {
		if(!patientRepository.existsById(patientId)) {
			return false;
		}
		patientRepository.deleteById(patientId);
		return true;
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Patient getPatientById(Long patientId) {
		return patientRepository.findById(patientId).orElse(null);
	}

	@Override
	public List<Patient> searchPatientById(Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElse(null);
        return patient == null ? List.of() : List.of(patient);
	}
	
}
