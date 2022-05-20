package com.bariatr.project.dto.user;

import com.bariatr.project.model.enums.City;
import com.bariatr.project.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AppUserDTO(
        String id,
        String firstName,
        String lastName,
        String phone,
        String email,
        City city
) {
}