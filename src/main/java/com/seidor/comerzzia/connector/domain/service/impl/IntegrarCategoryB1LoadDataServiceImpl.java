package com.seidor.comerzzia.connector.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractIntegrarB1LoadData;
import com.seidor.comerzzia.connector.api.v1.model.GuidB1Model;
import com.seidor.comerzzia.connector.api.v1.model.VerifyB1Model;
import com.seidor.comerzzia.connector.api.v1.model.input.GuidB1ModelInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ItemsGravarInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonCategoryInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceListInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonPartnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonTaxInput;
import com.seidor.comerzzia.connector.api.v1.model.input.VerifyB1ModelInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.JsonCategoryInnerInput;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.rest.client.RestClientB1Api;
import com.seidor.comerzzia.connector.rest.client.RestClientB1Json;
import com.seidor.comerzzia.connector.util.json.ReadJson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IntegrarCategoryB1LoadDataServiceImpl  extends ConstructorsAbstractIntegrarB1LoadData {

	public IntegrarCategoryB1LoadDataServiceImpl(
			@Value("${b1.token}")
			String token, 
			@Value("${b1.apiKey}")
			String apiKey,
			@Value("${url.base.b1}")
			String urlBaseB1,
			RestClientB1Json<GuidB1ModelInput, GuidB1Model, VerifyB1ModelInput, VerifyB1Model> restClient,
			RestClientB1Api<JsonCategoryInput> restClientApi,
			ReadJson<List<JsonTaxInput>> readJsonTax, ReadJson<List<JsonItemInput>> readJsonItem,
			ReadJson<List<JsonItemPriceInput>> readJsonItemPrice,
			ReadJson<List<JsonItemPriceListInput>> readJsonItemPriceList,
			ReadJson<List<JsonPartnerInput>> readJsonPartner,
			GravarDadosB1Service<List<JsonTaxInput>> gravarDadosTaxService,
			GravarDadosB1Service<ItemsGravarInput> gravarDadosItemService,
			GravarDadosB1Service<List<JsonPartnerInput>> gravarDadosPartnerService,
			GravarDadosB1Service<List<JsonCategoryInnerInput>> gravarDadosCategoryService) {
		super(token, apiKey, urlBaseB1, restClient, restClientApi, readJsonTax, readJsonItem, readJsonItemPrice,
				readJsonItemPriceList, readJsonPartner, gravarDadosTaxService, gravarDadosItemService,
				gravarDadosPartnerService, gravarDadosCategoryService);
	
	}

	@Override
	public void loadData() {
		
		log.info("[IntegrarB1ProcessServiceImpl] - Iniciando processamento de Category");
		
		GuidB1ModelInput inputGuid = GuidB1ModelInput.builder()
				.apiKey(apiKey)
				.token(token)
				.build();
		
		JsonCategoryInput response = null;
		
		try {
			response = restClientApi.getData(inputGuid, urlBaseB1 + "/category/data");
		} catch (Exception e) {
			log.error("[IntegrarB1ProcessServiceImpl] - ERRO: {}", e.getLocalizedMessage());
		}
			
		if (response.getData().size() > 0) {
			this.gravarDadosCategoryService.gravar(response.getData());
			//this.setReceived(guidModel);
			log.info("[IntegrarB1ProcessServiceImpl] - Fim do processamento de Category");
		} else {
			log.warn("[IntegrarB1ProcessServiceImpl] - Não há dados para processamento de Category");
		}

		
	}

}
