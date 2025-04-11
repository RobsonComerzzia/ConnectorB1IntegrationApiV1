package com.seidor.comerzzia.connector.api.v1.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IntegracaoB1Model {
	
	private String protocol;
	
	private LocalDateTime dateTimeRequest;

}
