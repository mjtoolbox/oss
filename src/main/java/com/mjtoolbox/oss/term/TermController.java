package com.mjtoolbox.oss.term;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
public class TermController {


    @Resource
    TermRepository termRepository;


    @GetMapping("/terms")
    public List<Term> retrieveAllTerms() {
        return StreamSupport.stream(termRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/terms/{term_id}")
    public Term findById(@PathVariable long term_id) {
        return termRepository.findById(term_id)
                .orElseThrow(() -> new ResourceNotFoundException("Term not found with ID: " + term_id));
    }

    @PostMapping("/terms")
    public Term createTerm(@Valid @RequestBody Term term) {
        return termRepository.save(term);
    }

    // Update program by term_id
    @PutMapping("/terms/{term_id}")
    public Term updateTerm(@PathVariable long term_id, @Valid @RequestBody Term term) {
        Term termFromDB = termRepository.findById(term_id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with ID: " + term_id));
        termFromDB.setTotal_cost(term.getTotal_cost());
        return termRepository.save(termFromDB);
    }

    @DeleteMapping("/terms/{term_id}")
    public void delete(@PathVariable long term_id) {
        termRepository.findById(term_id)
                .orElseThrow(() -> new ResourceNotFoundException("Term not found with ID: " + term_id));
        termRepository.deleteById(term_id);
    }

}
