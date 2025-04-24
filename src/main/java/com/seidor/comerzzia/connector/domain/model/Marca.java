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
@Table(name="D_ARTICULOS_MARCAS_TBL")
@IdClass(Marca.pk_marca.class)
public class Marca {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "codmarca", length = 20, nullable = false)
	private String codmarca;

	@Column(name = "desmarca", length = 60, nullable = false)
	private String desmarca;
	
	@Column(name = "codfab", length = 11, nullable = true)
	private String codfab;
	
	@Column(name = "activo", length = 1, nullable = false)
	private String activo;
	

	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_marca implements Serializable {

		private static final long serialVersionUID = 3617234287764373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "codmarca", length = 20, nullable = false)
		private String codmarca;
	}
}
