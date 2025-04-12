package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseTokenModel implements Serializable {

	private static final long serialVersionUID = 1948302612L;

	@JsonProperty("access_token")
	private String access_token;
	
	@JsonProperty("scope")
	private String scope;
	
	@JsonProperty("token_tuype")
	private String token_type;
	
	@JsonProperty("expires_in")
	private Long expires_in;
	
}
