package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;
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
@Schema(description = "Modelo de input de vinculo de imposto ao produto por estado")
public class ArticulosImpuestoInput implements Serializable {

	private static final long serialVersionUID = 12723729073L;
	
	@JsonProperty("articulos")
	private List<ArticuloImpuestoInput> articulos;

}
