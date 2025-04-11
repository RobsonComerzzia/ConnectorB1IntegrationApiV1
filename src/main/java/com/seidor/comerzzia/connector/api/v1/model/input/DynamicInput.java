package com.seidor.comerzzia.connector.api.v1.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Modelo de input da propriedade dinâmica")
public class DynamicInput {

	@Schema(example = "TAX_ALIQUOTA_PIS")
	@NotBlank
	@JsonProperty("key")
	private String key;
	
	@Schema(example = "1.65")
	@NotBlank
	@JsonProperty("value")
	private String value;
}
