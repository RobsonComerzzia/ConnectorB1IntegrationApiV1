package com.seidor.comerzzia.connector.domain.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.model.IntegracaoB1Model;

@Service
public class StartProcessService {
	
	@Autowired
	private IntegrationProcessService integrationProcessService;
	
	@Scheduled(cron = "0 0 6 * * ?")
	public IntegracaoB1Model startProcess() {
		
		IntegracaoB1Model response = IntegracaoB1Model.builder()
				.protocol(UUID.randomUUID().toString())
				.dateTimeRequest(LocalDateTime.now())
				.build();
		
		integrationProcessService.processIntegration();
		
		return response;
	}

}
