package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.List;
@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    @Autowired

    public PatientServiceImpl(HospitalRepository hospitalRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Patient save(Long hospitalId,Patient newPatient) {
        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
        newPatient.setHospital(hospital);
        return patientRepository.save(newPatient);
    }

    @Override
    public List<Patient> getAllPatients(Long id) {
        return patientRepository.getAllPatients(id);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.getPatientById(id);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.getPatientById(id);
        List<Appointment> appointments = patient.getAppointments();
        List<Appointment> appointmentList = appointments.stream().filter(s -> s.getPatient().getId().equals(id)).toList();
        appointmentList.forEach(s -> appointmentRepository.deleteAppointment(s.getId()));
        List<Patient> patients = patient.getHospital().getPatients();
        patients.removeIf(s -> s.getId().equals(id));
        patientRepository.deletePatient(id);

    }
    @Override
    public void update(Long id, Patient newPatient) {
        patientRepository.update(id, newPatient);

    }


}
