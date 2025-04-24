package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="D_IMP_PORCENTAJES_TBL")
@IdClass(ImpPorcentajes.pk_impporcentajes.class)
@AllArgsConstructor
@NoArgsConstructor
public class ImpPorcentajes {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "id_grupo_impuestos", nullable = false)
	private Integer idGrupoImpuestos;	
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "id_trat_impuestos", nullable = false)
	private Long idTratImpuestos;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "codimp", length = 2, nullable = false)
	private String codimp;	
	
	@Column(name = "porcentaje")
	private BigDecimal porcentaje;	
	
	@Column(name = "porcentaje_recargo", nullable = false)
	private BigDecimal porcentajeRecargo;
	
	@Column(name = "codimp_fiscal", length = 10)
	private String codimpfiscal;	
	
	@Column(name = "base_percentage")
	private BigDecimal basePercentage;
	
	@Column(name = "tax_treatment_id_destination")
	private BigInteger taxTreatmentIdDestination;
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_impporcentajes implements Serializable {

		private static final long serialVersionUID = 3617234287764373443L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "id_grupo_impuestos", nullable = false)
		private Integer idGrupoImpuestos;	
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "id_trat_impuestos", nullable = false)
		private Long idTratImpuestos;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "codimp", length = 2, nullable = false)
		private String codimp;	
	}
	
}
