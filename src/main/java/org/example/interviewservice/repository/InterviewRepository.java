package org.example.interviewservice.repository;

import org.example.interviewservice.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    List<Interview> findByApplicationIdAndUserEmail(int applicationId, String userEmail);
}