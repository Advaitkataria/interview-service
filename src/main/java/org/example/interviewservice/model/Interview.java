package org.example.interviewservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "type cannot be null")
    @Pattern(regexp = "Phone|Technical|Final")
    private String type;

    @NotNull
    private LocalDate interviewDate;

    @NotBlank(message = "outcome cannot be null")
    private String outcome;

    private String notes;

    @Column(nullable = false)
    private int applicationId;

    @Column(nullable = false)
    private String userEmail;
}