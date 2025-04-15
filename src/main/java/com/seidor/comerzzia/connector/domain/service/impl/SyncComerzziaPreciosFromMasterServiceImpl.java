package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.ItemPriceResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;

import jakarta.persistence.Tuple;

@Service
public class SyncComerzziaPreciosFromMasterServiceImpl extends ConstructorsAbstractComerzzia {

	public SyncComerzziaPreciosFromMasterServiceImpl(
			ItemB1Repository itemB1Repository,
			ItemPriceB1Repository itemPriceB1Repository, RestClientMaster<ArticulosInput> restClientArticulos,
			RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp) {
		super(itemB1Repository, itemPriceB1Repository, restClientArticulos, restClientArticulosImp);

	}

	@Override
	public void invokeApiComerzzia(String url, String token) {
		
		
		
	}
	
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
	
	

}
