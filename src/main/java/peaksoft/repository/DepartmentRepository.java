package peaksoft.repository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;

import java.util.List;
@Repository
@Transactional
public interface DepartmentRepository {
    Department save(Department newDepartment);
    List<Department> getAllDepartments(Long id);
    Department getDepartmentById(Long id);
    void deleteDepartment(Long id);
    void update( Long id,Department department);

}
