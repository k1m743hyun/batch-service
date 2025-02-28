package com.k1m743hyun.batchservice.monitoring.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BatchJobExecutionParams {

	private long jobExecutionId;
	private String typeCd;
	private String keyName;
	private String stringVal;
	private LocalDateTime dateVal;
	private Long longVal;
	private double doubleVal;
	private char identifying;
}
