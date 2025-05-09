package com.seidor.comerzzia.connector.api.v1.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Modelo de input de propriedades dinâmicas por produto")
public class DynamicArticuloModel {

	@Schema(example = "100103")
	@NotBlank
	@JsonProperty("itemCode")
	private String codart;
	
	@JsonProperty("dynamicProperties")
	private List<DynamicModel> dynamics;
}
