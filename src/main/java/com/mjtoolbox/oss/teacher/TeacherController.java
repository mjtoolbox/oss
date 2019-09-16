package com.mjtoolbox.oss.teacher;

import com.mjtoolbox.oss.program.Program;
import com.mjtoolbox.oss.program.ProgramRepository;
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

    @Resource
    ProgramRepository programRepository;

    // Some teachers may not have Program assigned yet. List all teachers
    @GetMapping("/teachers")
    public List<Teacher> retrieveAllTeachers() {
        return StreamSupport.stream(teacherRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    // Find Teachers by Program to assign Teacher for the lesson
    @GetMapping("/programs/{program_id}/teachers")
    public List<Teacher> retrieveTeachersByProgram(@PathVariable long program_id) {

        Program program = programRepository.findById(program_id).
                orElseThrow(() -> new ResourceNotFoundException("Program not found with ID: " + program_id));

        return StreamSupport.stream(teacherRepository.findByProgram(program).spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/teachers/{teacher_id}")
    public Teacher findById(@PathVariable long teacher_id) {
        return teacherRepository.findById(teacher_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacher_id));
    }

    // Create a Teacher with Program
    @PostMapping("/teachers/programs/{program_id}")
    public Teacher createTeacher(@PathVariable long program_id, @Valid @RequestBody Teacher teacher) {
        Program program = programRepository.findById(program_id).
                orElseThrow(() -> new ResourceNotFoundException("Program not found with ID: " + program_id));
        // Need to set program id, otherwise returning teacher's program id will be empty.
        teacher.setProgram_id(program_id);
        teacher.setProgram(program);
        return teacherRepository.save(teacher);
    }

    @PutMapping("/teachers/{teacher_id}/programs/{program_id}")
    public Teacher updateTeacher(@PathVariable long teacher_id, @PathVariable long program_id, @Valid @RequestBody Teacher teacher) {
        Teacher teacherFromDB = teacherRepository.findById(teacher_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacher_id));

        Program program = programRepository.findById(program_id).
                orElseThrow(() -> new ResourceNotFoundException("Program not found with ID: " + program_id));

        teacherFromDB.setProgram(program);
        teacherFromDB.setTeacher_name(teacher.getTeacher_name());
        teacherFromDB.setCell_phone(teacher.getCell_phone());
        teacherFromDB.setEmail(teacher.getEmail());
        teacherFromDB.setHome_phone(teacher.getHome_phone());
        teacherFromDB.setAddress(teacher.getAddress());
        teacherFromDB.setCity(teacher.getCity());
        teacherFromDB.setProvince(teacher.getProvince());
        teacherFromDB.setPostal_code(teacher.getPostal_code());
        teacherFromDB.setSubject(teacher.getSubject());
        teacherFromDB.setLevel(teacher.getLevel());
        teacherFromDB.setSubject(teacher.getSubject());
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
