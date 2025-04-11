package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de Input do meio de pagamento no item(produto)")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedioPagoInnerInput {
	
	@Schema(example = "0000")
	@NotBlank
	@JsonProperty("paymentMethodCode")
	private String codmedpag;

	@Schema(example = "DINHEIRO")
	@JsonProperty("paymentMethodDescr")
	private String desmedpag;

	@Schema(example = "S")
	@JsonProperty("counted")
	private String contado;

	@Schema(example = "S")
	@JsonProperty("effective")
	private String efectivo;

	@Schema(example = "N")
	@JsonProperty("creditCard")
	private String tarjetaCredito;

	@JsonProperty("typeOfEffect")
	private TipoEfectoInnerInput tipoEfecto;
	
	@Schema(example = "S")
	@JsonProperty("visibleOnSale")
	private String visibleVenta;

	@Schema(example = "S")
	@JsonProperty("visibleInOnlineStore")
	private String visibleTiendaVirtual;
	
	@Schema(example = "S")
	@JsonProperty("visibleUponPurchase")
	private String visibleCompra;

	@Schema(example = "S")
	@NotBlank
	@JsonProperty("active")
	private String activo;

	@Schema(example = "S")
	@NotBlank
	@JsonProperty("manual")
	private String manual;
	
	@Schema(example = "S")
	@NotBlank
	@JsonProperty("automaticMoneyCounting")
	private String recuentoAutomaticoCaja;

	@Schema(example = "contadoManager")
	@JsonProperty("controlClass")
	private String claseControl;
	
	@Schema(example = "S")
	@JsonProperty("visibleOnRefund")
	private String visibleDevolucion;
	
}
