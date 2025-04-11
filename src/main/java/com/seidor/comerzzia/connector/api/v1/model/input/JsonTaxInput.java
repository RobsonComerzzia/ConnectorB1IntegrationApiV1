package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
public class JsonTaxInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("guid")
	private String guid;	
	
	@JsonProperty("State")
	private String state;
	
	@JsonProperty("Tributacao")
	private String tributacao;
	
	@JsonProperty("NCM")
	private String ncm;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("EfctFrom")
	private LocalDateTime efctFrom;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("EfctTo")
	private LocalDateTime efctTo;
	
	@JsonProperty("Utilizacao")
	private Integer utilizacao;
	
	@JsonProperty("TaxCode")
	private String taxCode;
	
	@JsonProperty("CfopOut")
	private BigInteger cfopOut;
	
	@JsonProperty("ICMS")
	private BigDecimal icms;
	
	@JsonProperty("PIS")
	private BigDecimal pis;
	
	@JsonProperty("COFINS")
	private BigDecimal cofins;
	
	@JsonProperty("CST_ICMS")
	private String cst_icms;
	
	@JsonProperty("CST_PIS")
	private String cst_pis;
	
	@JsonProperty("CST_COFINS")
	private String cst_cofins;
	
}
