package in.careerit.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        appointmentService.bookAppointment(appointment);

        //Get latest DTO response
        List<AppointmentResponseDTO> list = appointmentService.getAllAppointments();
        return new ResponseEntity<>(list.get(list.size() - 1), HttpStatus.CREATED);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id,
            @RequestBody AppointmentRequestDTO dto) {

        Patient patient = patientService.getPatientById(dto.getPatientId());
        Doctor doctor = doctorService.getDoctorById(dto.getDoctorId());

        if (patient == null || doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dto.getAppointmentDate());

        Appointment updated = appointmentService.updateAppointmentById(id, appointment);

        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        AppointmentResponseDTO response = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(response);
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
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {

        AppointmentResponseDTO dto = appointmentService.getAppointmentById(id);

        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(dto);
    }
}