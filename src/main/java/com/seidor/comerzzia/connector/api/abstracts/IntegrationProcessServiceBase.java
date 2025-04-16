package com.seidor.comerzzia.connector.api.abstracts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.seidor.comerzzia.connector.api.v1.model.GuidB1Model;
import com.seidor.comerzzia.connector.api.v1.model.VerifyB1Model;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.GuidB1ModelInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ItemsGravarInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceListInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonPartnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonTaxInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.api.v1.model.input.VerifyB1ModelInput;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.domain.service.OauthService;
import com.seidor.comerzzia.connector.rest.client.RestClientB1;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;
import com.seidor.comerzzia.connector.util.json.ReadJson;

public abstract class IntegrationProcessServiceBase {
	
	@Value("${b1.token}")
	private String token;
	
	@Value("${b1.apiKey}")
	private String apiKey;
	
	@Value("${url.base.b1}")
	private String urlBaseB1;
	
	@Value("${master.api.integracao.item.url}")
	private String urlMasterItem;
	
	@Autowired
	private OauthService oauthService;
	
	@Autowired
	private ReadJson<List<JsonTaxInput>> readJsonTax;
	
	@Autowired
	private ReadJson<List<JsonItemInput>> readJsonItem;
	
	@Autowired
	private ReadJson<List<JsonItemPriceInput>> readJsonItemPrice;
	
	@Autowired
	private RestClientB1<GuidB1ModelInput, GuidB1Model, VerifyB1ModelInput, VerifyB1Model> restClientB1;
	
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
	private ItemPriceB1Repository itemPriceB1Repository;
	
	@Autowired
	private RestClientMaster<ArticulosInput> restClientArticulos;
	
	@Autowired
	private RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp;
	
	@Autowired
	private RestClientMaster<List<TarifaDetInput>> restClientTarifa;
	
	protected Class<?>[] loadTypesConstructorsB1(){
		
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
				, GravarDadosB1Service.class
			};
		
		return types;
		
	}
	
	protected Object[] loadValuesConstructorsB1() {
		
		Object[] values = {
				  token
				, apiKey
				, urlBaseB1
				, restClientB1
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
	
	protected Class<?>[] loadTypesConstructorsComerzzia(){
		
		Class<?>[] typesClassConstructor = {
				  ItemB1Repository.class
				, ItemPriceB1Repository.class
				, RestClientMaster.class
				, RestClientMaster.class
				, RestClientMaster.class
			};
		
		return typesClassConstructor;
		
	}
	
	protected Object[] loadValuesConstructorsComerzzia() {
		
		Object[] valuesClassConstructor = { 
				  itemB1Repository
				, itemPriceB1Repository
				, restClientArticulos
				, restClientArticulosImp
				, restClientTarifa
			};
		
		return valuesClassConstructor;
		
	}
	
	protected Object[] loadValuesParametersComerzzia() {
		
		Object[] valuesParamMethod = { 
				  this.urlMasterItem
				, null//oauthService.getToken()
			};
		
		return valuesParamMethod;
		
	}
	
	protected Class<?>[] loadTypesParametersComerzzia() {
		
		Class<?>[] typesParamMethod = {
				  String.class
				, String.class
			};
		
		return typesParamMethod;
		
	}

}
