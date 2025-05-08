package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.ArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.CategorizacionModel;
import com.seidor.comerzzia.connector.api.v1.model.DynamicArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.FidelizadoModel;
import com.seidor.comerzzia.connector.api.v1.model.ImpTratamientoModel;
import com.seidor.comerzzia.connector.api.v1.model.ItemPriceResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.TarifaDetModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.CategorizacionInput;
import com.seidor.comerzzia.connector.api.v1.model.input.DynamicArticuloInput;
import com.seidor.comerzzia.connector.api.v1.model.input.FidelizadoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.domain.model.Articulo;
import com.seidor.comerzzia.connector.domain.model.ItemPriceB1;
import com.seidor.comerzzia.connector.domain.model.ItemPriceListB1;
import com.seidor.comerzzia.connector.domain.repository.CategoryB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceListB1Repository;
import com.seidor.comerzzia.connector.domain.repository.PartnerB1Repository;
import com.seidor.comerzzia.connector.domain.repository.TaxB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;
import com.seidor.comerzzia.connector.rest.client.RestClientMasterReturn;

import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(5)
@Service
public class SyncComerzziaTarifasFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<ItemPriceResponseModel>> {

	public SyncComerzziaTarifasFromMasterServiceImpl(TaxB1Repository taxB1Repository, ItemB1Repository itemB1Repository,
			ItemPriceB1Repository itemPriceB1Repository, ItemPriceListB1Repository itemPriceListB1Repository,
			CategoryB1Repository categoryB1Repository, PartnerB1Repository partnerB1Repository,
			RestClientMaster<List<ArticuloModel>, ArticulosInput> restClientArticulos,
			RestClientMaster<List<ImpTratamientoModel>, ArticulosImpuestoInput> restClientArticulosImp,
			RestClientMaster<List<TarifaDetModel>, List<TarifaDetInput>> restClientTarifa,
			RestClientMasterReturn<List<Articulo>> restClientArticulo,
			RestClientMaster<List<CategorizacionModel>, List<CategorizacionInput>> restClientCategorizacion,
			RestClientMaster<List<DynamicArticuloModel>, List<DynamicArticuloInput>> restClientDynamics,
			RestClientMaster<List<FidelizadoModel>, List<FidelizadoInput>> restClientFidelizados) {
		super(taxB1Repository, itemB1Repository, itemPriceB1Repository, itemPriceListB1Repository, categoryB1Repository,
				partnerB1Repository, restClientArticulos, restClientArticulosImp, restClientTarifa, restClientArticulo,
				restClientCategorizacion, restClientDynamics, restClientFidelizados);
	}

	@Override
	public void invokeApiComerzzia(String url, String token) {
		
		log.info("[SyncComerzziaTarifasFromMasterServiceImpl] - Invocando Api Comerzzia para sincronização de Tarifas com o B1.");
		
		List<TarifaDetInput> requestList = new ArrayList<TarifaDetInput>();
		
		List<ItemPriceResponseModel> prices = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(prices));
		
		List<TarifaDetModel> response = restClientTarifa.execute(requestList, url  + "item/prices/list", token);
		
		if (response.size() > 0) {
			this.setLastSendDate(response);
			log.info("[SyncComerzziaTarifasFromMasterServiceImpl] - {} tarifas sincronizadas com o Comerzzia.", response.size());
		} else {
			log.warn("[SyncComerzziaTarifasFromMasterServiceImpl] - Nenhuma tarifa sincronizada com o Comerzzia!");
		}
		
	}
	
	@Override
	public List<ItemPriceResponseModel> getDataFromMasterB1() {

	    Set<Tuple> itemTaxTuples = itemPriceB1Repository.findItemPrices();
	    
	    List<ItemPriceResponseModel> itemPriceModel = itemTaxTuples.stream()
	            .map(t -> new ItemPriceResponseModel(
	                    t.get(0, String.class), 
	                    t.get(1, String.class),
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
	
	private void setLastSendDate(List<TarifaDetModel> response) {
		
		if (response.size() > 0) {
			response.stream().forEach(tar -> {
				ItemPriceB1.pk_itemPriceB1 pk = new ItemPriceB1.pk_itemPriceB1();
				pk.setPriceList(tar.getCodtar());
				pk.setItemCode(tar.getCodart());
				
				Optional<ItemPriceB1> price = itemPriceB1Repository.findById(pk);
				if (price.isPresent()) {
					price.get().setLastSendDate(LocalDateTime.now());
					itemPriceB1Repository.save(price.get());
				}
				
				ItemPriceListB1.pk_itemPriceListB1 pk2 = new ItemPriceListB1.pk_itemPriceListB1();
				pk2.setPriceList(price.get().getPriceList());
				
				Optional<ItemPriceListB1> pricelist = itemPriceListB1Repository.findById(pk2);
				if (pricelist.isPresent()) {
					pricelist.get().setLastSendDate(LocalDateTime.now());
					itemPriceListB1Repository.save(pricelist.get());
				}
			});
		}
		
	}
	

}
