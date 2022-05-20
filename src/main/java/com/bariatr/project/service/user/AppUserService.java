package com.bariatr.project.service.user;

import com.bariatr.project.dto.user.AppUserDTO;
import com.bariatr.project.dto.user.NewAppUserDTO;
import com.bariatr.project.model.user.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService<T extends  AppUser> {
    T save(NewAppUserDTO newAppUserDTO);
    T save(T t);

    T update(AppUserDTO appUserDTO);
    void blockUserById(String appUserId);
    void unBlockUserById(String appUserId);

    AppUserDTO findById(String appUserId);
    Optional<T> findByLogin(String login);

    List<T> findAll();

    void deleteById(String appUserId);
}
