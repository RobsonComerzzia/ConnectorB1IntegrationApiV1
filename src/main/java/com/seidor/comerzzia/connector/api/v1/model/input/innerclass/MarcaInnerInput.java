package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de Input da marca no item(produto)")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarcaInnerInput {
	
	@Schema(example = "08")
	@NotBlank
	@JsonProperty("brandCode")
	private String codmarca;

	@Schema(example = "Havaianas")
	@JsonProperty("brandDescr")
	private String desmarca;
	
	@Schema(example = "")
	@JsonProperty("factoryCode")
	private String codfab;
	
	@Schema(example = "")
	@JsonProperty("active")
	private String activo;
	
}
