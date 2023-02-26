package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.exceptions.ApiException;
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
        try {
            entityManager.persist(newHospital);
        }catch (ApiException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Hospital> getAllHospitals() {
        try {


        return entityManager.createQuery("select h from Hospital h", Hospital.class).getResultList();
    }catch (ApiException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public Hospital getHospitalById(Long id) {
        try {
            return entityManager.find(Hospital.class,id);
        }catch (ApiException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteHospital(Long id) {
        try {
            Hospital hospital = entityManager.find(Hospital.class, id);
            entityManager.remove(hospital);
        }catch (ApiException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void update(Long id, Hospital newHospital) {
       try {
           Hospital hospital = entityManager.find(Hospital.class, id);

           hospital.setName(newHospital.getName());
           hospital.setAddress(newHospital.getAddress());
           hospital.setImage(newHospital.getImage());
       }catch (ApiException e){
           System.out.println(e.getMessage());
       }

    }
}
