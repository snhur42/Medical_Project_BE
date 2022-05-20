package com.bariatr.project.repository.user;

import com.bariatr.project.model.user.impl.Doctor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends UserRepository<Doctor> {
}
