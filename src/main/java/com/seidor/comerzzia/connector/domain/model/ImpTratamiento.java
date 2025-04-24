package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="D_IMP_TRATAMIENTO_TBL")
@IdClass(ImpTratamiento.pk_imptratamiento.class)
@AllArgsConstructor
@NoArgsConstructor
public class ImpTratamiento {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "id_trat_impuestos", nullable = false)
	private Long idTratImpuestos;
	
	@Column(name = "codtratimp", length = 11, nullable = false)
	private String codtratimp;
	
	@Column(name = "destratimp", length = 45, nullable = false)
	private String destratimp;
	
	@Column(name = "aplica_recargo", length = 1, nullable = false)
	private String aplicaRecargo;

	@Column(name = "codpais", length = 4, nullable = false)
	private String codpais;
	
	@Column(name = "region_impuestos", length = 20, nullable = true)
	private String regionImpuestos;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
    	@JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="id_trat_impuestos", referencedColumnName="id_trat_impuestos", insertable = false, updatable = false)
    })
	private ImpPorcentajes impPorcentajes;
	

	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_imptratamiento implements Serializable {

		private static final long serialVersionUID = 3617234287764373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "id_trat_impuestos", nullable = false)
		private Long idTratImpuestos;
	}

}
