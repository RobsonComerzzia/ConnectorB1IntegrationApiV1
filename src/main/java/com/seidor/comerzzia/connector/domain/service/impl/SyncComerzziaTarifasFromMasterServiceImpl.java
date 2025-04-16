package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SyncComerzziaTarifasFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<ItemPriceResponseModel>> {

	public SyncComerzziaTarifasFromMasterServiceImpl(
			ItemB1Repository itemB1Repository,
			ItemPriceB1Repository itemPriceB1Repository, 
			RestClientMaster<ArticulosInput> restClientArticulos,
			RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp,
			RestClientMaster<List<TarifaDetInput>> restClientTarifa) {
		super(itemB1Repository, itemPriceB1Repository, restClientArticulos, restClientArticulosImp, restClientTarifa);

	}

	@Override
	public void invokeApiComerzzia(String url, String token) {
		
		log.info("[SyncComerzziaTarifasFromMasterServiceImpl] - Invocando Api Comerzzia para sincronização de Tarifas com o B1.");
		
		List<TarifaDetInput> requestList = new ArrayList<TarifaDetInput>();
		
		List<ItemPriceResponseModel> prices = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(prices));
		
		restClientTarifa.execute(requestList, url  + "item/prices/list", token);
		
	}
	
	@Override
	public List<ItemPriceResponseModel> getDataFromMasterB1() {

	    Set<Tuple> itemTaxTuples = itemPriceB1Repository.findItemPrices();
	    
	    List<ItemPriceResponseModel> itemPriceModel = itemTaxTuples.stream()
	            .map(t -> new ItemPriceResponseModel(
	                    t.get(0, Long.class), 
	                    t.get(1, Long.class),
	                    t.get(2, LocalDateTime.class), 
	                    t.get(3, BigDecimal.class),
	                    t.get(4, BigDecimal.class)
	                    ))
	            .collect(Collectors.toList());
	    
	    return itemPriceModel;
		
	}
	
	private List<TarifaDetInput> buildBody(List<ItemPriceResponseModel> prices){
		
		List<TarifaDetInput> requestList = new ArrayList<>();
		
		for (ItemPriceResponseModel item: prices) {
			TarifaDetInput price = TarifaDetInput.builder()
				.codart(item.getItemCode().toString())
				.codtar(item.getCodTar().toString())
				.desglose1("*")
				.desglose2("*")
				.fechaInicio(item.getValidFrom() != null ? item.getValidFrom() : LocalDateTime.now())
				.precioCusto(item.getCostPrice() != null ? item.getCostPrice() : new BigDecimal("0.01"))
				.factorMarcaje(BigDecimal.ZERO)
				.precioVenta(item.getPrice())
				.precioTotal(item.getPrice())
				.precioVentaRef(item.getPrice())
				.precioVentaRefTotal(item.getPrice())
				.build();
			requestList.add(price);
		}
		return requestList;
		
	}
	

}
