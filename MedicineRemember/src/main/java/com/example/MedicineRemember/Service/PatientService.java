package com.example.MedicineRemember.Service;



import com.example.MedicineRemember.Model.Patient;
import com.example.MedicineRemember.Repository.IPatient;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.MedicineRemember.Dto.PatientDto;
import com.example.MedicineRemember.Dto.ResponseDto;

@Service
public class PatientService {

    private final IPatient patientRepository;

    public PatientService(IPatient patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public ResponseDto addPatient(PatientDto patientDTO) {
        if (!validation(patientDTO)) return ResponseDto.error("All fields are required");

        Patient patient = dtoToModel(patientDTO);
        patientRepository.save(patient);
        return ResponseDto.ok("Patient added successfully");
    }

    public ResponseDto updatePatient(PatientDto patientDTO) {
        if (!validation(patientDTO)) return ResponseDto.error("All fields are required");
        
        Patient patient = dtoToModel(patientDTO);
        patientRepository.save(patient);
        return ResponseDto.ok("Patient updated successfully");
    }

    public ResponseDto suspendReminders(int patientId, boolean suspend) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (patientOpt.isEmpty()) return ResponseDto.error("Patient not found");

        Patient patient = patientOpt.get();
        patient.setReminderActive(!suspend);
        patientRepository.save(patient);
        return ResponseDto.ok(suspend ? "Reminders suspended" : "Reminders activated");
    }

    private boolean validation(PatientDto patientDTO) {
        return patientDTO.getName() != null && patientDTO.getEmail() != null;
    }

    private Patient dtoToModel(PatientDto patientDTO) {
        return new Patient(patientDTO.getPatientId(), patientDTO.getName(), 
                          patientDTO.getEmail(), patientDTO.isReminderActive());
    }


}
