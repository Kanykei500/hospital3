package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.*;
import peaksoft.repository.AppointmentRepository;

import java.util.List;
@Repository
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public AppointmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Appointment save(Appointment newAppointment) {
         entityManager.persist(newAppointment);
         return newAppointment;
    }

    @Override
    public List<Appointment> getAllAppointments(Long hospitalId) {
        return entityManager.createQuery("select a from Hospital  h  join h.appointments a where h.id=:id",Appointment.class)
                .setParameter("id",hospitalId)
                .getResultList();

    }
    @Override
    public Appointment getAppointmentById(Long id) {
       return entityManager.find(Appointment.class,id);
    }

    @Override
    public void deleteAppointment(Long id) {
        List<Hospital> hospitals = entityManager.createQuery("select h from Hospital h", Hospital.class).getResultList();
        hospitals.forEach(h-> h.getAppointments().removeIf(a->a.getId().equals(id)));
        entityManager.remove(entityManager.find(Appointment.class,id));
    }

    @Override
    public void update(Long id, Appointment newAppointment) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        appointment.setDate(newAppointment.getDate());
        appointment.setPatient(entityManager.find(Patient.class,newAppointment.getPatientId()));
        appointment.setDoctor(entityManager.find(Doctor.class,newAppointment.getPatientId()));
        appointment.setDepartment(entityManager.find(Department.class,newAppointment.getPatientId()));



    }
}
