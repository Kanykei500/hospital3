package peaksoft.repository;

import peaksoft.model.Appointment;

import java.util.List;

public interface AppointmentRepository {
    Appointment save(Appointment newAppointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long id);
    void update( Long id,Appointment newAppointment);
}
