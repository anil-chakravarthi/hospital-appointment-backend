package in.careerit.main.services;

import java.util.List;

import in.careerit.main.entities.Appointment;

public interface AppointmentService {
	Appointment bookAppointment(Appointment appointment);
    Appointment updateAppointmentById(Long appointmentId, Appointment appointment);
    boolean removeAppointment(Long appointmentId);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long appointmentId);
}
