package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;
@Service
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    @Autowired

    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public void save(Hospital newHospital) {
      hospitalRepository.save(newHospital);
    }

    @Override
    public List<Hospital> getAllHospitals(String word) {
        if (word != null && !word.trim().isEmpty()){
           return hospitalRepository.search(word);
        }else {
          return    hospitalRepository.getAllHospitals();
        }
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.getHospitalById(id);
    }

    @Override
    public void deleteHospital(Long id) {
        hospitalRepository.deleteHospital(id);

    }

    @Override
    public void update(Long id, Hospital newHospital) {
        hospitalRepository.update(id, newHospital);

    }
}
