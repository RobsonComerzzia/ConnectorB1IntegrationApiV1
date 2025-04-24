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
@Table(name="D_CATEGORIZACION_TBL")
@IdClass(Categorizacion.pk_categorizacion.class)
public class Categorizacion {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "codcat", length = 20, nullable = false)
	private String codcat;

	@Column(name = "descat", length = 60, nullable = false)
	private String descat;
	
	@Column(name = "activo", length = 1, nullable = true)
	private String activo;
	
	@Column(name = "version", nullable = false)
	private Long version;
	
	@Column(name = "fecha_version", nullable = true)
	private LocalDateTime fechaVersion;
	
	@Column(name = "codcat_padre", length = 20, nullable = true)
	private String codcatPadre;
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_categorizacion implements Serializable {
		
		private static final long serialVersionUID = 5715219882978850561L;

		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "codcat", length = 20, nullable = false)
		private String codcat;
	}	

}
