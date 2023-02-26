package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.enums.Gender;
import peaksoft.model.Patient;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final HospitalService hospitalService;
    private final PatientService patientService;
    @Autowired

    public PatientController(HospitalService hospitalService, PatientService patientService) {
        this.hospitalService = hospitalService;
        this.patientService = patientService;
    }
    @GetMapping("/{hospitalId}")
    public String getAllPatients(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("patients",patientService.getAllPatients(hospitalId));
        return "patients/patients";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("newPatients", new Patient());
        model.addAttribute("genders", Gender.values());
        model.addAttribute(hospitalId);
        return "patients/patientSavePage";
    }


    @PostMapping("/{hospitalId}/patientSavePage")
    public String savePatient(@PathVariable Long hospitalId, @ModelAttribute("newPatient")@Valid Patient patient) {
        patientService.save(hospitalId,patient);
        return "redirect: /patients/"+hospitalId;

    }
    @DeleteMapping("/{hospitalId}/{patientId}/delete")
    public String deletePatient(@PathVariable("hospitalId")Long hospitalId,@PathVariable("patientId")Long patientId){
        patientService.deletePatient(patientId);
        return "redirect:/patients/"+hospitalId;
    }
    @GetMapping("/{hospitalId}/{patientId}/edit")
    public String editPatient(Model model,@PathVariable("patientId")Long id,@PathVariable("hospitalId")Long hospitalId){
        model.addAttribute("patient",patientService.getPatientById(id));
        model.addAttribute("genders",Gender.values());
        return "patients/updatePatient";

    }
    @PutMapping("/{hospitalId}/{patientId}/update")
    public String updatePatient(@PathVariable("hospitalId")Long hospitalId,@PathVariable("patientId")Long patientId,@ModelAttribute("patient")@Valid Patient patient){
        patientService.update(patientId, patient);
        return "redirect:/patients/"+hospitalId;
    }
}



