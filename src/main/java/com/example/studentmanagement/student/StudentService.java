package com.example.studentmanagement.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private  final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentOptional=studentRepository
                .findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public  void deleteStudent(Long studentId){
//        Optional<Student> studentOptional=studentRepository.findById(studentId);
//
//        if(!studentOptional.isPresent()){
//            throw new IllegalStateException("Email taken");
//        }

      boolean studentExists=studentRepository.existsById(studentId);
        if(!studentExists) {
            throw new IllegalStateException("student with id "+studentId+" does not exists");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student=studentRepository.findById(studentId)
                        .orElseThrow(()->new IllegalStateException("student with id \"+studentId+\" does not exists"));

        if(name!=null &&
                name.length()>0 &&
                !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email!=null &&
            email.length()>0 &&
                !Objects.equals(student.getEmail(),email)
        ){
            Optional<Student> studentOptional= studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email is taken");
            }

            student.setEmail(email);
        }

    }
}
