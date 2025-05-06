package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seidor.comerzzia.connector.api.v1.model.ImpTratamientoModel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ArticulosImpuestoModel implements Serializable {


	private static final long serialVersionUID = 1378292729L;
	
	@JsonProperty("data")
	private List<ImpTratamientoModel> data;

}
