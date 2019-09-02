package com.mjtoolbox.oss.teacher;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
public class TeacherController {
    @Resource
    TeacherRepository teacherRepository;

    @GetMapping("/teachers")
    public List<Teacher> retrieveAllTeachers() {
        return StreamSupport.stream(teacherRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/teachers/{teacher_id}")
    public Teacher findById(@PathVariable long teacher_id) {
        return teacherRepository.findById(teacher_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacher_id));
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @PutMapping("/teachers/{teacher_id}")
    public Teacher updateTeacher(@PathVariable long teacher_id, @Valid @RequestBody Teacher teacher) {
        Teacher teacherFromDB = teacherRepository.findById(teacher_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacher_id));
        teacherFromDB.setTeacher_name(teacher.getTeacher_name());
        teacherFromDB.setCell_phone(teacher.getCell_phone());
        teacherFromDB.setEmail(teacher.getEmail());
        teacherFromDB.setHome_phone(teacher.getHome_phone());
        teacherFromDB.setAddress(teacher.getAddress());
        teacherFromDB.setCity(teacher.getCity());
        teacherFromDB.setProvince(teacher.getProvince());
        teacherFromDB.setPostal_code(teacher.getPostal_code());
        teacherFromDB.setSubjects(teacher.getSubjects());
        teacherFromDB.setLevel(teacher.getLevel());
        teacherFromDB.setSubjects(teacher.getSubjects());
        teacherFromDB.setStatus(teacher.getStatus());
        if (!teacher.getStatus().equalsIgnoreCase("active")) {
            teacherFromDB.setEnd_date(teacher.getEnd_date());
        }
        return teacherRepository.save(teacherFromDB);
    }

    @DeleteMapping("/teachers/{teacher_id}")
    public void delete(@PathVariable long teacher_id) {
        teacherRepository.findById(teacher_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacher_id));
        teacherRepository.deleteById(teacher_id);
    }
}
