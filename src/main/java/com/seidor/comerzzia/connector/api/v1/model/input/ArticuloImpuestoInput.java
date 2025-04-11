package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de input de vinculo de imposto ao produto por estado")
public class ArticuloImpuestoInput implements Serializable {

	private static final long serialVersionUID = 121212121214L;

	@Schema(example = "FOOD-SERVICE")
	@JsonProperty("uuidActividad")
	private String uuidActividad;
	
	@Schema(example = "PI")
	@NotBlank
	@JsonProperty("state")
	private String state;
	
	@Schema(example = "1504")
	@NotBlank
	@JsonProperty("itemCode")
	private Long itemCode;
	
	@Schema(example = "29001001")
	@NotBlank
	@JsonProperty("ncmCode")
	private String ncmCode;
	
	@Schema(example = "60")
	@NotBlank
	@JsonProperty("cstIcms")
	private String cstIcms;
	
	@Schema(example = "17.00")
	@NotBlank
	@JsonProperty("icms")
	private BigDecimal icms;

}
