package com.k1m743hyun.batchservice.monitoring.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BatchJobExecution {

    private long jobExecutionId;
    private Long version;
	private long jobInstanceId;
    private LocalDateTime createDateTime;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
	private String status;
	private String exitCode;
    private String exitMessage;
    private LocalDateTime lastUpdated;
}