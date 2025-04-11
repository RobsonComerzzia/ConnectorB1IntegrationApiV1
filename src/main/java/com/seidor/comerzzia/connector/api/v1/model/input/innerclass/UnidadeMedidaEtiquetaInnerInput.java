package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de Input da unidade de medida por etiqueta no item(produto)")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnidadeMedidaEtiquetaInnerInput {
	
	@Schema(example = "UN")
	@NotBlank
	@JsonProperty("unitMeasuareCode")
	private String codUmEtiqueta;

	@Schema(example = "")
	@JsonProperty("unitMeasuareDescr")
	private String desUmEtiqueta;

	@Schema(example = "UN")
	@JsonProperty("labelUnitMeasuare")
	private String desetiqueta;
	
	@Schema(example = "1")
	@JsonProperty("unitMeasureQuantity")
	private Long factor;
	
}
