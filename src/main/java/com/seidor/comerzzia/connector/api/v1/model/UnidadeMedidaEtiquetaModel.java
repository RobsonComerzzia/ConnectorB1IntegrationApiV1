package com.seidor.comerzzia.connector.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Schema(description = "Modelo da unidade de medida por etiqueta")
public class UnidadeMedidaEtiquetaModel {
	
	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;

	@Schema(example = "UN")
	@NotBlank
	@JsonProperty("unitMeasuareCode")
	private String codUmEtiqueta;

	@Schema(example = "")
	@NotBlank
	@JsonProperty("unitMeasuareDescr")
	private String desUmEtiqueta;

	@Schema(example = "UN")
	@NotBlank
	@JsonProperty("labelUnitMeasuare")
	private String desetiqueta;
	
	@Schema(example = "1")
	@NotBlank
	@JsonProperty("unitMeasureQuantity")
	private Long factor;
		

}
