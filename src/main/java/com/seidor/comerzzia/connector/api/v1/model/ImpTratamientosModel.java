package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImpTratamientosModel implements Serializable {

	private static final long serialVersionUID = 13235422527L;
	
	@JsonProperty("data")
	private List<ImpTratamientoModel> data;

}
