package com.seidor.comerzzia.connector.api.v1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Schema(description = "Modelo de tarifa e preços por produto")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TarifaDetModel {

	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;

	@Schema(example = "100100")
	@NotBlank
	@JsonProperty("itemCode")
	private String codart;

	@Schema(example = "7891079000205")
	@NotBlank
	@JsonProperty("rateCode")
	private String codtar;
	
	@Schema(example = "UN")
	@NotBlank
	@JsonProperty("unitMeasureCode")
	private String unitMeasureCode;

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

	@Schema(example = "10.20")
	@JsonProperty("salesPriceRefWithTaxes")
	private BigDecimal precioVentaRefTotal;
	
	@Schema(example = "N")
	@NotBlank
	@JsonProperty("erased")
	private String borrado;

	@Schema(example = "2024-09-13T18:01:28")
	@JsonProperty("endDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime endDate;

	@Schema(example = "10.0")
	@JsonProperty("discountPercentage")
	private BigDecimal discountPercentage;

	@JsonProperty("supportsPromotionsOfType")
	private String supportsPromotionsOfType;

}
