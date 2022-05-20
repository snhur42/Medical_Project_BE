package com.bariatr.project.model.user;

import com.bariatr.project.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AppUser extends AppUserDetails {
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String lastName;
    @Column(name = "login", nullable = false, columnDefinition = "VARCHAR(255)")
    private String login;
//    @Column(name = "year_of_Birth", nullable = false, columnDefinition = "TIMESTAMP")
//    private LocalDate yearOfBirth;
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)")
    private String phone;
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "city", nullable = false)
    private City city;


//    @Override
//    public String getUsername() {
//        return getEmail();
//    }
}
