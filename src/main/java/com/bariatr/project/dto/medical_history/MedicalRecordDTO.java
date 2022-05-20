package com.bariatr.project.dto.medical_history;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedicalRecordDTO (
        String id,
        LocalDateTime created,
        String question1,
        String question2,
        String question3
){
}
