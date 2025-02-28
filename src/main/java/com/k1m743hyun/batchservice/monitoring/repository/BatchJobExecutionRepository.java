package com.k1m743hyun.batchservice.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k1m743hyun.batchservice.monitoring.entity.BatchJobExecution;

public interface BatchJobExecutionRepository extends JpaRepository<BatchJobExecution, Long> {

}
