package com.example.hospital_management.repository;

import com.example.hospital_management.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // h. Search endpoint to find patients by name (partial match, case-insensitive)
    List<Patient> findByFullNameContainingIgnoreCase(String name);

    // i. Filter endpoint to retrieve patients by ward name
    List<Patient> findByWard(String wardName);
}