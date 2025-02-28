package com.k1m743hyun.batchservice.monitoring.service.impl;

import com.k1m743hyun.batchservice.monitoring.dto.response.MonitoringResponseDto;
import com.k1m743hyun.batchservice.monitoring.repository.BatchJobExecutionRepository;
import com.k1m743hyun.batchservice.monitoring.repository.BatchStepExecutionRepository;
import com.k1m743hyun.batchservice.monitoring.service.MonitoringService;

@RequiredArgsConstructor
@Service
public class MonitoringServiceImpl implements MonitoringService {

	private static BatchJobExecutionRepository jobRepository;
	private static BatchStepExecutionRepository stepRepository;

	public MonitoringResponseDto getJobErrorList() {
		List<BatchJobExecution> entityList = jobRepository.findByStatus("FAILED");
		return new MonitoringResponseDto();
	}

	public MonitoringResponseDto getStepErrorList() {
		List<BatchStepExecution> entityList = stepRepository.findByStatus("FAILED");
		return new MonitoringResponseDto();
	}
}
