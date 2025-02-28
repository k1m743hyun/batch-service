package com.k1m743hyun.batchservice.monitoring.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BatchJobExecutionContext {

	private long jobExecutionId;
	private String shortContext;
	private String serializedContext;
}
