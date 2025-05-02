package com.seidor.comerzzia.connector.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
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
import com.seidor.comerzzia.connector.api.v1.model.input.SetReceivedB1Input;
import com.seidor.comerzzia.connector.api.v1.model.input.VerifyB1ModelInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.JsonCategoryInnerInput;
import com.seidor.comerzzia.connector.constants.Constants;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.rest.client.RestClientB1Api;
import com.seidor.comerzzia.connector.rest.client.RestClientB1Json;
import com.seidor.comerzzia.connector.util.json.ReadJson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(1)
@Service
public class IntegrarItemB1LoadDataServiceImpl extends ConstructorsAbstractIntegrarB1LoadData {
	
	public IntegrarItemB1LoadDataServiceImpl(
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
		
		log.info("[IntegrarB1ProcessServiceImpl] - Iniciando processamento de Item");
		
		GuidB1ModelInput inputGuid = GuidB1ModelInput.builder()
				.apiKey(apiKey)
				.token(token)
				.build();
		
		GuidB1Model guidModel = restClient.getGuid(inputGuid, urlBaseB1 + "/Item");
		
		if (guidModel.getGuid() != null) {
			
			VerifyB1ModelInput inputVerify = VerifyB1ModelInput.builder()
				.apiKey(apiKey)
				.guid(guidModel.getGuid())
				.build();
			
			VerifyB1Model verifyModel = null;
			
			verifyModel = restClient.getData(inputVerify, urlBaseB1 + "/Item");
			
			if (!verifyModel.getStatus().equals(Constants.NO_DATA)) {
				List<JsonItemInput> items = readJsonItem.jsonToObject(verifyModel.getData().getFileItem());
				
				List<JsonItemPriceInput> itemsPrice = readJsonItemPrice.jsonToObject(verifyModel.getData().getFilePrice());
				
				List<JsonItemPriceListInput> itemsPriceList = readJsonItemPriceList.jsonToObject(verifyModel.getData().getFileList());
				
				items.stream().forEach(input -> input.setGuid(guidModel.getGuid()));
				
				itemsPrice.stream().forEach(input -> input.setGuid(guidModel.getGuid()));
				
				itemsPriceList.stream().forEach(input -> input.setGuid(guidModel.getGuid()));
				
				ItemsGravarInput itemsGravar = ItemsGravarInput.builder()
						.items(items)
						.itemsPrice(itemsPrice)
						.itemsPriceList(itemsPriceList)
						.build();
				
				this.gravarDadosItemService.gravar(itemsGravar);
				
				//this.setReceived(guidModel);
				log.info("[IntegrarB1ProcessServiceImpl] - Fim do processamento de Item");
			} else {
				log.warn("[IntegrarB1ProcessServiceImpl] - Não há dados para processamento de Item");
			}
			
		}
		
	}
	
	private void setReceived(GuidB1Model guidModel) {
		
		SetReceivedB1Input inputSetReceived = SetReceivedB1Input.builder()
			.apiKey(apiKey)
			.guid(guidModel.getGuid())
			.build();

		try {
			restClient.setReceived(inputSetReceived, urlBaseB1 + "/Item");
		} catch (Exception e) {
			log.error("[IntegrarB1ProcessServiceImpl] - Falha ao realizar o SetReceived para o guid {}. Motivo: {}", inputSetReceived.getGuid(), e.getMessage());
		}
		
	}

}
