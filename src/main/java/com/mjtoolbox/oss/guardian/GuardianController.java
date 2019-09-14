package com.mjtoolbox.oss.guardian;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
public class GuardianController {

    @Resource
    GuardianRepository guardianRepository;

    @GetMapping("/guardians")
    public List<Guardian> retrieveAllGuardians() {
        return StreamSupport.stream(guardianRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/guardians/{guardian_id}")
    public Guardian findById(@PathVariable long guardian_id) {
        return guardianRepository.findById(guardian_id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with ID: " + guardian_id));
    }

    @PostMapping("/guardians")
    public Guardian createGuardian(@Valid @RequestBody Guardian guardian) {
        return guardianRepository.save(guardian);
    }

    @PutMapping("/guardians/{guardian_id}")
    public Guardian updateGuardian(@PathVariable long guardian_id, @Valid @RequestBody Guardian guardian) {
        Guardian guardianFromDB = guardianRepository.findById(guardian_id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with ID: " + guardian_id));
        guardianFromDB.setGuardian_name(guardian.getGuardian_name());
        guardianFromDB.setRelationship(guardian.getRelationship());
        return guardianRepository.save(guardianFromDB);
    }

    @DeleteMapping("/guardians/{guardian_id}")
    public void delete(@PathVariable long guardian_id) {
        guardianRepository.findById(guardian_id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with ID: " + guardian_id));
        guardianRepository.deleteById(guardian_id);
    }

}
