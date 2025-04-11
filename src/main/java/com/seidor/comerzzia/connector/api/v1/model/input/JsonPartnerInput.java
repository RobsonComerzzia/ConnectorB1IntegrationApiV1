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
public class JsonPartnerInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("guid")
	private String guid;
	
	@JsonProperty("DocEntry")
	private String docEntry;	
	
	@JsonProperty("TransType")
	private String transType;
	
	@JsonProperty("ObjType")
	private Integer objType;
	
	@JsonProperty("CardCode")
	private String cardCode;
	
	@JsonProperty("CardName")
	private String cardName;
	
	@JsonProperty("CardType")
	private String cardType;
	
	@JsonProperty("validFor")
	private String validFor;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("validTo")
	private LocalDateTime validTo;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("validFrom")
	private LocalDateTime validFrom;
	
	@JsonProperty("frozenFor")
	private String frozenFor;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("frozenTo")
	private LocalDateTime frozenTo;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("frozenFrom")
	private LocalDateTime frozenFrom;
	
	@JsonProperty("AliasName")
	private String aliasName;
	
	@JsonProperty("Free_Text")
	private String freeText;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("DateTill")
	private LocalDateTime dateTill;
	
	@JsonProperty("LangCode")
	private BigInteger langCode;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("CreateDate")
	private LocalDateTime createDate;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("UpdateDate")
	private LocalDateTime updateDate;
	
	@JsonProperty("Street")
	private String street;
	
	@JsonProperty("Block")
	private String block;
	
	@JsonProperty("City")
	private String city;
	
	@JsonProperty("State")
	private String state;
	
	@JsonProperty("ZipCode")
	private String zipCode;
	
	@JsonProperty("TaxId0")
	private String taxId0;
	
	@JsonProperty("TaxId4")
	private String taxId4;
	
	@JsonProperty("CreditLine")
	private BigDecimal creditLine;
	
	@JsonProperty("Balance")
	private BigDecimal balance;	
	
}
