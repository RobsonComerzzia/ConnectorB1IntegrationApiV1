package com.seidor.comerzzia.connector.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.abstracts.ConstructorsAbstractIntegrarB1LoadDataService;
import com.seidor.comerzzia.connector.api.v1.model.GuidB1Model;
import com.seidor.comerzzia.connector.api.v1.model.VerifyB1Model;
import com.seidor.comerzzia.connector.api.v1.model.input.GuidB1ModelInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ItemsGravarInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceListInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonPartnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonTaxInput;
import com.seidor.comerzzia.connector.api.v1.model.input.VerifyB1ModelInput;
import com.seidor.comerzzia.connector.constants.Constants;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.rest.client.RestClientB1;
import com.seidor.comerzzia.connector.util.json.ReadJson;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IntegrarTaxB1LoadDataServiceImpl extends ConstructorsAbstractIntegrarB1LoadDataService {


	public IntegrarTaxB1LoadDataServiceImpl(
			@Value("${b1.token}")
			String token, 
			@Value("${b1.apiKey}")
			String apiKey,
			@Value("${url.base.b1}")
			String urlBaseB1,
			RestClientB1<GuidB1ModelInput, GuidB1Model, VerifyB1ModelInput, VerifyB1Model> restClient,
			ReadJson<List<JsonTaxInput>> readJsonTax, ReadJson<List<JsonItemInput>> readJsonItem,
			ReadJson<List<JsonItemPriceInput>> readJsonItemPrice,
			ReadJson<List<JsonItemPriceListInput>> readJsonItemPriceList,
			ReadJson<List<JsonPartnerInput>> readJsonPartner,
			GravarDadosB1Service<List<JsonTaxInput>> gravarDadosTaxService,
			GravarDadosB1Service<ItemsGravarInput> gravarDadosItemService,
			GravarDadosB1Service<List<JsonPartnerInput>> gravarDadosPartnerService) {
		super(token, apiKey, urlBaseB1, restClient, readJsonTax, readJsonItem, readJsonItemPrice, readJsonItemPriceList,
				readJsonPartner, gravarDadosTaxService, gravarDadosItemService, gravarDadosPartnerService);
	}

	@Override
	public void loadData() {
		
		log.info("[IntegrarB1ProcessServiceImpl] - Iniciando processamento de Tax");
		
		GuidB1ModelInput inputGuid = GuidB1ModelInput.builder()
				.apiKey(apiKey)
				.token(token)
				.build();
		
		GuidB1Model guidModel = restClient.getGuid(inputGuid, urlBaseB1 + "/tax");
		
		if (guidModel.getGuid() != null) {
			
			VerifyB1ModelInput inputVerify = VerifyB1ModelInput.builder()
				.apiKey(apiKey)
				.guid(guidModel.getGuid())
				.build();
			
			VerifyB1Model verifyModel = null;
			
			verifyModel = restClient.getData(inputVerify, urlBaseB1 + "/tax");
			
			if (verifyModel.getStatus().equals(Constants.PROCESSED)) {
				List<JsonTaxInput> taxes = readJsonTax.jsonToObject(verifyModel.getFileName());
				
				taxes.stream().forEach(input -> input.setGuid(guidModel.getGuid()));
				
				taxes.removeIf(tax -> tax.getState() == null);
				
				if (taxes.size() > 0)
					this.gravarDadosTaxService.gravar(taxes);	
			}
			
		}
		
		log.info("[IntegrarB1ProcessServiceImpl] - Fim do processamento de Tax");
		
	}

}
