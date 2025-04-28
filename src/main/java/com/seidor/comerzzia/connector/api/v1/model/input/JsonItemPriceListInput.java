package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonItemPriceListInput implements Serializable {

	private static final long serialVersionUID = 11214323230L;
	
	@JsonProperty("guid")
	private String guid;
	
	@JsonProperty("PriceList")
	private String priceList;

	@JsonProperty("ListName")
	private String listName;
	
	@JsonProperty("ValidFor")
	private String validFor;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("ValidFrom")
	private LocalDateTime validFrom;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("ValidTo")
	private LocalDateTime validTo;
	
}
