package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.ArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.CategorizacionModel;
import com.seidor.comerzzia.connector.api.v1.model.DynamicArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.DynamicModel;
import com.seidor.comerzzia.connector.api.v1.model.FidelizadoModel;
import com.seidor.comerzzia.connector.api.v1.model.ImpTratamientoModel;
import com.seidor.comerzzia.connector.api.v1.model.ItemTaxResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.TarifaDetModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.CategorizacionInput;
import com.seidor.comerzzia.connector.api.v1.model.input.DynamicArticuloInput;
import com.seidor.comerzzia.connector.api.v1.model.input.DynamicInput;
import com.seidor.comerzzia.connector.api.v1.model.input.FidelizadoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.constants.Constants;
import com.seidor.comerzzia.connector.domain.model.Articulo;
import com.seidor.comerzzia.connector.domain.model.TaxB1;
import com.seidor.comerzzia.connector.domain.repository.CategoryB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceListB1Repository;
import com.seidor.comerzzia.connector.domain.repository.PartnerB1Repository;
import com.seidor.comerzzia.connector.domain.repository.TaxB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;
import com.seidor.comerzzia.connector.rest.client.RestClientMasterReturn;
import com.seidor.comerzzia.connector.util.Utils;

import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(4)
@Service
public class SyncComerzziaDynamicsFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<ItemTaxResponseModel>> {

	public SyncComerzziaDynamicsFromMasterServiceImpl(TaxB1Repository taxB1Repository,
			ItemB1Repository itemB1Repository, ItemPriceB1Repository itemPriceB1Repository,
			ItemPriceListB1Repository itemPriceListB1Repository, CategoryB1Repository categoryB1Repository,
			PartnerB1Repository partnerB1Repository,
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
		
		log.info("[SyncComerzziaDynamicsFromMasterServiceImpl] - Invocando Api Comerzzia para sincronização de Dynamics com o B1.");
		
		List<DynamicArticuloInput> requestList = new ArrayList<DynamicArticuloInput>();
		
		List<ItemTaxResponseModel> prices = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(prices));
		
		if (requestList.size() > 0) {
			List<DynamicArticuloModel> response = restClientDynamics.execute(requestList, url  + "item/dynamics", token);
			
			if (response.size() > 0) {
				this.setLastSendDate(response);
				log.info("[SyncComerzziaDynamicsFromMasterServiceImpl] - {} dynamics sincronizadas com o Comerzzia.", response.size());
			} else {
				log.warn("[SyncComerzziaDynamicsFromMasterServiceImpl] - Nenhuma dynamic sincronizada com o Comerzzia!");
			}	
		} else {
			log.warn("[SyncComerzziaDynamicsFromMasterServiceImpl] - Nenhuma dynamic sincronizada com o Comerzzia!");
		}
		
	}
	
	@Override
	public List<ItemTaxResponseModel> getDataFromMasterB1() {

	    List<Tuple> itemTaxTuples = itemB1Repository.findItemTaxesDynamics();
	    
	    List<ItemTaxResponseModel> itemTaxModel = itemTaxTuples.stream()
	            .map(t -> new ItemTaxResponseModel(
	                    t.get(0, String.class), 
	                    t.get(1, String.class), 
	                    t.get(2, String.class),
	                    t.get(3, BigDecimal.class),
	                    t.get(4, BigDecimal.class),
	                    t.get(5, BigDecimal.class),
	                    t.get(6, String.class),
	                    t.get(7, String.class),
	                    t.get(8, String.class),
	                    t.get(9, String.class),
	                    t.get(10, String.class),
	                    t.get(11, String.class)
	                    ))
	            .collect(Collectors.toList());
	    
	    return itemTaxModel;
		
	}
	
	private List<DynamicArticuloInput> buildBody(List<ItemTaxResponseModel> prices){
		
		List<DynamicArticuloInput> requestList = new ArrayList<>();
		
		for (ItemTaxResponseModel item: prices) {
			DynamicArticuloInput dynamic = DynamicArticuloInput.builder()
				.codart(item.getItemCode())
				.dynamics(this.buildDynamicInputs(item))
				.build();
			requestList.add(dynamic);
		}
		return requestList;
		
	}
	
	private List<DynamicInput> buildDynamicInputs(ItemTaxResponseModel item){
		
		List<DynamicInput> dynamics = new ArrayList<>();
		
		Map<String, String> map = new HashMap<>();
		
		map.put(Constants.TAX_ALIQUOTA_PIS, Utils.formatNumber(item.getPis()));
		map.put(Constants.TAX_ALIQUOTA_COFINS, Utils.formatNumber(item.getCofins()));
		map.put(Constants.TAX_CARGA_TRIB, "0.00");
		map.put(Constants.TAX_CARGA_TRIB_EST, "0.00");
		map.put(Constants.TAX_CARGA_TRIB_MUN, "0.00");
		map.put(Constants.TAX_CEST, Utils.cleanString(item.getCest()));
		map.put(Constants.TAX_CODIGO_ORIGEM, item.getProductSrc());
		map.put(Constants.TAX_CST_COFINS, item.getCstCofins());
		map.put(Constants.TAX_CST_PIS, item.getCstPis());
		map.put(Constants.TAX_NCM, item.getNcmCode());
		
		map.forEach((key, value) -> {
			DynamicInput dynamic = DynamicInput.builder()
					.key(key)
					.value(value)
					.build();
			dynamics.add(dynamic);
		});
		
		return dynamics;
	}
	
	private void setLastSendDate(List<DynamicArticuloModel> response) {
		
		if (response.size() > 0) {
			response.stream().forEach(dynamicArt -> {
				
				Optional<DynamicModel> dynamicResult = dynamicArt.getDynamics().stream().filter(dynamic -> dynamic.getKey().equals(Constants.TAX_NCM)).findFirst();
				
				dynamicResult.ifPresent(item -> {
					
					List<TaxB1> taxB1List = taxB1Repository.findByNcm(item.getValue());
					if (taxB1List.size() > 0) {
						taxB1List.forEach(tax -> tax.setLastSendDate(LocalDateTime.now()));
						taxB1Repository.saveAll(taxB1List);
					}	
					
				});
				
			});
		}
		
	}

}
