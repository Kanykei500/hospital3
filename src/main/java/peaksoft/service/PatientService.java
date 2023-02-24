package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Patient;

import java.util.List;
@Service
public interface PatientService {
    Patient save(Long hospitalId, Patient newPatient);
    List<Patient> getAllPatients(Long id);
    Patient getPatientById(Long id);
    void deletePatient(Long id);
    void update( Long id,Patient newPatient);

}
