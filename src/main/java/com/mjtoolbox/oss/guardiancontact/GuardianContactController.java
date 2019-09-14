package com.mjtoolbox.oss.guardiancontact;

import com.mjtoolbox.oss.guardian.GuardianRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

public class GuardianContactController {


    @Resource
    GuardianContactRepository guardianContactRepository;

    @Resource
    GuardianRepository guardianRepository;


    @GetMapping("/guardians/{guardian_id}/contact")
    public GuardianContact findById(@PathVariable long guardian_id) {
        return guardianContactRepository.findById(guardian_id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian Contact not found with ID: " + guardian_id));
    }

    @PostMapping("/guardians/{guardian_id}/contact")
    public GuardianContact createGuardianContact(@Valid @RequestBody GuardianContact guardianContact) {
        return guardianContactRepository.save(guardianContact);
    }

    @PutMapping("/guardians/{guardian_id}/contact")
    public GuardianContact updateGuardianContact(@PathVariable long guardian_id, @Valid @RequestBody GuardianContact guardianContact) {
        GuardianContact contactFromDB = guardianContactRepository.findById(guardian_id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian Contact not found with ID: " + guardian_id));

        contactFromDB.setCell_phone(guardianContact.getCell_phone());
        contactFromDB.setEmail(guardianContact.getEmail());
        contactFromDB.setHome_phone(guardianContact.getHome_phone());
        contactFromDB.setAddress(guardianContact.getAddress());
        contactFromDB.setCity(guardianContact.getCity());
        contactFromDB.setProvince(guardianContact.getProvince());
        contactFromDB.setPostal_code(guardianContact.getPostal_code());
        return guardianContactRepository.save(contactFromDB);
    }
}
