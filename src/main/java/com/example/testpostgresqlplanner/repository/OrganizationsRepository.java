package com.example.testpostgresqlplanner.repository;

import com.example.testpostgresqlplanner.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationsRepository extends JpaRepository<Organization, Long> {
}
