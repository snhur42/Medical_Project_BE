package com.bariatr.project.service.user.impl;

import com.bariatr.project.dto.user.AppUserDTO;
import com.bariatr.project.dto.user.NewAppUserDTO;
import com.bariatr.project.model.enums.Role;
import com.bariatr.project.model.medical_history.MedicalHistory;
import com.bariatr.project.model.user.impl.Admin;
import com.bariatr.project.model.user.impl.Doctor;
import com.bariatr.project.model.user.impl.Patient;
import com.bariatr.project.repository.user.DoctorRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Qualifier("doctorService")
public class DoctorService extends AppUserServiceImpl<Doctor, DoctorRepository>{
    private final PatientService patientService;

    public DoctorService(DoctorRepository repository, PatientService patientService) {
        super(repository);
        this.patientService = patientService;
    }

    @Override
    public Doctor save(NewAppUserDTO newAppUserDTO) {
        Doctor doctor = new Doctor();
        doctor = parseNewUser(doctor, newAppUserDTO);
        doctor.setPatients(new ArrayList<>());
        doctor.setRole(Role.DOCTOR);
        return repository.save(doctor);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return repository.save(doctor);
    }

    public Patient createPatient(String doctorId, NewAppUserDTO newAppUserDTO) {
        Doctor doctor = repository.findById(UUID.fromString(doctorId)).get();
        Patient patient = patientService.save(newAppUserDTO);
        doctor.getPatients().add(patient);
        repository.save(doctor);
        return patient;
    }

    public Patient updatePatient(String doctorId, String patientId, AppUserDTO appUserDTO) {
        Patient patient = repository.findById(UUID.fromString(doctorId)).get().getPatients()
                .stream().filter(p -> p.getId().equals(UUID.fromString(patientId)))
                .findFirst().get();
        
        patient = patientService.update(appUserDTO);

        return patientService.save(patient);
    }

    public List<AppUserDTO> findAllPatientByDoctorId(String doctorId) {
        List<Patient> patientList = repository.findById(UUID.fromString(doctorId)).get().getPatients();
        List<AppUserDTO> appUserDTOList = new ArrayList<>();
                patientList.forEach(patient -> {
                    appUserDTOList.add(new AppUserDTO(patient.getId().toString(), patient.getFirstName(), patient.getLastName(),patient.getPhone(), patient.getEmail(), patient.getCity()));
                });

        return appUserDTOList;
    }
}


