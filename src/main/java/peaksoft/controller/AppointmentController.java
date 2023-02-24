package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.service.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final PatientService patientService;
    @Autowired

    public AppointmentController(AppointmentService appointmentService,
                                 DoctorService doctorService, DepartmentService departmentService,
                                  PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.departmentService = departmentService;
        this.patientService = patientService;
    }
    @GetMapping("/{hospitalId}")
    public String getAllAppointment(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments(hospitalId));
        return "appointments/appointments";
    }
    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("newAppointment", new Appointment());
        model.addAttribute("departments",departmentService.getAllDepartments(hospitalId));
        model.addAttribute("doctors",doctorService.getAllDoctors(hospitalId));
        model.addAttribute("patients",patientService.getAllPatients(hospitalId));
        model.addAttribute(hospitalId);
        return "appointments/appointmentSavePage";
    }


    @PostMapping("/{hospitalId}/appointmentSavePage")
    public String saveAppointment(@PathVariable Long hospitalId, @ModelAttribute("newAppointment")Appointment appointment) {
        appointmentService.save(hospitalId, appointment);
        return "redirect:/appointments/"+hospitalId;

    }
    @DeleteMapping("/{hospitalId}/{appointmentId}/delete")
    public String deleteAppointment(@PathVariable("hospitalId")Long hospitalId,@PathVariable ("appointmentId")Long appointmentId){
        appointmentService.deleteAppointment(hospitalId,appointmentId);
        return "redirect: /appointments/"+hospitalId;
    }
    @GetMapping("/{hospitalId}/{appointmentId}/edit")
    public String editAppointment(Model model,@PathVariable("hospitalId")Long hospitalId,@PathVariable ("appointmentId")Long appointmentId){
        model.addAttribute("appointment",appointmentService.getAppointmentById(appointmentId));
        model.addAttribute("doctors",doctorService.getAllDoctors(hospitalId));
        model.addAttribute("departments",departmentService.getAllDepartments(hospitalId));
        model.addAttribute("patients",patientService.getAllPatients(hospitalId));
        model.addAttribute("hospitalId",hospitalId);
        return "appointments/updateAppointment";

    }
    @PutMapping("/{hospitalId}/{appointmentId}/update")
    public String updateAppointment(@PathVariable("hospitalId")Long hospitalId,@PathVariable("appointmentId")Long appointmentId, @ModelAttribute("appointment") Appointment appointment){
        appointmentService.update(appointmentId, appointment);
        return "redirect: /appointments/"+hospitalId;
    }
}


