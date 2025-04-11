package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Schema(description = "Modelo de Input de tarifas e preços")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class TarifaDetInnerInput {

	@Schema(example = "7891079000205")
	@NotBlank
	@JsonProperty("rateCode")
	private String codtar;
	
	@Schema(example = "*")
	@NotBlank
	@JsonProperty("combination1Code")
	private String desglose1;
	
	@Schema(example = "*")
	@NotBlank
	@JsonProperty("combination2Code")
	private String desglose2;

	@Schema(example = "2024-09-13T18:01:28")
	@JsonProperty("startDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaInicio;

	@Schema(example = "1.20")
	@JsonProperty("unitCostPrice")
	private BigDecimal precioCusto;

	@Schema(example = "3.75")
	@JsonProperty("profitFactor")
	private BigDecimal factorMarcaje;

	@Schema(example = "10.20")
	@PositiveOrZero
	@JsonProperty("salesPrice")
	private BigDecimal precioVenta;

	@Schema(example = "10.20")
	@PositiveOrZero
	@JsonProperty("salesPriceWithTaxes")
	private BigDecimal precioTotal;

	@Schema(example = "9.10")
	@JsonProperty("salesPriceRef")
	private BigDecimal precioVentaRef;

	@Schema(example = "9.10")
	@JsonProperty("salesPriceRefWithTaxes")
	private BigDecimal precioVentaRefTotal;

	
}
