package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;

import java.util.List;
@Repository
public interface PatientRepository {
    Patient save( Patient newPatient);
    List<Patient> getAllPatients(Long id);
    Patient getPatientById(Long id);
    void deletePatient(Long id);
    void update( Long id,Patient newPatient);

}
