package in.careerit.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.careerit.main.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
