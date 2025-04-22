package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonItemPriceInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("guid")
	private String guid;
	
	@JsonProperty("ItemCode")
	private BigInteger itemCode;
	
	@JsonProperty("PriceList")
	private BigInteger priceList;
	
	@JsonProperty("Price")
	private BigDecimal price;
	
	@JsonProperty("UomCode")
	private String uomCode;
		
}
