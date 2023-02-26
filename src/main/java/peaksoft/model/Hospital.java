package peaksoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    @NotEmpty(message = "Name should not be null")
    private String name;
    @NotEmpty(message = "Address should not be null")
    private String address;

    @OneToMany(mappedBy = "hospital",cascade = {ALL})
    private List<Patient>patients;
    @OneToMany(cascade = {ALL})
    private List<Appointment>appointments;
    public void addAppointment(Appointment appointment) {
        if (appointments == null) {
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
    }
    @OneToMany(mappedBy = "hospital",cascade = {ALL})
    private List<Department>departments;
    @OneToMany(mappedBy = "hospital",cascade = {ALL})
    private List<Doctor>doctors;


}
