package com.k1m743hyun.batchservice.monitoring.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobInstance {

	private long jobInstanceId;
	private Long version;
	private String jobName;
	private String jobKey;
}
