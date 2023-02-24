package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/hospitals")

public class HospitalController {
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    @Autowired
    public HospitalController(HospitalService hospitalService, DepartmentService departmentService, DoctorService doctorService, PatientService patientService) {
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;

        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/{id}/mainPage")
    public String profile(@PathVariable("id") Long id, Model model){
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        model.addAttribute("departments",departmentService.getAllDepartments(id));
        model.addAttribute("doctors",doctorService.getAllDoctors(id));
        model.addAttribute("hospitalId",id);
        return "hospitals/mainPage";
    }


    @GetMapping("/profile")
    public String getAllHospitals(Model model){
        model.addAttribute("hospitals",hospitalService.getAllHospitals());
        return "hospitals/profile";
    }
    @GetMapping("/new")
    public String createHospital(Model model){
        model.addAttribute("newHospital",new Hospital());
        return "hospitals/newHospital";
    }
    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("newHospital")Hospital hospital) {
        hospitalService.save(hospital);
        return "redirect:/hospitals/profile";

    }
    @DeleteMapping("{id}/delete")
    public String deleteHospital(@PathVariable("id")Long id){
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals/profile";
    }
    @GetMapping("{hospitalId}")
    public String getById(Model model,@PathVariable ("hospitalId")Long id){
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        model.addAttribute("doctors",hospitalService.getHospitalById(id).getDoctors().size());
        model.addAttribute("patients",hospitalService.getHospitalById(id).getPatients().size());
        model.addAttribute("appointments",hospitalService.getHospitalById(id).getAppointments().size());
        model.addAttribute("departments",hospitalService.getHospitalById(id).getDepartments().size());
        return "hospitals/profile";
    }
    @GetMapping("/{id}/edit")
    public String editHospital(Model model,@PathVariable("id")Long id){
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        return "hospitals/updateHospital";

    }
    @PutMapping("/{id}/update")
    public String updateHospital(@PathVariable("id")Long id,@ModelAttribute("newHospital")Hospital newHospital){
        hospitalService.update(id, newHospital);
        return "redirect:/hospitals/profile";
    }

}

