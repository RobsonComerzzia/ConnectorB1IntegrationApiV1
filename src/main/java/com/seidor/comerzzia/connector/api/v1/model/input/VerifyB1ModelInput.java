package com.seidor.comerzzia.connector.api.v1.model.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerifyB1ModelInput {
	
	private String guid;
	
	private String apiKey;

}
