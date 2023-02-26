package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.exceptions.ApiException;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DepartmentService;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    @Autowired

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, HospitalRepository hospitalRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Department save(Long hospitalId, Department newDepartment) {
        try {
            for (Department d : departmentRepository.getAllDepartments(hospitalId)) {
                if (d.getName().equals(newDepartment.getName())) {
                    throw new ApiException("Department okshosh at menen saktalbash kerek");
                }
            }

            Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
            newDepartment.setHospital(hospital);
            return departmentRepository.save(newDepartment);
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Department> getAllDepartments(Long id) {
        return departmentRepository.getAllDepartments(id);
    }



    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.getDepartmentById(id);

    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.getDepartmentById(id);
        List<Appointment> appointments = department.getHospital().getAppointments();
        List<Appointment> appointmentList = appointments.stream().filter(s -> s.getDepartment().getId().equals(id)).toList();
        appointmentList.forEach(s -> appointmentRepository.deleteAppointment(s.getId()));
        List<Department> departments = department.getHospital().getDepartments();
        departments.removeIf(s -> s.getId().equals(id));
       departmentRepository.deleteDepartment(id);

    }

    @Override
    public void update(Long id, Department department) {
//        Department department1 = departmentRepository.getDepartmentById(id);
//        for (Department department2: departmentRepository.getAllDepartments(department1.getHospital().getId())) {
//            List<Department> departments = departmentRepository.getAllDepartments(department1.getHospital().getId());
//            departments.remove(department1);
            departmentRepository.update(id, department);

    }
}
