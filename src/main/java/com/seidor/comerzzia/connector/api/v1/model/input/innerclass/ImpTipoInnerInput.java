package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de Input tipo de imposto no item(produto)")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImpTipoInnerInput {
	
	@Schema(example = "01")
	@NotBlank
	@JsonProperty("taxCode")
	private String codimp;

	@Schema(example = "TRIBUTADA 3.00% CST 00 BASE 100.000")
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
