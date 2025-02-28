package com.k1m743hyun.batchservice.monitoring.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BatchStepExecution {

	private long stepInstanceId;
	private Long version;
	private String stepName;
	private long jobExecutionId;
	private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
	private String status;
	private long commitCount;
	private long readCount;
	private long filterCount;
	private long writeCount;
	private long readSkipCount;
	private long writeSkipCount;
	private long processSkipCount;
	private long rollbackCount;
	private String exitCode;
	private String exitMessage;
	private LocalDateTime lastUpdated;
}
