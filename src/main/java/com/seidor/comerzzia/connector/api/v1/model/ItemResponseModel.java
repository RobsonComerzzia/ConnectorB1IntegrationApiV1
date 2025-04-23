package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemResponseModel implements Serializable {

	private static final long serialVersionUID = 36282062830183L;
	
	private Long itemCode;
	
	private String itemName;
	
	private String codeBars;

}
