package com.example.hospital_management.service;

import com.example.hospital_management.entity.Patient;
import com.example.hospital_management.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    // a & g. Retrieve all patients with Pagination and Sorting
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    // b. Retrieve a patient by ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // c. Add a new patient
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // d. Update full patient details
    public Patient updatePatientFull(Long id, Patient updatedPatient) {
        return patientRepository.findById(id).map(patient -> {
            patient.setFullName(updatedPatient.getFullName());
            patient.setDisease(updatedPatient.getDisease());
            patient.setDoctorName(updatedPatient.getDoctorName());
            patient.setWard(updatedPatient.getWard());
            patient.setIsAdmitted(updatedPatient.getIsAdmitted());
            return patientRepository.save(patient);
        }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // e. Partially update a patient
    public Patient updatePatientPartial(Long id, Patient partialPatient) {
        return patientRepository.findById(id).map(patient -> {
            if (partialPatient.getDoctorName() != null) {
                patient.setDoctorName(partialPatient.getDoctorName());
            }
            if (partialPatient.getIsAdmitted() != null) {
                patient.setIsAdmitted(partialPatient.getIsAdmitted());
            }
            return patientRepository.save(patient);
        }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // f. Delete a patient by ID
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    // h. Search patients by name
    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByFullNameContainingIgnoreCase(name);
    }

    // i. Filter patients by ward
    public List<Patient> filterPatientsByWard(String wardName) {
        return patientRepository.findByWard(wardName);
    }
}