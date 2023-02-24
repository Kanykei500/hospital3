package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DoctorService;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;
    @Autowired

    public DoctorServiceImpl(DoctorRepository doctorRepository, DepartmentRepository departmentRepository, HospitalRepository hospitalRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Doctor save(Long hospitalId,Doctor newDoctor) {
        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
        Doctor doctor = new Doctor();
        doctor.setId(newDoctor.getId());
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setEmail(newDoctor.getEmail());
        doctor.setPosition(newDoctor.getPosition());
        doctor.setHospital(hospital);
        for (Long aLong : newDoctor.getDepartmentId()) {
            doctor.addDepartment(departmentRepository.getDepartmentById(aLong));}
        return doctorRepository.save(doctor);


    }

    @Override
    public List<Doctor> getAllDoctors(Long id) {
        return doctorRepository.getAllDoctors(id);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.getDoctorById(id);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.getDoctorById(id);
        List<Appointment> appointments = doctor.getAppointments();
        List<Appointment> appointmentList = appointments.stream().filter(s -> s.getDoctor().getId().equals(id)).toList();
        appointmentList.forEach(s -> appointmentRepository.deleteAppointment(s.getId()));
        List<Doctor> doctors = doctor.getHospital().getDoctors();
        doctors.removeIf(d->d.getId().equals(id));
        doctorRepository.deleteDoctor(id);

    }

    @Override
    public void update(Long id, Doctor newDoctor) {
        doctorRepository.update(id, newDoctor);

    }

    @Override
    public void addConstraint() {
        doctorRepository.addConstraint();
    }


}
