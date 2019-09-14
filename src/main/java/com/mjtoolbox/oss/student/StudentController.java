package com.mjtoolbox.oss.student;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
public class StudentController {

    @Resource
    StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> retrieveAllStudents() {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/students/{membership_id}")
    public Student findById(@PathVariable long membership_id) {
        return studentRepository.findById(membership_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + membership_id));
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }


    // Update student by membership_id
    @PutMapping("/students/{membership_id}")
    public Student updateStudent(@PathVariable long membership_id, @Valid @RequestBody Student student) {
        Student studentFromDB = studentRepository.findById(membership_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + membership_id));
        studentFromDB.setPreferred_name(student.getPreferred_name());
        studentFromDB.setLegal_name(student.getLegal_name());
        studentFromDB.setDate_of_birth(student.getDate_of_birth());
        studentFromDB.setGrade(student.getGrade());
        studentFromDB.setDate_of_registration(student.getDate_of_registration());
        studentFromDB.setSchool(student.getSchool());
        return studentRepository.save(studentFromDB);
    }

    @DeleteMapping("/students/{membership_id}")
    public void delete(@PathVariable long membership_id) {
        studentRepository.findById(membership_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + membership_id));
        studentRepository.deleteById(membership_id);
    }
}
