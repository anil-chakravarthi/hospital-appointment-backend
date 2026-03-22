package in.careerit.main.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.careerit.main.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	Optional<Patient> findByEmail(String email);
}
