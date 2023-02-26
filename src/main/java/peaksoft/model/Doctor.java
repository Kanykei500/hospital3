package peaksoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.MERGE;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doctor_id_gen")
    @SequenceGenerator(name = "doctor_id_gen",sequenceName = "doctor_id_seq",allocationSize = 1)
    private Long id;
    @NotEmpty(message = "First name should not be null")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty(message = "Last name should not be null")
    @Column(name = "last_name")
    private String lastName;
    @NotEmpty(message = "Position should not be null")
    private String position;
    @Column(name = "email",unique = true)
    @Email(message = "Email should be valid")
    private String email;
    @OneToMany(mappedBy = "doctor",cascade = {REFRESH,PERSIST,DETACH,MERGE})
    private List<Appointment>appointments;
    @ManyToMany(cascade = {REFRESH,PERSIST,DETACH,MERGE})
    private List<Department>departments;
    public void addDepartment(Department department){
        if (departments==null){
            departments=new ArrayList<>();
        }
        departments.add(department);
    }
    @ManyToOne(cascade = {REFRESH,PERSIST,DETACH,MERGE})
    private Hospital hospital;
    @Transient
    private Long hospitalId;
    @Transient
    private List<Long> departmentId = new ArrayList<>();
    public void setDepartment(Department byId) {
        departments.add(byId);
    }

}
