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
@Schema(description = "Modelo da marca")
public class MarcaModel {
	
	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;

	@Schema(example = "08")
	@NotBlank
	@JsonProperty("brandCode")
	private String codmarca;

	@Schema(example = "Havaianaa")
	@NotBlank
	@JsonProperty("brandDescr")
	private String desmarca;
	
	@Schema(example = "")
	@JsonProperty("factoryCode")
	private String codfab;
	
	@Schema(example = "S")
	@JsonProperty("active")
	private String activo;
	
}
