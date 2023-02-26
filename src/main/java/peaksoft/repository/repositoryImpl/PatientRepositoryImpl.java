package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.exceptions.ApiException;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.PatientRepository;

import java.util.List;

@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public PatientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Patient save(Patient newPatient) {
        entityManager.persist(newPatient);
        return newPatient;
    }


    @Override
    public List<Patient> getAllPatients(Long id) {
        return entityManager.createQuery("select p from Patient p join p.hospital h where h.id=:id", Patient.class)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    public Patient getPatientById(Long id) {
        return entityManager.find(Patient.class,id);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        entityManager.remove(patient);

    }

    @Override
    public void update(Long id, Patient newPatient) {
        if (newPatient.getPhoneNumber().startsWith("+996")) {
            Patient patient = entityManager.find(Patient.class, id);
            patient.setFirstName(newPatient.getFirstName());
            patient.setLastName(newPatient.getLastName());
            patient.setEmail(newPatient.getEmail());
            patient.setGender(newPatient.getGender());
            patient.setPhoneNumber(newPatient.getPhoneNumber());

        } else {
            throw new RuntimeException("phone number should be starts +996");

        }


    }}

