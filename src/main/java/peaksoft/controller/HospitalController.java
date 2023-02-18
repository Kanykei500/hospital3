package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/hospitals")

public class HospitalController {
    private final HospitalService hospitalService;
    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }


    @GetMapping
    public String getAllHospitals(Model model){
        model.addAttribute("hospitals",hospitalService.getAllHospitals());
        return "mainPage";
    }
    @GetMapping("/new")
    public String createHospital(Model model){
        model.addAttribute("newHospital",new Hospital());
        return "newHospital";
    }
    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("newHospital")Hospital hospital) {
        hospitalService.save(hospital);
        return "redirect:/hospitals";

    }
    @DeleteMapping("{hospitalId}/delete")
    public String deleteHospital(@PathVariable("hospitalId")Long id){
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }
    @GetMapping("/{id}/edit")
    public String editHospital(Model model,@PathVariable("id")Long id){
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        return "updateHospital";

    }
    @PutMapping("/{id}/update")
    public String updateHospital(@PathVariable("id")Long id,@ModelAttribute("hospital")Hospital newHospital){
        hospitalService.update(id, newHospital);
        return "redirect:/hospitals";
    }
}

