package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.DoctorRepository;

import java.util.List;

@Repository
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public DoctorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Doctor save(Doctor newDoctor) {
        entityManager.persist(newDoctor);
        return newDoctor;

    }

    @Override
    public List<Doctor> getAllDoctors(Long id) {
        return entityManager.createQuery("select d from Doctor d join d.hospital h where h.id=:id", Doctor.class).setParameter("id",id).getResultList();

    }

    @Override
    public Doctor getDoctorById(Long id) {
        return entityManager.find(Doctor.class,id);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        entityManager.remove(doctor);

    }

    @Override
    public void update(Long id, Doctor newDoctor) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.setEmail(newDoctor.getEmail());
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setPosition(newDoctor.getPosition());



    }

    @Override
    public void addConstraint() {
        entityManager.createNativeQuery("alter table doctors add unique(email) ", Doctor.class).executeUpdate();
    }


}
