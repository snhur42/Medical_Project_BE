package com.bariatr.project.service.user.impl;

import com.bariatr.project.dto.medical_history.MedicalRecordDTO;
import com.bariatr.project.dto.user.AppUserDTO;
import com.bariatr.project.dto.user.NewAppUserDTO;
import com.bariatr.project.model.enums.Role;
import com.bariatr.project.model.medical_history.MedicalHistory;
import com.bariatr.project.model.medical_history.MedicalRecord;
import com.bariatr.project.model.user.impl.Patient;
import com.bariatr.project.repository.medical_history.MedicalHistoryRepository;
import com.bariatr.project.repository.medical_history.MedicalRecordRepository;
import com.bariatr.project.repository.user.PatientRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@Qualifier("patientService")
public class PatientService extends AppUserServiceImpl<Patient, PatientRepository> {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    public PatientService(PatientRepository repository,
                          MedicalHistoryRepository medicalHistoryRepository,
                          MedicalRecordRepository medicalRecordRepository) {
        super(repository);
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public Patient save(NewAppUserDTO newAppUserDTO) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setMedicalRecords(medicalRecords);

        Patient patient = new Patient();
        patient = parseNewUser(patient, newAppUserDTO);
        patient.setRole(Role.PATIENT);

        patient.setMedicalHistory(medicalHistory);

        medicalHistoryRepository.save(medicalHistory);
        return repository.save(patient);
    }

    @Override
    public Patient save(Patient patient) {
        return repository.save(patient);
    }

    public List<MedicalRecordDTO> getMedicalRecords(String patientId) {
        List<MedicalRecord> medicalRecords = repository.findById(UUID.fromString(patientId)).get().getMedicalHistory().getMedicalRecords();
        List<MedicalRecordDTO> medicalRecordDTOS = new ArrayList<>();
        medicalRecords.forEach(medicalRecord -> {
            medicalRecordDTOS.add(new MedicalRecordDTO(medicalRecord.getId().toString(), medicalRecord.getCreated(), medicalRecord.getAnswer_1(), medicalRecord.getAnswer_2(), medicalRecord.getAnswer_3()));
        });

        return medicalRecordDTOS;
    }

    public MedicalRecord addMedicalRecord(String patientId, MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = new MedicalRecord(
                medicalRecordDTO.question1(),
                medicalRecordDTO.question2(),
                medicalRecordDTO.question3()
                );

        repository.findById(UUID.fromString(patientId)).get().getMedicalHistory().getMedicalRecords().add(medicalRecord);

        return medicalRecordRepository.save(medicalRecord);
    }

    public MedicalRecord updateMedicalRecord(String patientId, String medicalRecordId, MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = repository.findById(UUID.fromString(patientId))
                .get().getMedicalHistory().getMedicalRecords()
                .stream().filter(mr -> mr.getId().equals(UUID.fromString(medicalRecordId))).findFirst().get();

        medicalRecord.setAnswer_1(medicalRecordDTO.question1());
        medicalRecord.setAnswer_2(medicalRecordDTO.question2());
        medicalRecord.setAnswer_2(medicalRecordDTO.question3());

        return medicalRecordRepository.save(medicalRecord);
    }

    public Boolean deleteMedicalRecord(String patientId, String medicalRecordId) {
        MedicalRecord medicalRecord = repository.findById(UUID.fromString(patientId))
                .get().getMedicalHistory().getMedicalRecords()
                .stream().filter(mr -> mr.getId().equals(UUID.fromString(medicalRecordId))).findFirst().get();

        medicalRecordRepository.delete(medicalRecord);
        return true;
    }
}