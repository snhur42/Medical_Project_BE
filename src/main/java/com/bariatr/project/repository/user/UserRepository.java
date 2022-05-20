package com.bariatr.project.repository.user;

import com.bariatr.project.model.user.AppUser;
import com.bariatr.project.repository.EntityRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T extends AppUser> extends EntityRepository<T> {
    Optional<T> findByLogin(String login);
    boolean existsByLogin(String login);
}
