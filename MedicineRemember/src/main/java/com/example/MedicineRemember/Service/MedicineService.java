package com.example.MedicineRemember.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MedicineRemember.Dto.MedicineDto;
import com.example.MedicineRemember.Dto.ResponseDto;
import com.example.MedicineRemember.Model.Medicine;
import com.example.MedicineRemember.Repository.IMedicine;

@Service
public class MedicineService {

    private final IMedicine medicineRepository;

    public MedicineService(IMedicine medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public ResponseDto addMedicine(MedicineDto medicineDTO) {
        if (!validation(medicineDTO)) return ResponseDto.error("Name is required");

        Medicine medicine = dtoToModel(medicineDTO);
        medicineRepository.save(medicine);
        return ResponseDto.ok("Medicine added successfully");
    }

    public ResponseDto updateMedicine(MedicineDto medicineDTO) {
        if (!medicineRepository.existsById(medicineDTO.getMedicineId())) {
            return ResponseDto.error("Medicine not found");
        }
        
        Medicine medicine = dtoToModel(medicineDTO);
        medicineRepository.save(medicine);
        return ResponseDto.ok("Medicine updated successfully");
    }

    public ResponseDto deleteMedicine(int id) {
        if (!medicineRepository.existsById(id)) {
            return ResponseDto.error("Medicine not found");
        }
        
        medicineRepository.deleteById(id);
        return ResponseDto.ok("Medicine deleted successfully");
    }

    private boolean validation(MedicineDto medicineDTO) {
        return medicineDTO.getName() != null && !medicineDTO.getName().trim().isEmpty();
    }

    private Medicine dtoToModel(MedicineDto medicineDTO) {
        return new Medicine(medicineDTO.getMedicineId(), 
                           medicineDTO.getName(), medicineDTO.getDose());
    }

}
