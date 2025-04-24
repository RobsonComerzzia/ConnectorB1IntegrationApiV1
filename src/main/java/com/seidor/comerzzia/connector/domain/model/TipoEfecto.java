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
@Table(name="D_TIPOS_EFECTOS_TBL")
@IdClass(TipoEfecto.pk_tipoefecto.class)
public class TipoEfecto {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "codtipoefec", length = 2, nullable = false)
	private String codtipoefec;
	
	@Column(name = "destipoefec", length = 30, nullable = false)
	private String destipoefec;
	
	@Column(name = "remesable", length = 1, nullable = false)
	private String remesable;
	
	@Column(name = "entrada_documento_automatica", length = 1, nullable = false)
	private String entradaDocumentoAutomatica;
	
	@Column(name = "activo", length = 1, nullable = false)
	private String activo;
	

	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_tipoefecto implements Serializable {

		private static final long serialVersionUID = 3617234287764373445L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "codtipoefec", length = 2, nullable = false)
		private String codtipoefec;
	}

}
