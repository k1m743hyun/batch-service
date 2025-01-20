package com.k1m743hyun.batchservice.monitoring.service.impl;

import com.k1m743hyun.batchservice.monitoring.dto.response.MonitoringResponseDto;
import com.k1m743hyun.batchservice.monitoring.service.MonitoringService;

public class MonitoringServiceImpl implements MonitoringService {

	public MonitoringResponseDto getErrorList() {
		return new MonitoringResponseDto();
	}
}
