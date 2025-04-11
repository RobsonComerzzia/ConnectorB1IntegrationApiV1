package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Modelo de Input da categoria no item(produto)")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorizacionInnerInput {

	@Schema(example = "0101")
	@NotBlank
	@JsonProperty("categoryCode")
	private String codcat;
	
	@Schema(example = "CATEGORIA 01")
	@JsonProperty("categoryDescr")
	private String descat;
	
	@Schema(example = "01")
	@JsonProperty("parentCategoryCode")
	private String codcatPadre;

}
