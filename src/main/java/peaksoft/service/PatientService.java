package peaksoft.service;

import peaksoft.model.Patient;

import java.util.List;

public interface PatientService {
    Patient save(Patient newPatient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    void deletePatient(Long id);
    void update( Long id,Patient newPatient);
}
