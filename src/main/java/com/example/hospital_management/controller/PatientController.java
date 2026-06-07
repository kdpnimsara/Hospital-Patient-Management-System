package com.example.hospital_management.controller;

import com.example.hospital_management.entity.Patient;
import com.example.hospital_management.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/h/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // a & g. Retrieve all patients (Supports Pagination & Sorting)
    @GetMapping
    public ResponseEntity<Page<Patient>> getAllPatients(Pageable pageable) {
        return ResponseEntity.ok(patientService.getAllPatients(pageable));
    }

    // b. Retrieve a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // c. Add a new patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.savePatient(patient), HttpStatus.CREATED);
    }

    // d. Update full patient details
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatientFull(@PathVariable Long id, @RequestBody Patient patient) {
        try {
            return ResponseEntity.ok(patientService.updatePatientFull(id, patient));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // e. Partially update a patient (PATCH - මෙතන තමයි කලින් වැරදිලා තිබුණේ)
    @PatchMapping("/{id}")
    public ResponseEntity<Patient> updatePatientPartial(@PathVariable Long id, @RequestBody Patient patient) {
        try {
            return ResponseEntity.ok(patientService.updatePatientPartial(id, patient));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // f. Delete a patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    // h. Search endpoint
    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam String name) {
        return ResponseEntity.ok(patientService.searchPatientsByName(name));
    }

    // i. Filter endpoint
    @GetMapping("/filter")
    public ResponseEntity<List<Patient>> filterPatientsByWard(@RequestParam String ward) {
        return ResponseEntity.ok(patientService.filterPatientsByWard(ward));
    }
}