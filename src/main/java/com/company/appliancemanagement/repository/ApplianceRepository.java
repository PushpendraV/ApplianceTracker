package com.company.appliancemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.appliancemanagement.model.Appliance;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
}
