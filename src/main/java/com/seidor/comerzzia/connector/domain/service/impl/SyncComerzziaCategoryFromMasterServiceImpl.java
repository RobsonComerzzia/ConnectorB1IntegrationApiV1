package com.seidor.comerzzia.connector.domain.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.ArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.CategorizacionModel;
import com.seidor.comerzzia.connector.api.v1.model.TarifaDetModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.CategorizacionInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.constants.Constants;
import com.seidor.comerzzia.connector.domain.model.Articulo;
import com.seidor.comerzzia.connector.domain.model.CategoryB1;
import com.seidor.comerzzia.connector.domain.repository.CategoryB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;
import com.seidor.comerzzia.connector.rest.client.RestClientMasterReturn;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(1)
@Service
public class SyncComerzziaCategoryFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<CategoryB1>> {
	
	public SyncComerzziaCategoryFromMasterServiceImpl(
			ItemB1Repository itemB1Repository,
			ItemPriceB1Repository itemPriceB1Repository,
			CategoryB1Repository categoryB1Repository,
			RestClientMaster<List<ArticuloModel>, ArticulosInput> restClientArticulos,
			RestClientMaster<ArticulosImpuestoModel, ArticulosImpuestoInput> restClientArticulosImp,
			RestClientMaster<List<TarifaDetModel>, List<TarifaDetInput>> restClientTarifa,
			RestClientMasterReturn<List<Articulo>> restClientArticulo,
			RestClientMaster<List<CategorizacionModel>, List<CategorizacionInput>> restClientCategorizacion) {
		super(itemB1Repository, itemPriceB1Repository, categoryB1Repository, restClientArticulos, restClientArticulosImp, restClientTarifa,
				restClientArticulo, restClientCategorizacion);
	}

	@Override
	public void invokeApiComerzzia(String url, String token) {
		
		log.info("[SyncComerzziaCategoryFromMasterServiceImpl] - Invocando Api Comerzzia para sincronização de Categorias com o B1.");
		
		List<CategorizacionInput> requestList = new ArrayList<CategorizacionInput>();
		
		List<CategoryB1> categories = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(categories));
			
		List<CategorizacionModel> response = restClientCategorizacion.execute(requestList, url  + "category/list", token);
		
		if (response.size() > 0) {
			this.setLastSendDate(response);
			log.info("[SyncComerzziaCategoryFromMasterServiceImpl] - {} categorias sincronizadas com o Comerzzia.", response.size());
		} else {
			log.warn("[SyncComerzziaCategoryFromMasterServiceImpl] - Nenhum categoria sincronizada com o Comerzzia!");
		}

	}
	
	public List<CategoryB1> getDataFromMasterB1() {

	    List<CategoryB1> categories = categoryB1Repository.find();
	    
	    return categories;
		
	}
	
	private List<CategorizacionInput> buildBody(List<CategoryB1> categories){
		
		List<CategorizacionInput> requestList = new ArrayList<CategorizacionInput>();
		
		for (CategoryB1 category: categories) {
			CategorizacionInput item = CategorizacionInput.builder()
					.codcat(category.getCode())
					.descat(category.getName())
					.activo(Constants.SIM)
					.build();
			requestList.add(item);
		}
		
		return requestList;
		
	}
	
	private void setLastSendDate(List<CategorizacionModel> response) {
		
		if (response.size() > 0) {
			response.stream().forEach(cat -> {
				CategoryB1.pk_categoryB1 pk = new CategoryB1.pk_categoryB1();
				pk.setCode(cat.getCodcat());
				
				Optional<CategoryB1> category = categoryB1Repository.findById(pk);
				if (category.isPresent()) {
					category.get().setLastSendDate(LocalDateTime.now());
					categoryB1Repository.save(category.get());
				}
			});
		}
		
	}

}
