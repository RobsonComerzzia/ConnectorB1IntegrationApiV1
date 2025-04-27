package com.seidor.comerzzia.connector.api.abstracts;

import java.util.List;

import com.seidor.comerzzia.connector.api.v1.model.CategorizacionModel;
import com.seidor.comerzzia.connector.api.v1.model.TarifaDetModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosModel;
import com.seidor.comerzzia.connector.api.v1.model.input.CategorizacionInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.domain.model.Articulo;
import com.seidor.comerzzia.connector.domain.repository.CategoryB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.domain.service.SyncComerzziaFromMasterService;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;
import com.seidor.comerzzia.connector.rest.client.RestClientMasterReturn;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ConstructorsAbstractComerzzia<T> implements SyncComerzziaFromMasterService<T> {
	
	public ItemB1Repository itemB1Repository;
	
	public ItemPriceB1Repository itemPriceB1Repository;
	
	public CategoryB1Repository categoryB1Repository; 
	
	public RestClientMaster<ArticulosModel, ArticulosInput> restClientArticulos;
	
	public RestClientMaster<ArticulosImpuestoModel, ArticulosImpuestoInput> restClientArticulosImp;
	
	public RestClientMaster<List<TarifaDetModel>, List<TarifaDetInput>> restClientTarifa;
	
	public RestClientMasterReturn<List<Articulo>> restClientArticulo;
	
	public RestClientMaster<List<CategorizacionModel>, List<CategorizacionInput>> restClientCategorizacion;

}
