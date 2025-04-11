package com.seidor.comerzzia.connector.api.v1.model.input;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class VerifyB1DataModel implements Serializable {
	
	private static final long serialVersionUID = 13121211L;

	@JsonProperty("fileItem")	
	private String fileItem;
	
	@JsonProperty("fileList")	
	private String fileList;
	
	@JsonProperty("filePrice")	
	private String filePrice;	
	
	@JsonProperty("fileUnit")
	private String fileUnit;

}
