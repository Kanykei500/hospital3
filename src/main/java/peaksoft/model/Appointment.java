package peaksoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor

public class Appointment {
    @Id
    @SequenceGenerator(name = "appointment_id_gen",sequenceName = "appointment_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appointment_id_gen")
    private Long id;
    @NotEmpty(message = "Date should not be null")
    private LocalDate date;
    @ManyToOne(cascade = {REFRESH,PERSIST,DETACH,MERGE})
    private Doctor doctor;
    @ManyToOne(cascade = {REFRESH,PERSIST,DETACH,MERGE})
    private Department department;
    @ManyToOne(cascade = {REFRESH,PERSIST,DETACH,MERGE})
    private Patient patient;
    @Transient
    private Long departmentId;
    @Transient
    private Long patientId;
    @Transient
    private Long doctorId;
    @Transient
    private String date1;






}
