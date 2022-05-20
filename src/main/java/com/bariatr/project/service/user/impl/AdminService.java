package com.bariatr.project.service.user.impl;

import com.bariatr.project.dto.user.AppUserDTO;
import com.bariatr.project.dto.user.NewAppUserDTO;
import com.bariatr.project.model.enums.Role;
import com.bariatr.project.model.user.impl.Admin;
import com.bariatr.project.model.user.impl.Doctor;
import com.bariatr.project.repository.user.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Qualifier("adminService")
public class AdminService extends AppUserServiceImpl<Admin, AdminRepository> {
    private final DoctorService doctorService;
    
    public AdminService(AdminRepository repository, DoctorService doctorService) {
        super(repository);
        this.doctorService = doctorService;
    }

    @Override
    public Admin save(NewAppUserDTO newAppUserDTO) {
        Admin admin = new Admin();
        admin = parseNewUser(admin, newAppUserDTO);
        admin.setRole(Role.ADMIN);
        return repository.save(admin);
    }

    @Override
    public Admin save(Admin admin) {
        return repository.save(admin);
    }

    public Doctor createDoctor(NewAppUserDTO newAppUserDTO) {
        return doctorService.save(newAppUserDTO);
    }

    public void blockDoctor(String doctorId) {
        doctorService.blockUserById(doctorId);
    }

    public void unBlockDoctor(String doctorId) {
        doctorService.unBlockUserById(doctorId);
    }



}