package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepository;

import java.util.List;
@Repository
@Transactional
public class HospitalRepositoryImpl implements HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void save(Hospital newHospital) {
        entityManager.persist(newHospital);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return entityManager.createQuery("select h from Hospital h", Hospital.class).getResultList();
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return entityManager.find(Hospital.class,id);
    }

    @Override
    public void deleteHospital(Long id) {
        Hospital hospital = entityManager.find(Hospital.class, id);
        entityManager.remove(hospital);

    }

    @Override
    public void update(Long id, Hospital newHospital) {
        Hospital hospital = entityManager.find(Hospital.class, id);
        hospital.setName(newHospital.getName());
        hospital.setAddress(newHospital.getAddress());
        hospital.setImage(newHospital.getImage());


    }
}
