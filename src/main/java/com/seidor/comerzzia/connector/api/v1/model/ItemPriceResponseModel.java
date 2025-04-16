package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemPriceResponseModel implements Serializable {

	private static final long serialVersionUID = 147393682056L;
	
	private Long itemCode;
	
	private Long codTar;
	
	private LocalDateTime validFrom;
	
	private BigDecimal costPrice;
	
	private BigDecimal price;

}
