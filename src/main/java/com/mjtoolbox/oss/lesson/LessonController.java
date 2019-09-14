package com.mjtoolbox.oss.lesson;

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

    @PostMapping("/lessons")
    public Lesson createLesson(@Valid @RequestBody Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    // Update lesson by lesson_id
    @PutMapping("/lessons/{lesson_id}")
    public Lesson updateLesson(@PathVariable long lesson_id, @Valid @RequestBody Lesson lesson) {
        Lesson lessonFromDB = lessonRepository.findById(lesson_id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with ID: " + lesson_id));
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
