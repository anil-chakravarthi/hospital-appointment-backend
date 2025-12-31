package in.careerit.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.careerit.main.entities.Doctor;
import in.careerit.main.repositories.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public Doctor addDoctor(Doctor doctor) {
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
