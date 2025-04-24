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
@Table(name="D_UNIDAD_MEDIDA_ETIQUETAS_TBL")
@IdClass(UnidadeMedidaEtiqueta.pk_um_etiqueta.class)
public class UnidadeMedidaEtiqueta {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "cod_um_etiqueta", length = 4, nullable = false)
	private String codUmEtiqueta;

	@Column(name = "des_um_etiqueta", length = 60, nullable = false)
	private String desUmEtiqueta;

	@Column(name = "desetiqueta", length = 60, nullable = false)
	private String desetiqueta;
	
	@Column(name = "factor", nullable = false)
	private Long factor;
	

	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_um_etiqueta implements Serializable {

		private static final long serialVersionUID = 3617234287764373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "cod_um_etiqueta", length = 4, nullable = false)
		private String codUmEtiqueta;
	}

}
