package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.seidor.comerzzia.connector.util.StringUtils;

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
@Table(name="item_b1")
@IdClass(ItemB1.pk_itemB1.class)
public class ItemB1 {
	
	@Column(name = "id", nullable = false)
	private Long id; 
	
	@Column(name = "guid", length = 40, nullable = false)
	private String guid;
	
	@Column(name = "doc_entry", nullable = false)
	private BigInteger docEntry;
	
	@Column(name = "trans_type", length = 1, nullable = false)
	private String transType;
	
	@Column(name = "obj_type", nullable = false)
	private BigInteger objType;	

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "item_code", nullable = false)
	private String itemCode;	
	
	@Column(name = "item_name", length = 150, nullable = false)
	private String itemName;
	
	@Column(name = "item_class", nullable = false)
	private BigInteger itemClass;
	
	@Column(name = "valid_for", length = 1, nullable = false)
	private String validFor;
	
	@Column(name = "valid_from", nullable = true)
	private LocalDateTime validFrom;
	
	@Column(name = "valid_to", nullable = true)
	private LocalDateTime validTo;
	
	@Column(name = "frozen_for", length = 1, nullable = false)
	private String frozenFor;
	
	@Column(name = "frozen_to", nullable = true)
	private LocalDateTime frozenTo;
	
	@Column(name = "frozen_from", nullable = true)
	private LocalDateTime frozenFrom;
	
	@Column(name = "sell_item", length = 1, nullable = false)
	private String sellItem;
	
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;
	
	@Column(name = "code_bars", length = 100, nullable = true)
	private String codeBars;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "ncm_code", length = 20, nullable = false)
	private String ncmCode;
	
	@Column(name = "suom_entry", length = 100, nullable = true)
	private String sUoMEntry;
	
	@Column(name = "update_date", nullable = true)
	private LocalDateTime updateDate;
	
	@Column(name = "last_send_date", nullable = true)
	private LocalDateTime lastSendDate;
	
    @PrePersist
    public void prePersist() {
    	
    	ncmCode = StringUtils.tratarString(ncmCode);
    }
    
    @PreUpdate
    public void preUpdate() {
    	
    	ncmCode = StringUtils.tratarString(ncmCode);
    }
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_itemB1 implements Serializable {

		private static final long serialVersionUID = 3617234287724373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "item_code", nullable = false)
		private String itemCode;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "ncm_code", length = 20, nullable = false)
		private String ncmCode;
	}
	
}
