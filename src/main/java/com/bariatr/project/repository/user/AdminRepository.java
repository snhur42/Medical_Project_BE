package com.bariatr.project.repository.user;

import com.bariatr.project.model.user.impl.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin> {
}
