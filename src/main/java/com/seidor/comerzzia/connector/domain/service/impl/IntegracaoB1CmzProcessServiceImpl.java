package com.seidor.comerzzia.connector.domain.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.api.v1.util.reflections.ReflectionsUtils;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.service.IntegracaoB1CmzProcessService;
import com.seidor.comerzzia.connector.domain.service.IntegrarB1LoadDataService;
import com.seidor.comerzzia.connector.domain.service.UpdateFromMasterToComerzziaService;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IntegracaoB1CmzProcessServiceImpl implements IntegracaoB1CmzProcessService {
	
	@Autowired
	private ItemB1Repository itemB1Repository;
	
	@Autowired
	private RestClientMaster<ArticulosInput> restClientArticulos;
	
	@Autowired
	private RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp;
	
	@Override
	@Async("asyncB1Executor")
	public CompletableFuture<Void> processIntegration(Class<?>[] types, Object[] values, String urlMaster) {
		
		log.info("[IntegracaoB1CmzProcessService] - Atualizando bases de dados Master...");
		
		this.updateDataMaster(types, values);
			
		log.info("[IntegracaoB1CmzProcessService] - Atualizando bases de dados Comerzzia...");
		
		this.updateDataComerzzia(urlMaster);
	    
	    log.info("[IntegracaoB1CmzProcessService] - Processo finalizado com sucesso.");
	    
	    return CompletableFuture.<Void>completedFuture(null);
	}
	
	private void updateDataMaster(Class<?>[] types, Object[] values) {
		
		ReflectionsUtils.executeVoid(IntegrarB1LoadDataService.class, types, values, "loadData");
		
	}
	
	private void updateDataComerzzia(String urlMaster) {
		
		Object[] valuesParamMethod = { urlMaster };
		Class<?>[] typesParamMethod = { String.class };
		Object[] valuesClassConstructor= { itemB1Repository, restClientArticulos, restClientArticulosImp };
		Class<?>[] typesClassConstructor = { ItemB1Repository.class, RestClientMaster.class, RestClientMaster.class };
		
		ReflectionsUtils.executeVoid(UpdateFromMasterToComerzziaService.class
				, typesClassConstructor
				, valuesClassConstructor
				, "invokeApiComerzzia"
				, typesParamMethod
				, valuesParamMethod);
		
	}

}
