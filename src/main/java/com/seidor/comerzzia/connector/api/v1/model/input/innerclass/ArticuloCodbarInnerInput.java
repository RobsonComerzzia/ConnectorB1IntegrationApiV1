package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Schema(description = "Modelo de Input do código de barras no produto")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticuloCodbarInnerInput {

	@Schema(example = "7891079000205")
	@NotBlank
	@JsonProperty("barcode")
	private String codigoBarras;
	
	@Schema(example = "*")
	@NotBlank
	@JsonProperty("combination1Code")
	private String desglose1;
	
	@Schema(example = "*")
	@NotBlank
	@JsonProperty("combination2Code")
	private String desglose2;
	
	@Schema(example = "N")
	@NotBlank
	@JsonProperty("dun14")
	private String dun14;
	
	@Schema(example = "1.000")
	@JsonProperty("conversionFactor")
	private BigDecimal factorConversion;
	
	@Schema(example = "N")
	@NotBlank
	@JsonProperty("main")
	private String principal;

}
