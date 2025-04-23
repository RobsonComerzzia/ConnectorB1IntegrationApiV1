package com.seidor.comerzzia.connector.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticuloInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ASyncComerzziaArticulosFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<ArticuloInput>> {

	public ASyncComerzziaArticulosFromMasterServiceImpl(
			ItemB1Repository itemB1Repository,
			ItemPriceB1Repository itemPriceB1Repository, RestClientMaster<ArticulosInput> restClientArticulos,
			RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp,
			RestClientMaster<List<TarifaDetInput>> restClientTarifa) {
		super(itemB1Repository, itemPriceB1Repository, restClientArticulos, restClientArticulosImp, restClientTarifa);
		
	}
	
	@Override
	public CompletableFuture<Void> invokeApiComerzzia(String url, String token) {
		
		log.info("[ASyncComerzziaArticulosFromMasterServiceImpl] - Invocando Api Comerzzia para sincronização de Produtos com o B1.");
		
		List<ArticuloInput> requestList = new ArrayList<ArticuloInput>();
		
		List<?> items = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(items));
		
		ArticulosInput articulos = ArticulosInput.builder()
				.articulos(requestList)
				.build();
		
		restClientArticulos.execute(articulos, url  + "item/list", token);
		
		return null;
	}
	
	@Override
	public List<ArticuloInput> getDataFromMasterB1() {

		return null;
		
	}
	
	private List<ArticuloInput> buildBody(List<?> articulos){

		return null;
		
	}

}
