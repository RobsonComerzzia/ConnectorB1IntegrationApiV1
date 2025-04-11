package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Modelo de Input da família no item(produto)")
public class FamiliaInnerInput {
	
	@Schema(example = "01")
	@NotBlank
	@JsonProperty("familyCode")
	private String codfam;
	
	@Schema(example = "Familia 01")
	@JsonProperty("familyDescr")
	private String desfam;

	@Schema(example = "S")
	@JsonProperty("active")
	private String activo;
	
}
