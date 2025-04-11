package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de Input da seção no item(produto)")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeccionInnerInput  {
	
	@Schema(example = "")
	@NotBlank
	@JsonProperty("sessionCode")
	private String codseccion;

	@Schema(example = "")
	@JsonProperty("sessionDescr")
	private String desseccion;
	
	@Schema(example = "S")
	@JsonProperty("active")
	private String activo;
	
	@Schema(example = "preparationRoute")
	@JsonProperty("preparationRoute")
	private String rutaPreparacion;

}
