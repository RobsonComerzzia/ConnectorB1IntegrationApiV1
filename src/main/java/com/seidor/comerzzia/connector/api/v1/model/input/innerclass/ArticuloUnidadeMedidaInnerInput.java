package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de input da unidade de medida")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticuloUnidadeMedidaInnerInput {

	@Schema(example = "CX")
	@NotBlank
	@JsonProperty("unitMeasureCode")
	private String unidadMedida;
	
	@Schema(example = "12")
	@Positive
	@JsonProperty("conversionFactor")
	private BigDecimal factorConversion;
	
	@Schema(example = "1")
	@JsonProperty("height")
	private Long alto;
	
	@Schema(example = "1")
	@JsonProperty("width")
	private Long ancho;
	
	@Schema(example = "2")
	@JsonProperty("depth")
	private Long fondo;
	
	@Schema(example = "1.5")
	@JsonProperty("weight")
	private BigDecimal peso;
	
	@Schema(example = "1")
	@JsonProperty("prioritySalesUnit")
	private Integer prioridadUnidadVenta;
	
	@Schema(example = "1")
	@JsonProperty("minQuantity")
	private BigDecimal cantidadMinima;
	
	@Schema(example = "10")
	@JsonProperty("maxQuantity")
	private BigDecimal cantidadMaxima;
	
	@Schema(example = "10")
	@JsonProperty("multipleQuantity")
	private BigDecimal multiplosCantidad;
	
}
