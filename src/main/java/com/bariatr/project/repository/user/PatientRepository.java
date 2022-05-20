package com.bariatr.project.repository.user;

import com.bariatr.project.model.user.impl.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends UserRepository<Patient> {
}
