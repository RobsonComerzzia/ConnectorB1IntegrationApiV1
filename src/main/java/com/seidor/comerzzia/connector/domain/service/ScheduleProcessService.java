package com.seidor.comerzzia.connector.domain.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.model.IntegracaoB1Model;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Service
public class ScheduleProcessService {
	
	@Autowired
	private IntegrationProcessService integrationProcessService;
	
	@Scheduled(cron = "0 30 06 * * ?", zone = "America/Sao_Paulo")
	@SchedulerLock(name = "TaskScheduler_startProcess", lockAtLeastFor = "PT10M", lockAtMostFor = "PT20M")
	public IntegracaoB1Model startProcess() {
		
		IntegracaoB1Model response = IntegracaoB1Model.builder()
				.protocol(UUID.randomUUID().toString())
				.dateTimeRequest(LocalDateTime.now())
				.build();
		
		integrationProcessService.processIntegration();
		
		return response;
	}

}
