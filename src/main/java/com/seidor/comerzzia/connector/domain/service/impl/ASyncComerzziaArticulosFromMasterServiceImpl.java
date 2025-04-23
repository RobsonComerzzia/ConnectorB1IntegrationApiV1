package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.ItemResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticuloInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.ArticuloCodbarInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.ArticuloUnidadeMedidaInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.CategorizacionInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.FamiliaInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.ImpTipoInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.SeccionInnerInput;
import com.seidor.comerzzia.connector.constants.Constants;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.rest.client.RestClientMaster;

import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ASyncComerzziaArticulosFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<ItemResponseModel>> {

	public ASyncComerzziaArticulosFromMasterServiceImpl(
			ItemB1Repository itemB1Repository,
			ItemPriceB1Repository itemPriceB1Repository, RestClientMaster<ArticulosInput> restClientArticulos,
			RestClientMaster<ArticulosImpuestoInput> restClientArticulosImp,
			RestClientMaster<List<TarifaDetInput>> restClientTarifa) {
		super(itemB1Repository, itemPriceB1Repository, restClientArticulos, restClientArticulosImp, restClientTarifa);
		
	}
	
	@Override
	public CompletableFuture<Void> invokeApiComerzzia(String url, String token) {
		
		log.info("[ASyncComerzziaArticulosFromMasterServiceImpl] - Invocando Api Comerzzia para sincronização de Produtos com o B1.");
		
		List<ArticuloInput> requestList = new ArrayList<ArticuloInput>();
		
		List<ItemResponseModel> items = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(items));
		
		ArticulosInput articulos = ArticulosInput.builder()
				.articulos(requestList)
				.build();
		
		restClientArticulos.execute(articulos, url  + "item/list", token);
		
		return null;
	}
	
	@Override
	public List<ItemResponseModel> getDataFromMasterB1() {

	    List<Tuple> itemTuples = itemB1Repository.findItems();
	    
	    List<ItemResponseModel> itemModel = itemTuples.stream()
	            .map(t -> new ItemResponseModel(
	                    t.get(0, Long.class), 
	                    t.get(1, String.class), 
	                    t.get(2, String.class)
	                    ))
	            .collect(Collectors.toList());
	    
	    return itemModel;
		
	}
	
	private List<ArticuloInput> buildBody(List<ItemResponseModel> articulos){

		
		List<ArticuloInput> requestList = new ArrayList<ArticuloInput>();
		
		for (ItemResponseModel item: articulos) {
			ArticuloInput articulo = ArticuloInput.builder()
					.codart(item.getItemCode().toString())
					.desart(item.getItemName())
					.dtoProveedor(BigDecimal.ZERO)
					.activo(Constants.SIM)
					.desglose1("*")
					.desglose2("*")
					.generico(Constants.NAO)
					.escaparate(Constants.NAO)
					.balanzaTipoArt(Constants.UNIDADE)
					.familia(this.toFillFamily(item))
					.seccion(this.toFillSeccion(item))
					.categorizacion(this.toFillCategorizacion(item))
					.impTipo(this.toFillTaxType(item))
					.articuloUnidadesMedida(this.toFillItemUnitMeansure(item))
					.codbars(this.toFillCodBars(item))
					.build();
			requestList.add(articulo);
		}
		
		return requestList;
		
	}
	
	private FamiliaInnerInput toFillFamily(ItemResponseModel item) {
		
		FamiliaInnerInput familia = FamiliaInnerInput.builder()
				.codfam(null)
				.desfam(null)
				.activo(Constants.SIM)
				.build();
		
		return familia;
	}
	
	private SeccionInnerInput toFillSeccion(ItemResponseModel item) {
		
		SeccionInnerInput seccion = SeccionInnerInput.builder()
				.codseccion("")
				.desseccion("")
				.rutaPreparacion("")
				.activo(Constants.SIM)
				.build();
		
		return seccion;
	}
	
	private CategorizacionInnerInput toFillCategorizacion(ItemResponseModel item) {
		
		CategorizacionInnerInput categorizacion = CategorizacionInnerInput.builder()
				.codcat("")
				.descat("")
				.codcatPadre("")
				.build();
		
		return categorizacion;	
	}
	
	private ImpTipoInnerInput toFillTaxType(ItemResponseModel item) {
		
		ImpTipoInnerInput taxType = ImpTipoInnerInput.builder()
				.codimp("90")
				.build();
		
		return taxType;
	}
	
	private List<ArticuloUnidadeMedidaInnerInput> toFillItemUnitMeansure(ItemResponseModel item) {
		
		List<ArticuloUnidadeMedidaInnerInput> unitsMeansure = new ArrayList<>();
		
		ArticuloUnidadeMedidaInnerInput unitMeansure = ArticuloUnidadeMedidaInnerInput.builder()
				.unidadMedida(Constants.UNIDADE)
				.factorConversion(BigDecimal.ONE)
				.build();
		
		unitsMeansure.add(unitMeansure);
		
		return unitsMeansure;
	}
	
	private List<ArticuloCodbarInnerInput> toFillCodBars(ItemResponseModel item){
		
		List<ArticuloCodbarInnerInput> codesBars = new ArrayList<>();
		
		ArticuloCodbarInnerInput codeBars = ArticuloCodbarInnerInput.builder()
				.codigoBarras(item.getCodeBars())
				.desglose1("*")
				.desglose2("*")
				.dun14(Constants.NAO)
				.factorConversion(BigDecimal.ONE)
				.principal(Constants.NAO)
				.build();
		codesBars.add(codeBars);
				
		return codesBars;
	}

}
