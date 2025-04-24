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
@Table(name="D_SECCIONES_TBL")
@IdClass(Seccion.pk_seccion.class)
public class Seccion {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "codseccion", length = 20, nullable = false)
	private String codseccion;
	
	@Column(name = "desseccion", length = 60, nullable = false)
	private String desseccion;

	@Column(name = "activo", length = 1, nullable = false)
	private String activo;
	
	@Column(name = "ruta_preparacion", length = 1, nullable = true)
	private String rutaPreparacion;
	
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_seccion implements Serializable {

		private static final long serialVersionUID = 3617234287764373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "codseccion", length = 20, nullable = false)
		private String codseccion;
	}

}
