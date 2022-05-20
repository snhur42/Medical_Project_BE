package com.bariatr.project.rest;

import com.bariatr.project.dto.medical_history.MedicalRecordDTO;
import com.bariatr.project.dto.user.AppUserDTO;
import com.bariatr.project.dto.user.NewAppUserDTO;
import com.bariatr.project.model.medical_history.MedicalHistory;
import com.bariatr.project.model.medical_history.MedicalRecord;
import com.bariatr.project.model.user.impl.Admin;
import com.bariatr.project.model.user.impl.Patient;
import com.bariatr.project.service.user.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")
public class PatientRestController {
    private final PatientService patientService;

    @Autowired
    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("get_all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("get_patient/{patientId}")
    public ResponseEntity<AppUserDTO> getPatientById(@PathVariable String patientId) {
        return new ResponseEntity<>(patientService.findById(patientId), HttpStatus.OK);
    }

    @PutMapping("update_patient")
    public ResponseEntity<Patient> updatePatient(@RequestBody AppUserDTO appUserDTO) {
        return new ResponseEntity<>(patientService.update(appUserDTO), HttpStatus.OK);
    }

    @GetMapping("get_medical_records/{patientId}")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecords(@PathVariable String patientId) {
        return new ResponseEntity<>(patientService.getMedicalRecords(patientId), HttpStatus.OK);
    }

    @PostMapping("{patientId}/add_record")
    public ResponseEntity<MedicalRecord> addMedicalRecord(@PathVariable String patientId, @RequestBody MedicalRecordDTO medicalRecordDTO) {
        return new ResponseEntity<>(patientService.addMedicalRecord(patientId, medicalRecordDTO), HttpStatus.CREATED);
    }

    @PutMapping("{patientId}/update_record/{medicalRecordId}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String patientId, @PathVariable String medicalRecordId, @RequestBody MedicalRecordDTO medicalRecordDTO) {
        return new ResponseEntity<>(patientService.updateMedicalRecord(patientId, medicalRecordId, medicalRecordDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("{patientId}/delete_record/{medicalRecordId}")
    public ResponseEntity<HttpStatus> deleteMedicalRecord(@PathVariable String patientId, @PathVariable String medicalRecordId) {
        patientService.deleteMedicalRecord(patientId, medicalRecordId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
