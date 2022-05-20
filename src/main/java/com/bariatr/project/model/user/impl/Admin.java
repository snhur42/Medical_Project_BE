package com.bariatr.project.model.user.impl;

import com.bariatr.project.model.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Admin")
@Table(name = "admin",
        uniqueConstraints = {
                @UniqueConstraint(name = "admin_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "admin_phone_unique", columnNames = "phone")
        }
)
public class Admin extends AppUser {
}
