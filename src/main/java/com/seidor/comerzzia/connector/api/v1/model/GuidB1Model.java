package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuidB1Model implements Serializable {

	private static final long serialVersionUID = 142243265L;

	@JsonProperty("message")
	private String message;
	
	@JsonProperty("guid")
	private String guid;
	
}
