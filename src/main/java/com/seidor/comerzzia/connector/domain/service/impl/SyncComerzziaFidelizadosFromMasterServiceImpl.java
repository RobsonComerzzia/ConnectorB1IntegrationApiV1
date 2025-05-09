package com.seidor.comerzzia.connector.domain.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.abstracts.ConstructorsAbstractComerzzia;
import com.seidor.comerzzia.connector.api.v1.model.ArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.CategorizacionModel;
import com.seidor.comerzzia.connector.api.v1.model.DynamicArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.FidelizadoModel;
import com.seidor.comerzzia.connector.api.v1.model.ImpTratamientoModel;
import com.seidor.comerzzia.connector.api.v1.model.PartnerResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.TarifaDetModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.api.v1.model.input.CategorizacionInput;
import com.seidor.comerzzia.connector.api.v1.model.input.DynamicArticuloInput;
import com.seidor.comerzzia.connector.api.v1.model.input.FidelizadoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;
import com.seidor.comerzzia.connector.constants.Constants;
import com.seidor.comerzzia.connector.domain.model.Articulo;
import com.seidor.comerzzia.connector.domain.model.PartnerB1;
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
@Order(6)
@Service
public class SyncComerzziaFidelizadosFromMasterServiceImpl extends ConstructorsAbstractComerzzia<List<PartnerResponseModel>> {

    private final PartnerB1Repository partnerB1Repository;

	public SyncComerzziaFidelizadosFromMasterServiceImpl(TaxB1Repository taxB1Repository,
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
		this.partnerB1Repository = partnerB1Repository;
	}

	@Override
	public void invokeApiComerzzia(String url, String token) {
		
		log.info("[SyncComerzziaFidelizadosFromMasterServiceImpl] - Invocando Api Comerzzia para sincronização de Fidelizados com o B1.");
		
		List<FidelizadoInput> requestList = new ArrayList<FidelizadoInput>();
		
		List<PartnerResponseModel> partners = this.getDataFromMasterB1();
		
		requestList.addAll(this.buildBody(partners));
		
		if (requestList.size() > 0) {
			List<FidelizadoModel> response = restClientFidelizados.execute(requestList, url  + "fidelizados/list", token);
				
			if (response.size() > 0) {
				this.setLastSendDate(response);
				log.info("[SyncComerzziaFidelizadosFromMasterServiceImpl] - {} fidelizados sincronizados com o Comerzzia.", response.size());
			} else {
				log.warn("[SyncComerzziaFidelizadosFromMasterServiceImpl] - Nenhum fidelizado sincronizado com o Comerzzia!");
			}	
			
		} else {
			log.warn("[SyncComerzziaFidelizadosFromMasterServiceImpl] - Nenhum fidelizado sincronizado com o Comerzzia!");
		}
		
	}

	@Override
	public List<PartnerResponseModel> getDataFromMasterB1() {

	    List<Tuple> partnerTuples = partnerB1Repository.findPartners();
	    
	    List<PartnerResponseModel> partnerModel = partnerTuples.stream()
	            .map(t -> new PartnerResponseModel(
	                    t.get(0, String.class),
	                    t.get(1, String.class),
	                    t.get(2, String.class), 
	                    t.get(3, String.class),
	                    t.get(4, String.class),
	                    t.get(5, String.class),
	                    t.get(6, String.class),
	                    t.get(7, String.class),
	                    t.get(8, String.class),
	                    t.get(9, String.class)
	                    ))
	            .collect(Collectors.toList());
	    
	    return partnerModel;
		
	}
	
	private List<FidelizadoInput> buildBody(List<PartnerResponseModel> partners){
		
		List<FidelizadoInput> fidelizados = new ArrayList<>();
		
		for (PartnerResponseModel item: partners) {
			FidelizadoInput fidelizado = FidelizadoInput.builder()
					.nombre(item.getNombre())
					.apellidos(item.getApellidos())
					.domicilio(item.getDomicilio())
					.poblacion(item.getPoblacion())
					.cp(item.getCp())
					.localidad(item.getLocalidad())
					.provincia(item.getProvincia())
					.codpais(Constants.BR)
					.codtipoiden(item.getCnpj() != null ? Constants.CNPJ : item.getCpf() != null ? Constants.CPF : null)
					.documento(item.getCnpj() != null ? item.getCnpj() : item.getCpf() != null ? item.getCpf() : null)
					.codestcivil("")
					.fechaNacimiento(null)
					.observaciones("")
					.sexo("")
					.build();
			fidelizados.add(fidelizado);
		}
		return fidelizados;
		
	}
	
	private void setLastSendDate(List<FidelizadoModel> response) {
		
		if (response.size() > 0) {
			response.stream().forEach(p -> {
				
				try {
					
					Optional<PartnerB1> partner = Optional.empty();
					
					if (p.getDocumento() != null) {
						partner = partnerB1Repository.findByTaxId0OrTaxId4(Utils.formatarCNPJ(p.getDocumento()), Utils.formatarCPF(p.getDocumento()));
						if (!partner.isPresent())
							partner = partnerB1Repository.findByCardName(p.getNombre() + " " + p.getApellidos());
					} else
						partner = partnerB1Repository.findByCardName(p.getNombre() + " " + p.getApellidos());
					
					if (partner.isPresent()) {
						partner.get().setLastSendDate(LocalDateTime.now());
						partnerB1Repository.save(partner.get());
					}	
				} catch (Exception e) {
					log.error("[SyncComerzziaFidelizadosFromMasterServiceImpl] - ERRO: {}", e.getLocalizedMessage());
				}
				
			});
		}
		
	}

}
