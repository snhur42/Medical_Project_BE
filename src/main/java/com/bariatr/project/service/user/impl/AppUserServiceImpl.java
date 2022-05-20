package com.bariatr.project.service.user.impl;

import com.bariatr.project.dto.user.AppUserDTO;
import com.bariatr.project.dto.user.NewAppUserDTO;
import com.bariatr.project.model.user.AppUser;
import com.bariatr.project.model.user.impl.Admin;
import com.bariatr.project.repository.user.UserRepository;
import com.bariatr.project.service.user.AppUserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AppUserServiceImpl<T extends AppUser, R extends UserRepository<T>> implements AppUserService<T> {
//    protected final PasswordEncoder passwordEncoder;
    protected final R repository;

    public AppUserServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public abstract T save(NewAppUserDTO newAppUserDTO);

    @Override
    public abstract T save(T t);

    @Override
    public T update(AppUserDTO appUserDTO){
        T t = repository.findById(UUID.fromString(appUserDTO.id())).get();

        t.setFirstName(appUserDTO.firstName());
        t.setLastName(appUserDTO.lastName());
        t.setPhone(appUserDTO.phone());
        t.setEmail(appUserDTO.email());
        t.setCity(appUserDTO.city());

        return save(t);
    };

    @Override
    public void blockUserById(String appUserId) {
        T t = repository.findById(UUID.fromString(appUserId)).get();

        t.setAccountNonExpired(false);
        t.setCredentialsNonExpired(false);
        t.setAccountNonLocked(false);
        t.setEnabled(false);

        save(t);
    }

    @Override
    public void unBlockUserById(String appUserId) {
        T t = repository.findById(UUID.fromString(appUserId)).get();

        t.setAccountNonExpired(true);
        t.setCredentialsNonExpired(true);
        t.setAccountNonLocked(true);
        t.setEnabled(true);

        save(t);
    }

    @Override
    public AppUserDTO findById(String appUserId) {
        T t = repository.findById(UUID.fromString(appUserId)).get();
        return new AppUserDTO(t.getId().toString(), t.getFirstName(), t.getLastName(),t.getPhone(), t.getEmail(), t.getCity());
    }

    @Override
    public Optional<T> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(String appUserId) {
        repository.deleteById(UUID.fromString(appUserId));
    }

    protected T parseNewUser(T t, NewAppUserDTO newAppUserDTO){
        t.setFirstName(newAppUserDTO.firstName());
        t.setLastName(newAppUserDTO.lastName());
        t.setLogin(newAppUserDTO.login());
        t.setPhone(newAppUserDTO.phone());
        t.setEmail(newAppUserDTO.email());
        t.setCity(newAppUserDTO.city());
        t.setPassword(newAppUserDTO.password());
        t.setAccountNonExpired(true);
        t.setCredentialsNonExpired(true);
        t.setAccountNonLocked(true);
        t.setEnabled(true);

        return t;
    }
}
