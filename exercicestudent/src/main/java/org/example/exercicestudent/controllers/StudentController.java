package org.example.exercicestudent.controllers;

import org.example.exercicestudent.models.Student;
import org.example.exercicestudent.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("students", studentService.getStudents());
        model.addAttribute("mode","list");
        return "student/list";
    }

    @GetMapping("/student/{id}")
    public String show(@PathVariable UUID id, Model model) {
        model.addAttribute(studentService.getStudent(id));
        return "student/details";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "name",required = false) String name, Model model) {
        List<Student> studentsList = studentService.getStudents();
        studentsList = studentService.getStudentsByName(studentsList, name);
        model.addAttribute("mode","search");
        model.addAttribute("students", studentsList);
        return "student/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("isExist", false);
        model.addAttribute("student", new Student());
        return "student/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Student student,Model model) {
        studentService.addStudent(student);
        return "redirect:/list";
        //return "redirect:/product/" + product.getId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable UUID id, Model model) {
        boolean isExistProduct = true;
        model.addAttribute("isExist", isExistProduct);
        model.addAttribute("student", studentService.getStudent(id));
        return "student/add";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Student product) {
        studentService.updateStudent(product);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        studentService.deleteStudent(id);
        return "redirect:/list";
    }
}
