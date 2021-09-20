package com.example.springquerydslbasic.company.repo;

import com.example.springquerydslbasic.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {
}
