package com.bariatr.project.repository;

import com.bariatr.project.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface EntityRepository<T extends AbstractEntity> extends JpaRepository<T, UUID> {

}
