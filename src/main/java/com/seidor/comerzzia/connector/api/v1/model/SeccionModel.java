package com.seidor.comerzzia.connector.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Schema(description = "Modelo da seção")
public class SeccionModel  {
	
	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;
	
	@Schema(example = "")
	@NotBlank
	@JsonProperty("sessionCode")
	private String codseccion;

	@Schema(example = "")
	@NotBlank
	@JsonProperty("sessionDescr")
	private String desseccion;

	@Schema(example = "S")
	@JsonProperty("active")
	private String activo;

	@Schema(example = "")
	@JsonProperty("preparationRoute")
	private String rutaPreparacion;

}
