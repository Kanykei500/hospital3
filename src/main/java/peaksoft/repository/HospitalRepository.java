package peaksoft.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;

import java.util.List;
@Repository
public interface HospitalRepository {
    void save(Hospital newHospital);
    List<Hospital> getAllHospitals();
    Hospital getHospitalById(Long id);
    void deleteHospital(Long id);
    void update( Long id,Hospital newHospital);

    List<Hospital> search(String word);
}
