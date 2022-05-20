package com.bariatr.project.model.medical_history;

import com.bariatr.project.model.AbstractEntity;
import com.bariatr.project.model.user.impl.Patient;
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
@Entity(name = "MedicalHistory")
@Table(name = "medical_history")
public class MedicalHistory extends AbstractEntity {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "medical_records_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "medical_records_id_fk"
            )
    )
    private List<MedicalRecord> medicalRecords;
}
