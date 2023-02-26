package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @Autowired

    public DepartmentController(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{hospitalId}")
    public String getAllDepartments(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments(hospitalId));
        model.addAttribute(hospitalId);
        return "departments/departments";
    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable Long hospitalId, Model model) {
        model.addAttribute("newDepartment", new Department());
        model.addAttribute(hospitalId);
        return "departments/departmentSavePage";
    }


    @PostMapping("/{hospitalId}/departmentSavePage")
    public String saveDepartment(@PathVariable Long hospitalId, @ModelAttribute("newDepartment")@Valid Department department) {
        departmentService.save(hospitalId,department);
        return "redirect: /departments/"+hospitalId;

    }
    @DeleteMapping("/{hospitalId}/{departmentId}/delete")
    public String deleteDepartment(@PathVariable("hospitalId")Long hospitalId, @PathVariable ("departmentId")Long id){
        departmentService.deleteDepartment(id);
        return "redirect:/departments/"+hospitalId;
    }
    @GetMapping("/{hospitalId}/{departmentId}/edit")
    public String editDepartment(Model model,@PathVariable("departmentId")Long departmentId,@PathVariable ("hospitalId")Long hospitalId){
        model.addAttribute(departmentService.getDepartmentById(departmentId));
        model.addAttribute("hospitalId",hospitalId);
        return "departments/updateDepartment";

    }
    @PutMapping("/{hospitalId}/{departmentId}/update")
    public String updateDepartment(@PathVariable("hospitalId")Long hospitalId,@PathVariable("departmentId")Long id, @ModelAttribute("department")@Valid Department department){
        departmentService.update(id, department);
        return "redirect:/departments/"+hospitalId;
    }
}
