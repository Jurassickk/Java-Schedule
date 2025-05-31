package com.example.MedicineRemember.Repository;

import com.example.MedicineRemember.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatient extends JpaRepository<Patient, Integer> {
}
