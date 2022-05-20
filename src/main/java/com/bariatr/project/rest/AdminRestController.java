package com.bariatr.project.rest;

import com.bariatr.project.dto.user.AppUserDTO;
import com.bariatr.project.dto.user.NewAppUserDTO;
import com.bariatr.project.model.user.impl.Admin;
import com.bariatr.project.model.user.impl.Doctor;
import com.bariatr.project.service.user.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminRestController {
    private final AdminService adminService;

    @Autowired
    public AdminRestController(@Qualifier("adminService") AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("get_admin/{adminId}")
    public ResponseEntity<AppUserDTO> getAdmin(@PathVariable String adminId) {
        return new ResponseEntity<>(adminService.findById(adminId), HttpStatus.CREATED);
    }

    @PostMapping("create_admin")
    public ResponseEntity<Admin> createAdmin(@RequestBody NewAppUserDTO newAppUserDTO) {
        return new ResponseEntity<>(adminService.save(newAppUserDTO), HttpStatus.CREATED);
    }

    @PutMapping("update_admin")
    public ResponseEntity<Admin> updateAdmin(@RequestBody AppUserDTO appUserDTO) {
        return new ResponseEntity<>(adminService.update(appUserDTO), HttpStatus.OK);
    }

    @PostMapping("create_doctor")
    public ResponseEntity<Doctor> createDoctor(@RequestBody NewAppUserDTO newAppUserDTO) {
        return new ResponseEntity<>(adminService.createDoctor(newAppUserDTO), HttpStatus.CREATED);
    }

    @PutMapping("block_doctor/{doctorId}")
    public ResponseEntity<HttpStatus> blockDoctor(@PathVariable  String doctorId) {
        adminService.blockDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("unblock_doctor/{doctorId}")
    public ResponseEntity<HttpStatus> unBlockDoctor(@PathVariable  String doctorId) {
        adminService.unBlockDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
