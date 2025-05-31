package com.example.MedicineRemember.Controllers;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicineRemember.Dto.PatientDto;
import com.example.MedicineRemember.Dto.ResponseDto;
import com.example.MedicineRemember.Model.Patient;
import com.example.MedicineRemember.Service.PatientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addPatient(@RequestBody PatientDto patient) {
        ResponseDto response = patientService.addPatient(patient);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updatePatient(@PathVariable int id, @RequestBody PatientDto patient) {
        patient.setPatientId(id);
        ResponseDto response = patientService.updatePatient(patient);
        return ResponseEntity.ok(response);
    }

}
