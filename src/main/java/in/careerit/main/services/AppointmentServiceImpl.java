package in.careerit.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.careerit.main.entities.Appointment;
import in.careerit.main.repositories.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public Appointment bookAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
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

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment getAppointmentById(Long appointmentId) {
		return appointmentRepository.findById(appointmentId).orElse(null);
	}
	
	
}
