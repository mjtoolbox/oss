package com.mjtoolbox.oss.reportcard;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
public class ReportCardController {

    @Resource
    ReportCardRepository reportCardRepository;

    @GetMapping("/reportcards")
    public List<ReportCard> retrieveAllReportCards() {
        return StreamSupport.stream(reportCardRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/reportcards/{report_card_id}")
    public ReportCard findById(@PathVariable long report_card_id) {
        return reportCardRepository.findById(report_card_id)
                .orElseThrow(() -> new ResourceNotFoundException("Report Cards not found with ID: " + report_card_id));
    }

    @PostMapping("/reportcards")
    public ReportCard createReportCard(@Valid @RequestBody ReportCard reportCard) {
        return reportCardRepository.save(reportCard);
    }

    // Update lesson by report_card_id
    @PutMapping("/reportcards/{report_card_id}")
    public ReportCard updateReportCard(@PathVariable long report_card_id, @Valid @RequestBody ReportCard reportCard) {
        ReportCard reportCardFromDB = reportCardRepository.findById(report_card_id)
                .orElseThrow(() -> new ResourceNotFoundException("Report Card not found with ID: " + report_card_id));
        reportCardFromDB.setScore(reportCard.getScore());
        reportCardFromDB.setSubmitted_date(reportCard.getSubmitted_date());
        return reportCardRepository.save(reportCardFromDB);
    }

    @DeleteMapping("/reportcards/{report_card_id}")
    public void delete(@PathVariable long report_card_id) {
        reportCardRepository.findById(report_card_id)
                .orElseThrow(() -> new ResourceNotFoundException("Report Card not found with ID: " + report_card_id));
        reportCardRepository.deleteById(report_card_id);
    }

}
