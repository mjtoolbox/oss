package com.mjtoolbox.oss.lesson;

import com.mjtoolbox.oss.program.Program;
import com.mjtoolbox.oss.program.ProgramRepository;
import com.mjtoolbox.oss.teacher.Teacher;
import com.mjtoolbox.oss.teacher.TeacherRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
public class LessonController {

    @Resource
    LessonRepository lessonRepository;

    @Resource
    ProgramRepository programRepository;

    @Resource
    TeacherRepository teacherRepository;

    @GetMapping("/lessons")
    public List<Lesson> retrieveAllLessons() {
        return StreamSupport.stream(lessonRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/lessons/{lesson_id}")
    public Lesson findById(@PathVariable long lesson_id) {
        return lessonRepository.findById(lesson_id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with ID: " + lesson_id));
    }

    // Create a Lesson under this Program with Teacher
    @PostMapping("/programs/{program_id}/teachers/{teacher_id}")
    public Lesson createLesson(@PathVariable long program_id, @PathVariable long teacher_id, @Valid @RequestBody Lesson lesson) {

        Teacher teacher = teacherRepository.findById(teacher_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacher_id));

        // Validate if program exists
        Program program = programRepository.findById(program_id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with ID: " + program_id));

        // Create a lesson with the program
        lesson.setProgram_id(program_id);
        lesson.setProgram(program);
        lesson.setTeacher_id(teacher_id);
        lesson.setTeacher(teacher);

        return lessonRepository.save(lesson);
    }

    // Update lesson from program list

    /**
     * Use this method to update Teacher ID in Lesson or to update Lesson detail
     *
     * @param lesson_id
     * @param teacher_id
     * @param lesson
     * @return
     */
    @PutMapping("/lessons/{lesson_id}/teachers/{teacher_id}")
    public Lesson updateLesson(@PathVariable long lesson_id, @PathVariable long teacher_id, @Valid @RequestBody Lesson lesson) {

        Teacher teacher = teacherRepository.findById(teacher_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacher_id));

        Lesson lessonFromDB = lessonRepository.findById(lesson_id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with ID: " + lesson_id));

        lessonFromDB.setTeacher_id(teacher_id);
        lessonFromDB.setTeacher(teacher);
        lessonFromDB.setName(lesson.getName());
        lessonFromDB.setSeason(lesson.getSeason());
        lessonFromDB.setLesson_date(lesson.getLesson_date());
        return lessonRepository.save(lessonFromDB);
    }

    @DeleteMapping("/lessons/{lesson_id}")
    public void delete(@PathVariable long lesson_id) {
        lessonRepository.findById(lesson_id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with ID: " + lesson_id));
        lessonRepository.deleteById(lesson_id);
    }
}
