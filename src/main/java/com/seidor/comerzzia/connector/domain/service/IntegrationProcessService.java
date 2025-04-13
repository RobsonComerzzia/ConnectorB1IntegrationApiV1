package com.seidor.comerzzia.connector.domain.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.model.GuidB1Model;
import com.seidor.comerzzia.connector.api.v1.model.VerifyB1Model;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.AuthenticationInput;
import com.seidor.comerzzia.connector.api.v1.model.input.GuidB1ModelInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ItemsGravarInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceListInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonPartnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonTaxInput;
import com.seidor.comerzzia.connector.api.v1.model.input.VerifyB1ModelInput;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientB1;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;
import com.seidor.comerzzia.connector.rest.client.RestClientToken;
import com.seidor.comerzzia.connector.util.json.ReadJson;
import com.seidor.comerzzia.connector.util.reflections.ReflectionsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IntegrationProcessService extends OauthService {
	
	@Value("${master.api.integracao.item.url}")
	private String urlMasterItem;
	
	@Value("${b1.token}")
	private String token;
	
	@Value("${b1.apiKey}")
	private String apiKey;
	
	@Value("${url.base.b1}")
	private String urlBaseB1;
	
	@Autowired
	private RestClientB1<GuidB1ModelInput, GuidB1Model, VerifyB1ModelInput, VerifyB1Model> restClient;
	
	@Autowired
	private ReadJson<List<JsonTaxInput>> readJsonTax;
	
	@Autowired
	private ReadJson<List<JsonItemInput>> readJsonItem;
	
	@Autowired
	private ReadJson<List<JsonItemPriceInput>> readJsonItemPrice;
	
	@Autowired
	private ReadJson<List<JsonItemPriceListInput>> readJsonItemPriceList;
	
	@Autowired
	private ReadJson<List<JsonPartnerInput>> readJsonPartner;
	
	@Autowired
	private GravarDadosB1Service<List<JsonTaxInput>> gravarDadosTaxService;
	
	@Autowired
	private GravarDadosB1Service<ItemsGravarInput> gravarDadosItemService;
	
	@Autowired
	private GravarDadosB1Service<List<JsonPartnerInput>> gravarDadosPartnerService;
	
	@Autowired
	private ItemB1Repository itemB1Repository;
	
	@Autowired
	private RestClientMaster<ArticulosInput> restClientArticulos;
	
	@Autowired
	private RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp;
	
	@Async("asyncB1Executor")
	public CompletableFuture<Void> processIntegration() {
		
		log.info("[IntegracaoB1CmzProcessService] - Atualizando bases de dados Master...");
		
		this.updateDataMaster(this.loadTypes(), this.loadValues());
			
		log.info("[IntegracaoB1CmzProcessService] - Atualizando bases de dados Comerzzia...");
		
		this.updateDataComerzzia(this.urlMasterItem);
	    
	    log.info("[IntegracaoB1CmzProcessService] - Processo finalizado com sucesso.");
	    
	    return CompletableFuture.<Void>completedFuture(null);
	}
	
	private void updateDataMaster(Class<?>[] types, Object[] values) {
		
		ReflectionsUtils.executeVoid(IntegrarB1LoadDataService.class, types, values, "loadData");
		
	}
	
	private void updateDataComerzzia(String urlMaster) {
		
		Object[] valuesParamMethod = { urlMaster, null };
		Class<?>[] typesParamMethod = { String.class, String.class };
		Object[] valuesClassConstructor= { itemB1Repository, restClientArticulos, restClientArticulosImp };
		Class<?>[] typesClassConstructor = { ItemB1Repository.class, RestClientMaster.class, RestClientMaster.class };
		
		ReflectionsUtils.executeVoid(SyncComerzziaFromMasterService.class
				, typesClassConstructor
				, valuesClassConstructor
				, "invokeApiComerzzia"
				, typesParamMethod
				, valuesParamMethod);
		
	}
	
	private Class<?>[] loadTypes(){
		
		Class<?>[] types = { 
				  String.class
				, String.class
				, String.class
				, RestClientB1.class
				, ReadJson.class
				, ReadJson.class
				, ReadJson.class
				, ReadJson.class
				, ReadJson.class
				, GravarDadosB1Service.class
				, GravarDadosB1Service.class
				, GravarDadosB1Service.class };
		
		return types;
		
	}
	
	private Object[] loadValues() {
		
		Object[] values = { 
				  token
				, apiKey
				, urlBaseB1
				, restClient
				, readJsonTax
				, readJsonItem
				, readJsonItemPrice
				, readJsonItemPriceList
				, readJsonPartner
				, gravarDadosTaxService
				, gravarDadosItemService
				, gravarDadosPartnerService 
			};
		
		return values;
	}

}
