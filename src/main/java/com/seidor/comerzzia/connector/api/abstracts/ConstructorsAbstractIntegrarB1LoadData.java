package com.seidor.comerzzia.connector.api.abstracts;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

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
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.domain.service.IntegrarB1LoadDataService;
import com.seidor.comerzzia.connector.rest.client.RestClientB1;
import com.seidor.comerzzia.connector.util.json.ReadJson;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ConstructorsAbstractIntegrarB1LoadData implements IntegrarB1LoadDataService  {
	
	@Value("${b1.token}")
	public String token; 
	
	@Value("${b1.apiKey}")
	public String apiKey;
	
	@Value("${url.base.b1}")
	public String urlBaseB1;
	
	public RestClientB1<GuidB1ModelInput, GuidB1Model, VerifyB1ModelInput, VerifyB1Model> restClient;
	
	public ReadJson<List<JsonTaxInput>> readJsonTax;
	
	public ReadJson<List<JsonItemInput>> readJsonItem;
	
	public ReadJson<List<JsonItemPriceInput>> readJsonItemPrice;
	
	public ReadJson<List<JsonItemPriceListInput>> readJsonItemPriceList;
	
	public ReadJson<List<JsonPartnerInput>> readJsonPartner;
	
	public GravarDadosB1Service<List<JsonTaxInput>> gravarDadosTaxService;
	
	public GravarDadosB1Service<ItemsGravarInput> gravarDadosItemService;
	
	public GravarDadosB1Service<List<JsonPartnerInput>> gravarDadosPartnerService;
}
