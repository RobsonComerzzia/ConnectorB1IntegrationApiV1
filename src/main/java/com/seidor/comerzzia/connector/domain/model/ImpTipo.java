package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;

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
@Table(name="D_IMP_TIPOS_TBL")
@IdClass(ImpTipo.pk_imptipo.class)
public class ImpTipo {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "codimp", length = 2, nullable = false)
	private String codimp;

	@Column(name = "desimp", length = 45, nullable = false)
	private String desimp;
	
	@Column(name = "summary", nullable = true)
	private Integer summary;
	
	@Column(name = "calculation_method", length = 255, nullable = true)
	private String calculationMethod;
	
	@Column(name = "parent_codimp", length = 2, nullable = true)
	private String parentCodimp;

	@Column(name = "application_order", nullable = true)
	private Integer applicationOrder;
	

	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_imptipo implements Serializable {

		private static final long serialVersionUID = 3617234287764373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "codimp", length = 2, nullable = false)
		private String codimp;
	}

}
