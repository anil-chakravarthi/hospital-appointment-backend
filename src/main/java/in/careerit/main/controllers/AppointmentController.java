package in.careerit.main.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import in.careerit.main.dto.AppointmentRequestDTO;
import in.careerit.main.dto.AppointmentResponseDTO;
import in.careerit.main.entities.Appointment;
import in.careerit.main.entities.Doctor;
import in.careerit.main.entities.Patient;
import in.careerit.main.services.AppointmentService;
import in.careerit.main.services.DoctorService;
import in.careerit.main.services.PatientService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
    private AppointmentService appointmentService;
	
	 @Autowired
	    private PatientService patientService;

	 @Autowired
	 	private DoctorService doctorService;
	 
	 @PostMapping
	    public ResponseEntity<AppointmentResponseDTO> bookAppointment(@RequestBody AppointmentRequestDTO dto) {

	        Patient patient = patientService.getPatientById(dto.getPatientId());
	        Doctor doctor = doctorService.getDoctorById(dto.getDoctorId());
	        if (patient == null || doctor == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        Appointment appointment = new Appointment();
	        appointment.setPatient(patient);
	        appointment.setDoctor(doctor);
	        appointment.setAppointmentDate(dto.getAppointmentDate());
	        Appointment savedAppointment = appointmentService.bookAppointment(appointment);
	        return new ResponseEntity<>(mapToResponseDTO(savedAppointment), HttpStatus.CREATED);
	    }
	 
	 @PutMapping("/{id}")
	    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id,@RequestBody AppointmentRequestDTO dto) {

	        Patient patient = patientService.getPatientById(dto.getPatientId());
	        Doctor doctor = doctorService.getDoctorById(dto.getDoctorId());

	        if (patient == null || doctor == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        Appointment appointment = new Appointment();
	        appointment.setPatient(patient);
	        appointment.setDoctor(doctor);
	        appointment.setAppointmentDate(dto.getAppointmentDate());

	        Appointment updatedAppointment = appointmentService.updateAppointmentById(id, appointment);
	        if (updatedAppointment == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return ResponseEntity.ok(mapToResponseDTO(updatedAppointment));
	    }
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
	        boolean deleted = appointmentService.removeAppointment(id);
	        if (!deleted) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	 
	 @GetMapping
	    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
	        List<AppointmentResponseDTO> response = appointmentService.getAllAppointments().stream().map(this::mapToResponseDTO).collect(Collectors.toList());
	        return ResponseEntity.ok(response);
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {

	        Appointment appointment = appointmentService.getAppointmentById(id);
	        if (appointment == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        return ResponseEntity.ok(mapToResponseDTO(appointment));
	    }

	    private AppointmentResponseDTO mapToResponseDTO(Appointment appointment) {
	        AppointmentResponseDTO dto = new AppointmentResponseDTO();
	        dto.setAppointmentId(appointment.getAppointmentId());
	        dto.setPatientId(appointment.getPatient().getPatientId());
	        dto.setPatientName(appointment.getPatient().getName());
	        dto.setDoctorId(appointment.getDoctor().getDoctorId());
	        dto.setDoctorName(appointment.getDoctor().getName());
	        dto.setAppointmentDate(appointment.getAppointmentDate());
	        return dto;
	    }
	 
}
