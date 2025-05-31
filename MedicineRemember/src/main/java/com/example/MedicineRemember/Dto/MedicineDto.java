package com.example.MedicineRemember.Dto;

public class MedicineDto {

    private int medicineId;
    private String name;
    private String dose;

    public MedicineDto(){}

    public MedicineDto(int medicineId, String name, String dose) {
        this.medicineId = medicineId;
        this.name = name;
        this.dose = dose;
    }
    public int getMedicineId() {
        return medicineId;
    }
    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDose() {
        return dose;
    }
    public void setDose(String dose) {
        this.dose = dose;
    }

}
