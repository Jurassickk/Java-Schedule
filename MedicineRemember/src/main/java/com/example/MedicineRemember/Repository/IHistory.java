package com.example.MedicineRemember.Repository;

import com.example.MedicineRemember.Model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistory extends JpaRepository<History, Integer> {
}
