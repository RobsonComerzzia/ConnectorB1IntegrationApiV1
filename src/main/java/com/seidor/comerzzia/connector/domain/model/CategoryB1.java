package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;
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
@Table(name="category_b1")
@IdClass(CategoryB1.pk_categoryB1.class)
public class CategoryB1 {
	
	@Column(name = "id", nullable = false)
	private Long id; 
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "code", length = 10, nullable = false)
	private String code;
	
	@Column(name = "name", length = 150, nullable = false)
	private String name;
	
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;
	
	@Column(name = "update_date", nullable = true)
	private LocalDateTime updateDate;
	
	@Column(name = "update_date_master", nullable = true)
	private LocalDateTime updateDateMaster;
	
	@Column(name = "last_send_date", nullable = true)
	private LocalDateTime lastSendDate;
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_categoryB1 implements Serializable {

		private static final long serialVersionUID = 3617234287724373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "code", length = 10, nullable = false)
		private String code;
		
	}
	
}
