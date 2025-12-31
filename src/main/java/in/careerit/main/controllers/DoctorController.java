package in.careerit.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.careerit.main.entities.Doctor;
import in.careerit.main.services.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
    private DoctorService doctorService;
	
	@PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.updateDoctorById(id, doctor);
        if (updatedDoctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedDoctor);
    }
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
	        boolean deleted = doctorService.removeDoctor(id);
	        if (!deleted) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	 
	 @GetMapping
	    public ResponseEntity<List<Doctor>> getAllDoctors() {
	        return ResponseEntity.ok(doctorService.getAllDoctors());
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
	        Doctor doctor = doctorService.getDoctorById(id);
	        if (doctor == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return ResponseEntity.ok(doctor);
	    }

}
