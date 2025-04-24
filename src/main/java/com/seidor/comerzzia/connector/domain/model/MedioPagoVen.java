package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="D_MEDIOS_PAGO_VEN_TBL")
@IdClass(MedioPagoVen.pk_mediopagoven.class)
public class MedioPagoVen {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "id_medpag_ven", nullable = false)
	private Long idMedpagVen;
	
	@Column(name = "codmedpag", length = 4, nullable = false)
	private String codmedpag;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="codmedpag", referencedColumnName="codmedpag", insertable = false, updatable = false)
    })
	private MedioPago medioPago;
	
	@Column(name = "desmedpag_ven", length = 45, nullable = false)
	private String desmedpagVen;
	
	@Column(name = "numero_vencimientos", nullable = false)
	private Integer numeroVencimientos;
	
	@Column(name = "dias_cadencia", nullable = false)
	private Integer diasCadencia;
	
	@Column(name = "dias_entre_vencimientos", nullable = false)
	private Integer diasEntreVencimientos;
	
	@Column(name = "dias_naturales", length = 1, nullable = false)
	private String diasNaturales;
	
	@Column(name = "activo", length = 1, nullable = false)
	private String activo;
	
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_mediopagoven implements Serializable {

		private static final long serialVersionUID = 3617234287764373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "id_medpag_ven", nullable = false)
		private Long idMedpagVen;
	}

}
