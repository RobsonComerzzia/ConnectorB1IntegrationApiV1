package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Schema(description = "Modelo do tratamento do imposto")
public class ImpTratamientoModel implements Serializable {
	
	private static final long serialVersionUID = 1484938370L;

	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;
	
	@Schema(example = "0138302811")
	@NotBlank
	@JsonProperty("codart")
	private String codart;	

	@Schema(example = "124")
	@NotBlank
	@JsonProperty("taxTreatmentId")
	private Long idTratImpuestos;
	
	@Schema(example = "BR-SP")
	@NotBlank
	@JsonProperty("taxTreatmentCode")
	private String codtratimp;
	
	@Schema(example = "Imposto região de SP")
	@NotBlank
	@JsonProperty("taxTreatmentDescr")
	private String destratimp;
	
	@Schema(example = "BR")
	@NotBlank
	@JsonProperty("contryCode")
	private String codpais;
	
	@Schema(example = "SP")
	@NotBlank
	@JsonProperty("taxRegion")
	private String regionImpuestos;
	
}
