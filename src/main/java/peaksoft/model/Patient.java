package peaksoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.MERGE;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_id_gen",sequenceName = "patient_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_id_gen")

    private Long id;
    @NotEmpty(message = "First name should not be null ")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty(message = "Lastname should not be empty")
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    //@NotEmpty(message = "Gender should not be empty")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotEmpty(message = "Email should not be null")
    @Email(message = "Email should be valid")
    @Column(name = "email",unique = true)

    private String email;
    @ManyToOne(cascade  = {REFRESH,PERSIST,DETACH,MERGE})
    private Hospital hospital;
    @OneToMany(mappedBy = "patient",cascade = {REFRESH,PERSIST,DETACH,MERGE,REMOVE})
    private List<Appointment> appointments;
    @Transient
    private Long hospitalId;

}
