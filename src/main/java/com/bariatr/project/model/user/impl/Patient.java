package com.bariatr.project.model.user.impl;

import com.bariatr.project.model.medical_history.MedicalHistory;
import com.bariatr.project.model.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(name = "patient_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "patient_phone_unique", columnNames = "phone")
        }
)
public class Patient extends AppUser {
        @OneToOne()
        @JoinColumn(
                name = "medical_history_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(
                        name = "medical_history_id_fk"
                )
        )
        private MedicalHistory medicalHistory;
}
