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
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="partner_b1")
@IdClass(PartnerB1.pk_partnerB1.class)
public class PartnerB1 {
	
	@Column(name = "guid", length = 40, nullable = false)
	private String guid;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "doc_entry", nullable = false)
	private BigInteger docEntry;
	
	@Column(name = "trans_type", length = 1, nullable = false)
	private String transType;
	
	@Column(name = "obj_type", nullable = false)
	private BigInteger objType;	
	
	@Column(name = "card_code", length = 20, nullable = false)
	private String cardCode;	
	
	@Column(name = "card_name", length = 150, nullable = false)
	private String cardName;
	
	@Column(name = "card_f_name", length = 100, nullable = false)
	private String cardFName;

	@Column(name = "card_type", length = 1, nullable = false)
	private String cardType;
	
	@Column(name = "valid_for", length = 1, nullable = false)
	private String validFor;
	
	@Column(name = "valid_to", nullable = true)
	private LocalDateTime validTo;
	
	@Column(name = "valid_from", nullable = true)
	private LocalDateTime validFrom;
	
	@Column(name = "frozen_for", length = 1, nullable = false)
	private String frozenFor;
	
	@Column(name = "frozen_to", nullable = true)
	private LocalDateTime frozenTo;
	
	@Column(name = "frozen_from", nullable = true)
	private LocalDateTime frozenFrom;
	
	@Column(name = "alias_name", length = 100, nullable = true)
	private String aliasName;
	
	@Column(name = "free_text", length = 150, nullable = true)
	private String freeText;

	@Column(name = "date_till", nullable = true)
	private LocalDateTime dateTill;
	
	@Column(name = "lang_code", nullable = false)
	private BigInteger langCode;	
	
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;
	
	@Column(name = "update_date", nullable = true)
	private LocalDateTime updateDate;
	
	@Column(name = "update_date_master", nullable = true)
	private LocalDateTime updateDateMaster;
	
	@Column(name = "street", length = 100, nullable = true)
	private String street;
	
	@Column(name = "block", length = 100, nullable = true)
	private String block;
	
	@Column(name = "city", length = 100, nullable = true)
	private String city;	
	
	@Column(name = "state", length = 2, nullable = true)
	private String state;	
	
	@Column(name = "zip_code", length = 20, nullable = true)
	private String zipCode;
	
	@Column(name = "tax_id0", length = 50, nullable = true)
	private String taxId0;
	
	@Column(name = "tax_id4", length = 50, nullable = true)
	private String taxId4;
	
	@Column(name = "credit_line", nullable = false)
	private String creditLine;
	
	@Column(name = "balance", nullable = false)
	private BigDecimal balance;
	
	@Column(name = "last_send_date", nullable = true)
	private LocalDateTime lastSendDate;
    
    @PrePersist
    public void prePersist() {
    	
    	if (creditLine == null) {
    		creditLine = "0";
    	}
    	
    	if (balance == null) {
    		balance = BigDecimal.ZERO;
    	}
    }
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_partnerB1 implements Serializable {

		private static final long serialVersionUID = 3617234287724373443L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "doc_entry", nullable = false)
		private BigInteger docEntry;
		
	}
	
}
