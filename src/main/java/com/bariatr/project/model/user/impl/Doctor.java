package com.bariatr.project.model.user.impl;

import com.bariatr.project.model.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Doctor")
@Table(name = "doctor",
        uniqueConstraints = {
                @UniqueConstraint(name = "doctor_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "doctor_phone_unique", columnNames = "phone")
        }
)
public class Doctor extends AppUser {
        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(
                name = "patient_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(
                        name = "patient_id_fk"
                )
        )
        private List<Patient> patients;

}
