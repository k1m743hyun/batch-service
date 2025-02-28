package com.k1m743hyun.batchservice.monitoring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k1m743hyun.batchservice.monitoring.dto.request.MonitoringRequestDto;
import com.k1m743hyun.batchservice.monitoring.dto.response.MonitoringResponseDto;
import com.k1m743hyun.batchservice.monitoring.service.MonitoringService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MonitoringController {
	
	private final MonitoringService service;

	@GetMapping("/jobs/errors")
	public MonitoringResponseDto getJobErrorList(MonitoringRequestDto requestDto) {
		return service.getJobErrorList();
	}

	@GetMapping("/steps/errors")
	public MonitoringResponseDto getStepErrorList(MonitoringRequestDto requestDto) {
		return service.getStepErrorList();
	}
}
