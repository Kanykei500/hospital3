package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.*;

@Controller
@RequestMapping("/hospitals")

public class HospitalController {
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    @Autowired
    public HospitalController(HospitalService hospitalService, DepartmentService departmentService, DoctorService doctorService, PatientService patientService, AppointmentService appointmentService) {
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;

        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}/mainPage")
    public String profile(@PathVariable("id") Long id, Model model){
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        model.addAttribute("doctors",doctorService.getAllDoctors(id).size());
       model.addAttribute("patients",patientService.getAllPatients(id).size());
       model.addAttribute("appointments",appointmentService.getAllAppointments(id).size());
        model.addAttribute("departments",departmentService.getAllDepartments(id).size());
        model.addAttribute("hospitalId",id);
        return "hospitals/mainPage";
    }


    @GetMapping("/profile")
    public String getAllHospitals(Model model ,@RequestParam(name = "word",required = false)String word){
        model.addAttribute("word",word);
        model.addAttribute("hospitals",hospitalService.getAllHospitals(word));
        return "hospitals/profile";
    }
    @GetMapping("/new")
    public String createHospital(Model model){
        model.addAttribute("newHospital",new Hospital());
        return "hospitals/newHospital";
    }
    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("newHospital") @Valid Hospital hospital ) {
        hospitalService.save(hospital);
        return "redirect:/hospitals/profile";

    }
    @DeleteMapping("{id}/delete")
    public String deleteHospital(@PathVariable("id")Long id){
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals/profile";
    }

    @GetMapping("/{id}/edit")
    public String editHospital(Model model,@PathVariable("id")Long id){
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        return "hospitals/updateHospital";

    }
    @PutMapping("/{id}/update")
    public String updateHospital(@PathVariable("id")Long id,@ModelAttribute("newHospital") @Valid Hospital newHospital){
        hospitalService.update(id, newHospital);
        return "redirect:/hospitals/profile";
    }

}

