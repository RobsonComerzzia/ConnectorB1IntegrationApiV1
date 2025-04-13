package com.seidor.comerzzia.connector.abstracts;

import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.service.SyncComerzziaFromMasterService;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ConstructorsAbstract implements SyncComerzziaFromMasterService {
	
	public ItemB1Repository itemB1Repository;
	
	public RestClientMaster<ArticulosInput> restClientArticulos;
	
	public RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp;

}
