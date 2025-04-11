package com.seidor.comerzzia.connector.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.model.GuidB1Model;
import com.seidor.comerzzia.connector.api.v1.model.IntegracaoB1Model;
import com.seidor.comerzzia.connector.api.v1.model.VerifyB1Model;
import com.seidor.comerzzia.connector.api.v1.model.input.GuidB1ModelInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ItemsGravarInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceListInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonPartnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonTaxInput;
import com.seidor.comerzzia.connector.api.v1.model.input.VerifyB1ModelInput;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.domain.service.IntegracaoB1CmzProcessService;
import com.seidor.comerzzia.connector.domain.service.IntegracaoB1CmzService;
import com.seidor.comerzzia.connector.rest.client.RestClientB1;
import com.seidor.comerzzia.connector.util.json.ReadJson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IntegracaoB1ErpToCmzServiceImpl implements IntegracaoB1CmzService<IntegracaoB1Model> {
	
	@Value("${b1.token}")
	private String token;
	
	@Value("${b1.apiKey}")
	private String apiKey;
	
	@Value("${url.base.b1}")
	private String urlBaseB1;
	
	@Value("${master.api.integracao.item.url}")
	private String urlMasterItem = "";
	
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
	private IntegracaoB1CmzProcessService integracaoB1CmzProcessService;
	
	@Override
	public IntegracaoB1Model startProcess() {
		
		IntegracaoB1Model response = IntegracaoB1Model.builder()
				.protocol(UUID.randomUUID().toString())
				.dateTimeRequest(LocalDateTime.now())
				.build();
		
		integracaoB1CmzProcessService.processIntegration(this.loadTypes(), this.loadValues(), urlMasterItem);
		
		return response;
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
