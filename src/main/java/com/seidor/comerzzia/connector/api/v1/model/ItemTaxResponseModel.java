package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemTaxResponseModel implements Serializable {

	private static final long serialVersionUID = 13122341543L;
	
	private String state;
	
	private String itemCode;
	
	private String ncmCode;
	
	private BigDecimal icms;
	
	private BigDecimal pis;
	
	private BigDecimal cofins;
	
	private String cstIcms;
	
	private String cstPis;
	
	private String cstCofins;
	
	private String category;
	
	private String cest;
	
	private String productSrc;

}
