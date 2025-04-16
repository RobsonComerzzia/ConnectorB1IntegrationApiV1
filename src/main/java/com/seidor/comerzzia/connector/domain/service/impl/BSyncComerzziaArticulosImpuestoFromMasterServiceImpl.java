package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.ItemTaxResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticuloImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;

import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BSyncComerzziaArticulosImpuestoFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<ItemTaxResponseModel>> {

	public BSyncComerzziaArticulosImpuestoFromMasterServiceImpl(
			ItemB1Repository itemB1Repository,
			ItemPriceB1Repository itemPriceB1Repository, RestClientMaster<ArticulosInput> restClientArticulos,
			RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp,
			RestClientMaster<List<TarifaDetInput>> restClientTarifa) {
		super(itemB1Repository, itemPriceB1Repository, restClientArticulos, restClientArticulosImp, restClientTarifa);
		
	}

	@Override
	public void invokeApiComerzzia(String url, String token) {
		
		log.info("[SyncComerzziaTarifasFromMasterServiceImpl] - Invocando Api Comerzzia para sincronização de Articulos x Impuesto com o B1.");
		
		List<ArticuloImpuestoInput> requestList = new ArrayList<ArticuloImpuestoInput>();
		
		List<ItemTaxResponseModel> taxes = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(taxes));
		
		ArticulosImpuestoInput articulos = ArticulosImpuestoInput.builder()
				.articulos(requestList)
				.build();
		
		restClientArticulosImp.execute(articulos, url  + "item/taxbystate", token);
		
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
