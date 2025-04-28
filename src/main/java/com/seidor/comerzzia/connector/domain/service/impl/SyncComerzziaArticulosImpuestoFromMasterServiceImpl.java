package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.ArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.CategorizacionModel;
import com.seidor.comerzzia.connector.api.v1.model.ItemTaxResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.TarifaDetModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticuloImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.CategorizacionInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.domain.model.Articulo;
import com.seidor.comerzzia.connector.domain.repository.CategoryB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;
import com.seidor.comerzzia.connector.rest.client.RestClientMasterReturn;

import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(3)
@Service
public class SyncComerzziaArticulosImpuestoFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<ItemTaxResponseModel>> {

	public SyncComerzziaArticulosImpuestoFromMasterServiceImpl(
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
		
		log.info("[SyncComerzziaArticulosImpuestoFromMasterServiceImpl] - Invocando Api Comerzzia para sincronização de Articulos x Impuesto com o B1.");
		
		List<ArticuloImpuestoInput> requestList = new ArrayList<ArticuloImpuestoInput>();
		
		List<ItemTaxResponseModel> taxes = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(taxes));
		
		if (requestList.size() > 0) {	
			ArticulosImpuestoInput articulos = ArticulosImpuestoInput.builder()
					.articulos(requestList)
					.build();
			
			restClientArticulosImp.execute(articulos, url  + "item/taxbystate", token);
		}
		
	}

	@Override
	public List<ItemTaxResponseModel> getDataFromMasterB1() {

	    List<Tuple> itemTaxTuples = itemB1Repository.findItemTaxes();
	    
	    List<ItemTaxResponseModel> itemTaxModel = itemTaxTuples.stream()
	            .map(t -> new ItemTaxResponseModel(
	                    t.get(0, String.class), 
	                    t.get(1, Long.class), 
	                    t.get(2, String.class),
	                    t.get(3, String.class),
	                    t.get(4, BigDecimal.class)
	                    ))
	            .collect(Collectors.toList());
	    
	    return itemTaxModel;
		
	}
	
	private List<ArticuloImpuestoInput> buildBody(List<ItemTaxResponseModel> taxes){
		
		List<ArticuloImpuestoInput> articulos = new ArrayList<>();
		
		for (ItemTaxResponseModel item: taxes) {
			ArticuloImpuestoInput articulo = ArticuloImpuestoInput.builder()
					.state(item.getState())
					.itemCode(item.getItemCode())
					.ncmCode(item.getNcmCode())
					.cstIcms(item.getCstIcms())
					.icms(item.getIcms())
					.build();
			articulos.add(articulo);
		}
		return articulos;
		
	}

}
