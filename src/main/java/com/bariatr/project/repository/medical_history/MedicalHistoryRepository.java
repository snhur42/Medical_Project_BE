package com.bariatr.project.repository.medical_history;

import com.bariatr.project.model.medical_history.MedicalHistory;
import com.bariatr.project.repository.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends EntityRepository<MedicalHistory> {
}
