package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seidor.comerzzia.connector.api.v1.model.input.VerifyB1DataModel;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifyB1Model implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("status")
	private String status;
	
	@JsonProperty("FileName")
	private String fileName;
	
	@JsonProperty("data")
	private VerifyB1DataModel data;

}
