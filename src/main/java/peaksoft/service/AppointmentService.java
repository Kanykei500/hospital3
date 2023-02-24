package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;

import java.util.List;
@Service
public interface AppointmentService {
    Appointment save(Long hospitalId,Appointment appointment);
    List<Appointment> getAllAppointments(Long id);
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long hospitalId,Long id);
    void update( Long id,Appointment newAppointment);
}
