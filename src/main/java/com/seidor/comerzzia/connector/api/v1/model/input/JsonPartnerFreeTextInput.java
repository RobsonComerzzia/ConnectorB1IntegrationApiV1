package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonPartnerFreeTextInput implements Serializable {


	private static final long serialVersionUID = 137227490279L;

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("data")
	private List<Long> data;
	
}
