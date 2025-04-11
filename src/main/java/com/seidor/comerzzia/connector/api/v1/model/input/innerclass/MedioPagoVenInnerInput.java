package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de Input do vencimento do meio de pagamento no item(produto)")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedioPagoVenInnerInput {
	
	@Schema(example = "1")
	@NotBlank
	@JsonProperty("dueDateDataId")
	private Long idMedpagVen;
	
	@JsonProperty("paymentMethod")
	private MedioPagoInnerInput medioPago;
	
	@Schema(example = "CARTAO DE CREDITO 1X")
	@JsonProperty("dueDateDataDescr")
	private String desmedpagVen;
	
	@Schema(example = "1")
	@JsonProperty("numberOfInstallments")
	private Integer numeroVencimientos;
	
	@Schema(example = "0")
	@JsonProperty("cadenceOfDay")
	private Integer diasCadencia;
	
	@Schema(example = "0")
	@JsonProperty("dayTermBetweenInstallments")
	private Integer diasEntreVencimientos;
	
	@Schema(example = "N")
	@JsonProperty("calendarDays")
	private String diasNaturales;
	
	@Schema(example = "S")
	@JsonProperty("active")
	private String activo;
	
		
}
