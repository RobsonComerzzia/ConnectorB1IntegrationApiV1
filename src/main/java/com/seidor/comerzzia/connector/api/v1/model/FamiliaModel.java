package com.seidor.comerzzia.connector.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Schema(description = "Modelo da família")
public class FamiliaModel {
	
	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;

	@Schema(example = "01")
	@NotBlank
	@JsonProperty("familyCode")
	private String codfam;
	
	@Schema(example = "Familia 01")
	@NotBlank
	@JsonProperty("familyDescr")
	private String desfam;
	
	@Schema(example = "S")
	@NotBlank
	@JsonProperty("active")
	private String activo;
	
}
