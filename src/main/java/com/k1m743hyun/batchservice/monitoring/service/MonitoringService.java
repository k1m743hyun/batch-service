package com.k1m743hyun.batchservice.monitoring.service;

import com.k1m743hyun.batchservice.monitoring.dto.response.MonitoringResponseDto;

public interface MonitoringService {
	
	MonitoringResponseDto getJobErrorList();

	MonitoringResponseDto getStepErrorList();
}
