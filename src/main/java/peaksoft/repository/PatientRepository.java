package peaksoft.repository;

import peaksoft.model.Hospital;
import peaksoft.model.Patient;

import java.util.List;

public interface PatientRepository {
    Patient save(Patient newPatient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    void deletePatient(Long id);
    void update( Long id,Patient newPatient);
}
