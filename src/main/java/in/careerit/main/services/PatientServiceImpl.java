package in.careerit.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.careerit.main.entities.Patient;
import in.careerit.main.entities.User;
import in.careerit.main.repositories.PatientRepository;
import in.careerit.main.repositories.UserRepository;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient addPatient(Patient patient) {
		
		if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
		    throw new RuntimeException("Patient already exists with this email");
		}
		
		if (userRepository.findByUsername(patient.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }
		
		User user = new User();
        user.setUsername(patient.getEmail());   
        user.setPassword(patient.getPassword());
        user.setRole("PATIENT");
        
        userRepository.save(user);
        
        patient.setUser(user);
        
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
		existingPatient.setAge(patient.getAge());       
	    existingPatient.setGender(patient.getGender()); 
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
