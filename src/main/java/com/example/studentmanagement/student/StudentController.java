package com.example.studentmanagement.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    public  void removeStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public  void updateStudent(@PathVariable("studentId") Long studentId,
                               @RequestBody StudentUpdateDto updateDto){
        studentService.updateStudent(studentId,updateDto.getName(),updateDto.getEmail());
    }
}
