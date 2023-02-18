package peaksoft.service;

import peaksoft.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor save(Doctor newDoctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    void deleteDoctor(Long id);
    void update( Long id,Doctor newDoctor);
}
