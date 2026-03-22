package in.careerit.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.careerit.main.entities.Doctor;
import in.careerit.main.entities.User;
import in.careerit.main.repositories.DoctorRepository;
import in.careerit.main.repositories.UserRepository;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public Doctor addDoctor(Doctor doctor) {
		

		    if (doctor.getEmail() == null || doctor.getPassword() == null) {
		        throw new RuntimeException("Email and Password are required");
		    }

		    if (userRepository.findByUsername(doctor.getEmail()).isPresent()) {
		        throw new RuntimeException("User already exists");
		    }

		    User user = new User();
		    user.setUsername(doctor.getEmail());
		    user.setPassword(doctor.getPassword());
		    user.setRole("DOCTOR");

		    userRepository.save(user);

		    doctor.setUser(user);

		    return doctorRepository.save(doctor);
		}


	@Override
	public Doctor updateDoctorById(Long doctorId, Doctor doctor) {
		 Doctor existingDoctor = doctorRepository.findById(doctorId).orElse(null);
		 if(existingDoctor == null) {
			 return null;
		 }
		 existingDoctor.setName(doctor.getName());
		 existingDoctor.setSpecialization(doctor.getSpecialization());
		 
		 return doctorRepository.save(existingDoctor);
	}

	@Override
	public boolean removeDoctor(Long doctorId) {
		if (!doctorRepository.existsById(doctorId)) {
            return false;
        }
        doctorRepository.deleteById(doctorId);
        return true;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public Doctor getDoctorById(Long doctorId) {
		return doctorRepository.findById(doctorId).orElse(null);
	}

}
