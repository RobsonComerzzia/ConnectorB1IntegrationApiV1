package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seidor.comerzzia.connector.api.v1.model.ImpTratamientoModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Schema(description = "Modelo de input de vinculo de imposto ao produto por estado")
public class ArticulosImpuestoModel implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@JsonProperty("data")
	private List<ImpTratamientoModel> data;

}
