package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de Input do tipo de efeito no meio de pagto no item(produto)")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipoEfectoInnerInput {

	@Schema(example = "20")
	@NotBlank
	@JsonProperty("effectTypeCode")
	private String codtipoefec;
	
	@Schema(example = "TRANSFERENCIA")
	@JsonProperty("descrEffectType")
	private String destipoefec;
	
	@Schema(example = "S")
	@JsonProperty("remesable")
	private String remesable;
	
	@Schema(example = "N")
	@JsonProperty("automaticDocumentEntry")
	private String entradaDocumentoAutomatica;
	
	@Schema(example = "S")
	@JsonProperty("active")
	private String activo;
}
