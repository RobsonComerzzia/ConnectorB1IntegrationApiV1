package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;
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
public class JsonItemInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("guid")
	private String guid;
	
	@JsonProperty("DocEntry")
	private String docEntry;	
	
	@JsonProperty("TransType")
	private String transType;
	
	@JsonProperty("ObjType")
	private Integer objType;
	
	@JsonProperty("ItemCode")
	private BigInteger itemCode;
	
	@JsonProperty("ItemName")
	private String itemName;
	
	@JsonProperty("ItemClass")
	private Integer itemClass;
	
	@JsonProperty("validFor")
	private String validFor;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("validFrom")
	private LocalDateTime validFrom;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("validTo")
	private LocalDateTime validTo;
	
	
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
	
	@JsonProperty("SellItem")
	private String sellItem;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("CreateDate")
	private LocalDateTime createDate;
	
	@JsonProperty("CodeBars")
	private String codeBars;
	
	@JsonProperty("NcmCode")
	private String ncmCode;
	
	@JsonProperty("SUoMEntry")
	private String sUoMEntry;
	
	@JsonProperty("U_CMZB1_CATEG")
	private String u_cmzb1_categ;
	
}
