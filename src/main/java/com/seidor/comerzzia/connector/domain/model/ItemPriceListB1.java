package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="item_price_list_b1")
@IdClass(ItemPriceListB1.pk_itemPriceListB1.class)
public class ItemPriceListB1 {

	@Column(name = "id", nullable = false)
	private Long id; 
	
	@Column(name = "guid", length = 40, nullable = false)
	private String guid;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "price_list", nullable = false)
	private BigInteger priceList;
	
	@Column(name = "list_name", nullable = false)
	private String listName;
	
	@Column(name = "valid_for", nullable = false)
	private String validFor;
	
	@Column(name = "valid_from", nullable = true)
	private LocalDateTime validFrom;
	
	@Column(name = "valid_to", nullable = true)
	private LocalDateTime validTo;
	
	@Column(name = "update_date", nullable = true)
	private LocalDateTime updateDate;
	
	@Column(name = "last_send_date", nullable = true)
	private LocalDateTime lastSendDate;
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_itemPriceListB1 implements Serializable {

		private static final long serialVersionUID = 3617234287724373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "price_list", nullable = false)
		private BigInteger priceList;
	}
	
}
