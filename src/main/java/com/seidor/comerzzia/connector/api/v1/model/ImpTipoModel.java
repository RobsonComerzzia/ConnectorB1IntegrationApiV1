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
@Schema(description = "Modelo do tipo de imposto")
public class ImpTipoModel {
	
	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;

	@Schema(example = "01")
	@NotBlank
	@JsonProperty("taxCode")
	private String codimp;

	@Schema(example = "TRIBUTADA 3.00% CST 00 BASE 100.000")
	@NotBlank
	@JsonProperty("taxDescr")
	private String desimp;
	
	@Schema(example = "1")
	@JsonProperty("summary")
	private Integer summary;
	
	@Schema(example = "")
	@JsonProperty("calculationMethod")
	private String calculationMethod;
	
	@Schema(example = "")
	@JsonProperty("parentTaxCode")
	private String parentCodimp;

	@Schema(example = "")
	@JsonProperty("applicationOrder")
	private Integer applicationOrder;
	
}
