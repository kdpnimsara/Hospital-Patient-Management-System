package com.example.hospital_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "disease")
    private String disease;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "ward")
    private String ward;

    @Column(name = "is_admitted")
    private Boolean isAdmitted;

    // 7) No-args constructor
    public Patient() {
    }

    // 7) Parameterized constructor
    public Patient(Long patientId, String fullName, String disease, String doctorName, String ward, Boolean isAdmitted) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.disease = disease;
        this.doctorName = doctorName;
        this.ward = ward;
        this.isAdmitted = isAdmitted;
    }

    // 7) Getters and Setters
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Boolean getIsAdmitted() {
        return isAdmitted;
    }

    public void setIsAdmitted(Boolean isAdmitted) {
        this.isAdmitted = isAdmitted;
    }
}