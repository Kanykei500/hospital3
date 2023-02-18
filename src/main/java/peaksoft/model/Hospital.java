package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
public class Hospital {
    @Id
    @SequenceGenerator(name = "hospital_id_gen",sequenceName = "hospital_id_seq",allocationSize = 1,initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hospital_id_gen")

    private Long id;
    @Column(length = 1000000)
    private String image;
    private String name;
    private String address;

    @OneToMany(mappedBy = "hospital",cascade = {ALL})
    private List<Patient>patients;
    @OneToMany(cascade = {ALL})
    private List<Appointment>appointments;
    @OneToMany(mappedBy = "hospital",cascade = {ALL})
    private List<Department>departments;
    @OneToMany(mappedBy = "hospital",cascade = {ALL})
    private List<Doctor>doctors;
}
