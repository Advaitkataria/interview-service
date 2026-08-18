package org.example.interviewservice.service;

import org.example.interviewservice.model.Interview;
import org.example.interviewservice.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;

    @Autowired
    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

    public List<Interview> getInterviews(int applicationId, String userEmail) {
        return interviewRepository.findByApplicationIdAndUserEmail(applicationId, userEmail);
    }

    public Interview addInterview(Interview interview, int applicationId, String userEmail) {
        interview.setApplicationId(applicationId);
        interview.setUserEmail(userEmail);
        return interviewRepository.save(interview);
    }

    public void deleteInterview(int id, String userEmail) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        if (!interview.getUserEmail().equals(userEmail)) {
            throw new RuntimeException("You can delete only your interviews");
        }
        interviewRepository.deleteById(id);
    }

    public Interview updateInterview(int id, Interview updatedInterview, String userEmail) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        if (!interview.getUserEmail().equals(userEmail)) {
            throw new RuntimeException("You can update only your interviews");
        }
        interview.setInterviewDate(updatedInterview.getInterviewDate());
        interview.setType(updatedInterview.getType());
        interview.setOutcome(updatedInterview.getOutcome());
        interview.setNotes(updatedInterview.getNotes());
        return interviewRepository.save(interview);
    }
}