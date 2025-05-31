package com.example.MedicineRemember.Repository;

import com.example.MedicineRemember.Model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicine extends JpaRepository<Medicine, Integer> {
}
