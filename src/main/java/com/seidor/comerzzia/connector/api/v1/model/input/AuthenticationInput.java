package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationInput implements Serializable {
	
	
	private static final long serialVersionUID = 13829272902898L;

	@JsonProperty("Username")
	private String username;
	
	@JsonProperty("password")
	private String password;
	
}
