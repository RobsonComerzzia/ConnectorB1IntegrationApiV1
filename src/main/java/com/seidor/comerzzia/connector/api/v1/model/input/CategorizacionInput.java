package com.seidor.comerzzia.connector.api.v1.model.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de input da categoria")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorizacionInput {

	@Schema(example = "0101")
	@NotBlank
	@JsonProperty("categoryCode")
	private String codcat;
	
	@Schema(example = "CATEGORIA 01")
	@NotBlank
	@JsonProperty("categoryDescr")
	private String descat;
	
	@Schema(example = "S")
	@NotBlank
	@JsonProperty("active")
	private String activo;
	
	@Schema(example = "01")
	@JsonProperty("parentCategoryCode")
	private String codcatPadre;

}
