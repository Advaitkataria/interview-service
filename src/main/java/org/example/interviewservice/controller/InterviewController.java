package org.example.interviewservice.controller;

import org.example.interviewservice.feign.AuthValidationService;
import org.example.interviewservice.model.Interview;
import org.example.interviewservice.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    private final InterviewService interviewService;
    private final AuthValidationService authValidationService;

    @Autowired
    public InterviewController(InterviewService interviewService,
                               AuthValidationService authValidationService) {
        this.interviewService = interviewService;
        this.authValidationService = authValidationService;
    }

    private String getEmailFromToken(String authHeader) {
        return authValidationService.validateToken(authHeader);
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<Interview>> getInterviews(
            @PathVariable int applicationId,
            @RequestHeader("Authorization") String authHeader) {
        String email = getEmailFromToken(authHeader);
        return new ResponseEntity<>(interviewService.getInterviews(applicationId, email), HttpStatus.OK);
    }

    @PostMapping("/application/{applicationId}")
    public ResponseEntity<Interview> addInterview(
            @PathVariable int applicationId,
            @Valid @RequestBody Interview interview,
            @RequestHeader("Authorization") String authHeader) {
        String email = getEmailFromToken(authHeader);
        return new ResponseEntity<>(interviewService.addInterview(interview, applicationId, email), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interview> updateInterview(
            @PathVariable int id,
            @Valid @RequestBody Interview interview,
            @RequestHeader("Authorization") String authHeader) {
        String email = getEmailFromToken(authHeader);
        return new ResponseEntity<>(interviewService.updateInterview(id, interview, email), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(
            @PathVariable int id,
            @RequestHeader("Authorization") String authHeader) {
        String email = getEmailFromToken(authHeader);
        interviewService.deleteInterview(id, email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}