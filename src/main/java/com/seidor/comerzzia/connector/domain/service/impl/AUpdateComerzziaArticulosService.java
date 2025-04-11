package com.seidor.comerzzia.connector.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.abstracts.ConstructorsAbstract;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticuloInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;

@Service
public class AUpdateComerzziaArticulosService extends ConstructorsAbstract {

	public AUpdateComerzziaArticulosService(
			
			ItemB1Repository itemB1Repository,
			
			RestClientMaster<ArticulosInput> restClientArticulos,
			
			RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp
			
			) {
		super(itemB1Repository, restClientArticulos, restClientArticulosImp);
	}

	@Override
	public void invokeApiComerzzia(String url) {
		
		List<ArticuloInput> requestList = new ArrayList<ArticuloInput>();
		
		List<?> items = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(items));
		
		ArticulosInput articulos = ArticulosInput.builder()
				.articulos(requestList)
				.build();
		
		String token = null;
		
		restClientArticulos.execute(articulos, url  + "/list", token);
		
	}
	
	public List<ArticuloInput> getDataFromMasterB1() {

		return null;
		
	}
	
	private List<ArticuloInput> buildBody(List<?> articulos){

		return null;
		
	}

}
