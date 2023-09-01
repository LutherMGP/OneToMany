package com.example.jpamanytoone_1.repository;

import com.example.jpamanytoone_1.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KommuneRepository extends JpaRepository<Kommune, String> {
}
