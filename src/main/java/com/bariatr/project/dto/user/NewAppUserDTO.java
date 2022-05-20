package com.bariatr.project.dto.user;

import com.bariatr.project.model.enums.City;
import com.bariatr.project.model.enums.Role;


public record NewAppUserDTO(
        String firstName,
        String lastName,
        String login,
        String phone,
        String email,
        City city,
        String password
) {
}
