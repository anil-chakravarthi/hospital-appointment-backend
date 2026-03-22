package in.careerit.main.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.careerit.main.dto.AppointmentResponseDTO;
import in.careerit.main.entities.Appointment;
import in.careerit.main.repositories.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);

        if (appointment == null) return null;

        return mapToDTO(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AppointmentResponseDTO mapToDTO(Appointment appointment) {

        AppointmentResponseDTO dto = new AppointmentResponseDTO();

        dto.setAppointmentId(appointment.getAppointmentId());

        dto.setPatientId(appointment.getPatient().getPatientId());
        dto.setPatientName(appointment.getPatient().getName());
        dto.setPatientAge(appointment.getPatient().getAge());       
        dto.setPatientGender(appointment.getPatient().getGender()); 

        dto.setDoctorId(appointment.getDoctor().getDoctorId());
        dto.setDoctorName(appointment.getDoctor().getName());

        dto.setAppointmentDate(appointment.getAppointmentDate());

        return dto;
    }

    @Override
    public Appointment updateAppointmentById(Long appointmentId, Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointmentId).orElse(null);

        if (existingAppointment == null) {
            return null;
        }

        existingAppointment.setPatient(appointment.getPatient());
        existingAppointment.setDoctor(appointment.getDoctor());
        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());

        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public boolean removeAppointment(Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            return false;
        }
        appointmentRepository.deleteById(appointmentId);
        return true;
    }
}