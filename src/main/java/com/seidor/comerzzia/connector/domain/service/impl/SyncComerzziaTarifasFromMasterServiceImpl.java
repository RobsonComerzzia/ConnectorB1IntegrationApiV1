package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.ItemPriceResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;

import jakarta.persistence.Tuple;

@Service
public class SyncComerzziaTarifasFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<ItemPriceResponseModel>> {

	public SyncComerzziaTarifasFromMasterServiceImpl(
			ItemB1Repository itemB1Repository,
			ItemPriceB1Repository itemPriceB1Repository, RestClientMaster<ArticulosInput> restClientArticulos,
			RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp,
			RestClientMaster<List<TarifaDetInput>> restClientTarifa) {
		super(itemB1Repository, itemPriceB1Repository, restClientArticulos, restClientArticulosImp, restClientTarifa);

	}

	@Override
	public void invokeApiComerzzia(String url, String token) {
		
		List<TarifaDetInput> requestList = new ArrayList<TarifaDetInput>();
		
		List<ItemPriceResponseModel> prices = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(prices));
		
		restClientTarifa.execute(requestList, url  + "/price/list", token);
		
	}
	
	@Override
	public List<ItemPriceResponseModel> getDataFromMasterB1() {

	    List<Tuple> itemTaxTuples = itemPriceB1Repository.findItemPrices();
	    
	    List<ItemPriceResponseModel> itemPriceModel = itemTaxTuples.stream()
	            .map(t -> new ItemPriceResponseModel(
	                    t.get(0, BigInteger.class), 
	                    t.get(1, LocalDateTime.class), 
	                    t.get(2, BigDecimal.class),
	                    t.get(3, BigDecimal.class)
	                    ))
	            .collect(Collectors.toList());
	    
	    return itemPriceModel;
		
	}
	
	private List<TarifaDetInput> buildBody(List<ItemPriceResponseModel> prices){
		
		List<TarifaDetInput> requestList = new ArrayList<>();
		
		for (ItemPriceResponseModel item: prices) {
			TarifaDetInput price = TarifaDetInput.builder()
				.codart(item.getItemCode().toString())
				.codtar(null)
				.desglose1("*")
				.desglose2("*")
				.fechaInicio(item.getValidFrom())
				.precioCusto(item.getCostPrice())
				.factorMarcaje(BigDecimal.ZERO)
				.precioVenta(item.getPrice())
				.precioTotal(BigDecimal.ZERO)
				.precioVentaRef(BigDecimal.ZERO)
				.precioVentaRefTotal(BigDecimal.ZERO)
				.build();
			requestList.add(price);
		}
		return requestList;
		
	}
	

}
