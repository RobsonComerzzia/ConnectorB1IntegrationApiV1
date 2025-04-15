package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationBodyInput implements Serializable {
	
	
	private static final long serialVersionUID = 13829272902898L;

	@JsonProperty("grant_type")
	private String grant_type;
	
	@JsonProperty("scope")
	private String scope;
	
}
