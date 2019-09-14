package com.mjtoolbox.oss.program;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
public class ProgramController {

    @Resource
    ProgramRepository programRepository;

    @GetMapping("/programs")
    public List<Program> retrieveAllPrograms() {
        return StreamSupport.stream(programRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/programs/{program_id}")
    public Program findById(@PathVariable long program_id) {
        return programRepository.findById(program_id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with ID: " + program_id));
    }

    @PostMapping("/programs")
    public Program createProgram(@Valid @RequestBody Program program) {
        return programRepository.save(program);
    }

    // Update program by program_id
    @PutMapping("/programs/{program_id}")
    public Program updateProgram(@PathVariable long program_id, @Valid @RequestBody Program program) {
        Program programFromDB = programRepository.findById(program_id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with ID: " + program_id));
        programFromDB.setProgram_type(program.getProgram_type());
        programFromDB.setCost(program.getCost());
        return programRepository.save(programFromDB);
    }

    @DeleteMapping("/programs/{program_id}")
    public void delete(@PathVariable long program_id) {
        programRepository.findById(program_id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with ID: " + program_id));
        programRepository.deleteById(program_id);
    }
}
