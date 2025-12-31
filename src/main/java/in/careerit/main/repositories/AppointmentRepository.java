package in.careerit.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.careerit.main.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
