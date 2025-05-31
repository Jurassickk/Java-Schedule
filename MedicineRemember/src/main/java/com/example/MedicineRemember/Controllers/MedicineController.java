package com.example.MedicineRemember.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicineRemember.Dto.MedicineDto;
import com.example.MedicineRemember.Dto.ResponseDto;
import com.example.MedicineRemember.Model.Medicine;
import com.example.MedicineRemember.Service.MedicineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addMedicine(@RequestBody MedicineDto medicineDTO) {
        ResponseDto response = medicineService.addMedicine(medicineDTO);
        return ResponseEntity.ok(response); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateMedicine(@PathVariable int id, @RequestBody MedicineDto medicineDTO) {
        medicineDTO.setMedicineId(id);
        ResponseDto response = medicineService.updateMedicine(medicineDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteMedicine(@PathVariable int id) {
        ResponseDto response = medicineService.deleteMedicine(id);
        return ResponseEntity.ok(response);
    }
}
