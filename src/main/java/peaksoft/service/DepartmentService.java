package peaksoft.service;

import peaksoft.model.Department;

import java.util.List;

public interface DepartmentService {
    Department save(Long hospitalId, Department newDepartment);
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    void deleteDepartment(Long id);
    void update( Long id,Department newDepartment);
}
