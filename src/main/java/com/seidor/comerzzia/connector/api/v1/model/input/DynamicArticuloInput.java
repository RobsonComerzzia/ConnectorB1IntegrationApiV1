package com.seidor.comerzzia.connector.api.v1.model.input;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Modelo de input de propriedades dinâmicas por produto")
public class DynamicArticuloInput {

	@Schema(example = "100103")
	@NotBlank
	@JsonProperty("itemCode")
	private String codart;
	
	@JsonProperty("dynamicProperties")
	private List<DynamicInput> dynamics;
}
