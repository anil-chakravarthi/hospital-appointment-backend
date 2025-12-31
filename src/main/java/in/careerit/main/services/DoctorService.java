package in.careerit.main.services;

import java.util.List;

import in.careerit.main.entities.Doctor;

public interface DoctorService {
	Doctor addDoctor(Doctor doctor);
    Doctor updateDoctorById(Long doctorId, Doctor doctor);
    boolean removeDoctor(Long doctorId);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long doctorId);
}
