package com.seidor.comerzzia.connector.domain.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.IntegrationProcessServiceBase;
import com.seidor.comerzzia.connector.constants.Constants;
import com.seidor.comerzzia.connector.util.reflections.ReflectionsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IntegrationProcessService extends IntegrationProcessServiceBase {
	
	@Async("asyncB1Executor")
	public CompletableFuture<Void> processIntegration() {
		
		log.info("[IntegracaoB1CmzProcessService] - Atualizando bases de dados Master...");
		
		this.updateDataMaster();
			
		log.info("[IntegracaoB1CmzProcessService] - Atualizando bases de dados Comerzzia...");
		
		this.updateDataComerzzia();
	    
	    log.info("[IntegracaoB1CmzProcessService] - Processo finalizado com sucesso.");
	    
	    return CompletableFuture.<Void>completedFuture(null);
	}
	
	private void updateDataMaster() {
		
		ReflectionsUtils.executeVoid(IntegrarB1LoadDataService.class
				, this.loadTypesConstructorsB1()
				, this.loadValuesConstructorsB1()
				, Constants.LOAD_DATA);
		
	}
	
	private void updateDataComerzzia() {
		
		ReflectionsUtils.executeVoid(SyncComerzziaFromMasterService.class
				, this.loadTypesConstructorsComerzzia()
				, this.loadValuesConstructorsComerzzia()
				, Constants.INVOKE_API_COMERZZIA
				, this.loadTypesParametersComerzzia()
				, this.loadValuesParametersComerzzia());
		
	}

}
