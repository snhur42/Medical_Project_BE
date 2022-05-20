package com.bariatr.project.rest;

import com.bariatr.project.dto.user.AppUserDTO;
import com.bariatr.project.dto.user.NewAppUserDTO;
import com.bariatr.project.model.user.impl.Doctor;
import com.bariatr.project.model.user.impl.Patient;
import com.bariatr.project.service.user.impl.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/doctor")
public class DoctorRestController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorRestController(@Qualifier("doctorService") DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("get_doctor/{doctorId}")
    public ResponseEntity<AppUserDTO> getDoctorById(@PathVariable String doctorId) {
        return new ResponseEntity<>(doctorService.findById(doctorId), HttpStatus.OK);
    }

    @GetMapping("get_all_patient/{doctorId}")
    public ResponseEntity<List<AppUserDTO>> getAllPatients(@PathVariable String doctorId) {
        return new ResponseEntity<>(doctorService.findAllPatientByDoctorId(doctorId), HttpStatus.OK);
    }

    @PutMapping("update_doctor")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody AppUserDTO appUserDTO) {
        return new ResponseEntity<>(doctorService.update(appUserDTO), HttpStatus.OK);
    }

    @PostMapping("{doctorId}/create_patient")
    public ResponseEntity<Patient> createPatient(@PathVariable String doctorId, @RequestBody NewAppUserDTO newAppUserDTO) {
        return new ResponseEntity<>(doctorService.createPatient(doctorId, newAppUserDTO), HttpStatus.CREATED);
    }

    @PutMapping("{doctorId}/update_patient/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String doctorId, @PathVariable String patientId, @RequestBody AppUserDTO appUserDTO) {
        return new ResponseEntity<>(doctorService.updatePatient(doctorId, patientId, appUserDTO), HttpStatus.OK);
    }

}
