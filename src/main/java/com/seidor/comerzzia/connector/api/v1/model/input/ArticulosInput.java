package com.seidor.comerzzia.connector.api.v1.model.input;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo de input de cadastro de articulos")
public class ArticulosInput {
	
	@JsonProperty("articulos")
	private List<ArticuloInput> articulos;

}
