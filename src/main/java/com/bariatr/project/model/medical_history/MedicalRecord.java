package com.bariatr.project.model.medical_history;

import com.bariatr.project.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MedicalRecord")
@Table(name = "medical_record")
public class MedicalRecord extends AbstractEntity {
    @Column(name = "answer_1", nullable = false, columnDefinition = "TEXT")
    private String answer_1;
    @Column(name = "answer_2", nullable = false, columnDefinition = "TEXT")
    private String answer_2;
    @Column(name = "answer_3", nullable = false, columnDefinition = "TEXT")
    private String answer_3;
}
