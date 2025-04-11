package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Modelo de input de um estoque do produto")
public class AlmaceneArticuloStockInnerInput {
	
	@Schema(example = "0001")
	@NotBlank
	@JsonProperty("whCode")
	private String codalm;
	
	@Schema(example = "100.000")
	@PositiveOrZero
	@JsonProperty("stock")
	private BigDecimal stock;

	@Schema(example = "*")
	@NotBlank
	@JsonProperty("combination1Code")
	private String desglose1;
	
	@Schema(example = "*")
	@NotBlank
	@JsonProperty("combination2Code")
	private String desglose2;

	@Schema(example = "S")
	@NotBlank
	@JsonProperty("active")
	private String activo;
	
}
