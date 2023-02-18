package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;
    private String email;
    @OneToMany(mappedBy = "doctor",cascade = {REFRESH,PERSIST,DETACH,MERGE})
    private List<Appointment>appointments;
    @ManyToMany(cascade = {REFRESH,PERSIST,DETACH,MERGE})
    private List<Department>departments;
    @ManyToOne(cascade = {REFRESH,PERSIST,DETACH,MERGE})
    private Hospital hospital;
}
