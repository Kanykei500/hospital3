package peaksoft.service;

import peaksoft.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment save(Appointment newAppointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long id);
    void update( Long id,Appointment newAppointment);
}
