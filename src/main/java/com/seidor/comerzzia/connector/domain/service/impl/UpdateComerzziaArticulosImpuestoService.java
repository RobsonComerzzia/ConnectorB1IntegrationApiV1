package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.abstracts.ConstructorsAbstract;
import com.seidor.comerzzia.connector.api.v1.model.ItemTaxResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticuloImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;

import jakarta.persistence.Tuple;

@Service
public class UpdateComerzziaArticulosImpuestoService extends ConstructorsAbstract {

	public UpdateComerzziaArticulosImpuestoService(
			ItemB1Repository itemB1Repository,
			RestClientMaster<ArticulosInput> restClientArticulos,
			RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp) {
		super(itemB1Repository, restClientArticulos, restClientArticulosImp);

	}

	@Override
	public void invokeApiComerzzia(String url, String token) {
		
		List<ArticuloImpuestoInput> requestList = new ArrayList<ArticuloImpuestoInput>();
		
		List<ItemTaxResponseModel> taxes = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(taxes));
		
		ArticulosImpuestoInput articulos = ArticulosImpuestoInput.builder()
				.articulos(requestList)
				.build();
		
		restClientArticulosImp.execute(articulos, url  + "/taxbystate", token);
		
	}

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
