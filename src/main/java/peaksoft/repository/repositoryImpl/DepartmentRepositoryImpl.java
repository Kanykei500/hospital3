package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.exceptions.ApiException;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.repository.DepartmentRepository;

import java.util.List;

@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public DepartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Department save(Department newDepartment) {
       try {
           entityManager.persist(newDepartment);
           return newDepartment;
       }catch (ApiException e){
           System.out.println(e.getMessage());
       }


        return null;
    }

    @Override
    public List<Department> getAllDepartments(Long id) {
        return entityManager.createQuery("select d from Department d join d.hospital h where h.id=:id",Department.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return entityManager.find(Department.class,id);
    }

    @Override
    public void deleteDepartment(Long id) {
        entityManager.createQuery("delete from Department d where d.id=:id").setParameter("id",id).executeUpdate();

    }

    @Override
    public void update(Long id, Department department) {
        Department department1 = entityManager.find(Department.class, id);
        department1.setName(department.getName());

    }


    }

