package peaksoft.repository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Appointment;

import java.util.List;
@Repository

public interface AppointmentRepository {
    Appointment save(Appointment newAppointment);
    List<Appointment> getAllAppointments(Long id);
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long id);
    void update( Long id,Appointment newAppointment);
}
