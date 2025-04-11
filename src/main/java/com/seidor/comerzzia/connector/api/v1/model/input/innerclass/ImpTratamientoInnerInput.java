package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Schema(description = "Modelo de Input da tratamento do imposto no item(produto)")
public class ImpTratamientoInnerInput {
	
	@Schema(example = "124")
	@NotBlank
	@JsonProperty("taxTreatmentId")
	private Long idTratImpuestos;
	
	@Schema(example = "BR-SP")
	@JsonProperty("taxTreatmentCode")
	private String codtratimp;
	
	@Schema(example = "Imposto região de SP")
	@JsonProperty("taxTreatmentDescr")
	private String destratimp;
	
	@Schema(example = "N")
	@JsonProperty("surchargeApplies")
	private String aplicaRecargo;
	
	@Schema(example = "BR")
	@JsonProperty("contryCode")
	private String codpais;
	
	@Schema(example = "SP")
	@JsonProperty("taxRegion")
	private String regionImpuestos;
	
}
