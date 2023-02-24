package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;

import java.util.List;
@Repository
public interface DoctorRepository {
    Doctor save(Doctor newDoctor);
    List<Doctor> getAllDoctors(Long id);
    Doctor getDoctorById(Long id);
    void deleteDoctor(Long id);
    void update( Long id,Doctor newDoctor);
    void addConstraint();

}
