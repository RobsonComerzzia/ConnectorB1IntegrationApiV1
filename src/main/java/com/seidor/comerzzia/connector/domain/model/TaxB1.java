package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="tax_b1")
@IdClass(TaxB1.pk_taxB1.class)
public class TaxB1 {
	
	@Column(name = "guid", length = 40, nullable = false)
	private String guid;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "state", length = 2, nullable = false)
	private String state;
	
	@Column(name = "tributacao", length = 40, nullable = false)
	private String tributacao;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "ncm_code", length = 20, nullable = false)
	private String ncm;
	
	@Column(name = "efct_from", nullable = false)
	private LocalDateTime efctFrom;
	
	@Column(name = "efct_to", nullable = true)
	private LocalDateTime efctTo;
	
	@Column(name = "utilizacao", nullable = false)
	private BigInteger utilizacao;
	
	@Column(name = "tax_code", length = 10, nullable = false)
	private String taxCode;
	
	@Column(name = "cfop_out", length = 5, nullable = false)
	private String cfopOut;
	
	@Column(name = "icms", nullable = false)
	private BigDecimal icms;
	
	@Column(name = "pis", nullable = false)
	private BigDecimal pis;
	
	@Column(name = "cofins", nullable = false)
	private BigDecimal cofins;
	
	@Column(name = "cst_icms", length = 2, nullable = false)
	private String cst_icms;
	
	@Column(name = "cst_pis", length = 2, nullable = false)
	private String cst_pis;
	
	@Column(name = "cst_cofins", length = 2, nullable = false)
	private String cst_cofins;
	
	@Column(name = "process_date", nullable = true)
	private LocalDateTime processDate;
	
	@Column(name = "last_send_date", nullable = true)
	private LocalDateTime lastSendDate;	
	
    @PrePersist
    public void prePersist() {
    	
    	ncm = StringUtils.tratarString(ncm);
    }
    
    @PreUpdate
    public void preUpdate() {
    	
    	ncm = StringUtils.tratarString(ncm);
    }
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_taxB1 implements Serializable {

		private static final long serialVersionUID = 3617234287724373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "state", length = 2, nullable = false)
		private String state;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "ncm_code", length = 20, nullable = false)
		private String ncm;
	}
	
}
