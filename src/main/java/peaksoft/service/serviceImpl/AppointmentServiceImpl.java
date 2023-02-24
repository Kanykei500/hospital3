package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.repository.*;
import peaksoft.service.AppointmentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, DepartmentRepository departmentRepository, PatientRepository patientRepository, HospitalRepository hospitalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Appointment save(Long hospitalId, Appointment appointment) {

        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
        Appointment appointment1 = new Appointment();
        appointment1.setId(appointment.getId());
        appointment1.setDate(appointment.getDate());
        appointment1.setPatient(patientRepository.getPatientById(appointment.getPatientId()));
        appointment1.setDoctor(doctorRepository.getDoctorById(appointment.getDoctorId()));
        appointment1.setDepartment(departmentRepository.getDepartmentById(appointment.getDepartmentId()));
        hospital.addAppointment(appointment1);
        return appointmentRepository.save(appointment1);
    }

    @Override
    public List<Appointment> getAllAppointments(Long id) {
        return appointmentRepository.getAllAppointments(id);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.getAppointmentById(id);
    }

    @Override
    public void deleteAppointment(Long hospitalId,Long id) {
        appointmentRepository.deleteAppointment(id);

    }



    @Override
    public void update(Long id, Appointment newAppointment) {
        appointmentRepository.update(id, newAppointment);


    }
}
