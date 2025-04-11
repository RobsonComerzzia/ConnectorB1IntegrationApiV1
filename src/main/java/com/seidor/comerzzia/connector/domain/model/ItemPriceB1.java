package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="item_price_b1")
@IdClass(ItemPriceB1.pk_itemPriceB1.class)
public class ItemPriceB1 {

	@Column(name = "id", nullable = false)
	private Long id; 
	
	@Column(name = "guid", length = 40, nullable = false)
	private String guid;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "item_code", nullable = false)
	private BigInteger itemCode;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "price_list", nullable = false)
	private BigInteger priceList;
	
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
	@Column(name = "cost_price", nullable = false)
	private BigDecimal costPrice;
	
	@Column(name = "uom_code", nullable = false)
	private String uomCode;
	
	@Column(name = "update_date", nullable = true)
	private LocalDateTime updateDate;
	
	@Column(name = "last_send_date", nullable = true)
	private LocalDateTime lastSendDate;
	
    @PrePersist
    public void prePersist() {
    	
    	if (costPrice == null) {
    		costPrice = BigDecimal.ZERO;
    	}
    }
    
    @PreUpdate
    public void preUpdate() {
    	
    	if (costPrice == null) {
    		costPrice = BigDecimal.ZERO;
    	}
    }
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_itemPriceB1 implements Serializable {

		private static final long serialVersionUID = 3617234287724373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "item_code", nullable = false)
		private BigInteger itemCode;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "price_list", nullable = false)
		private BigInteger priceList;
	}
	
}
