package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name="D_PROVEEDORES_TBL")
@IdClass(Proveedor.pk_proveedor.class)
public class Proveedor {
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "codpro", length = 11, nullable = false)
	private String codpro;

	@Column(name = "despro", length = 45, nullable = false)
	private String despro;

	@Column(name = "tipo_proveedor", length = 3, nullable = true)
	private String tipoProveedor;

	@Column(name = "nombre_comercial", length = 45, nullable = true)
	private String nombreComercial;

	@Column(name = "domicilio", length = 50, nullable = true)
	private String domicilio;
	
	@Column(name = "poblacion", length = 50, nullable = true)
	private String poblacion;
	
	@Column(name = "provincia", length = 50, nullable = true)
	private String provincia;
	
	@Column(name = "cp", length = 20, nullable = true)
	private String cp;
	
	@Column(name = "codpais", length = 4, nullable = false)
	private String codpais;
	
	@Column(name = "telefono1", length = 15, nullable = true)
	private String telefono1;
	
	@Column(name = "telefono2", length = 15, nullable = true)
	private String telefono2;
	
	@Column(name = "fax", length = 15, nullable = true)
	private String fax;
	
	@Column(name = "persona_contacto", length = 45, nullable = true)
	private String personaContacto;
	
	@Column(name = "email", length = 60, nullable = true)
	private String email;
	
	@Column(name = "cif", length = 20, nullable = true)
	private String cif;
	
	@Column(name = "id_trat_impuestos", nullable = false)
	private Long idTratImpuestos;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="id_trat_impuestos", referencedColumnName="id_trat_impuestos", insertable = false, updatable = false)
    })
	private ImpTratamiento impTratamiento;
	
	@Column(name = "id_medpag_ven", nullable = true)
	private Long idMedpagVen;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="id_medpag_ven", referencedColumnName="id_medpag_ven", insertable = false, updatable = false)
    })
	private MedioPagoVen medioPagoVen;
	
	@Column(name = "observaciones", length = 255, nullable = true)
	private String observaciones;
	
	@Column(name = "activo", length = 1, nullable = false)
	private String activo;
	
	@Column(name = "fecha_alta", nullable = true)
	private LocalDateTime fechaAlta;
	
	@Column(name = "fecha_baja", nullable = true)
	private LocalDateTime fechaBaja;
	
	@Column(name = "banco", length = 45, nullable = true)
	private String banco;
	
	@Column(name = "banco_domicilio", length = 50, nullable = true)
	private String bancoDomicilio;
	
	@Column(name = "banco_poblacion", length = 50, nullable = true)
	private String bancoPoblacion;
	
	@Column(name = "ccc", length = 34, nullable = true)
	private String ccc;
	
	@Column(name = "localidad", length = 50, nullable = true)
	private String localidad;
	
	@Column(name = "codtipoiden", length = 10, nullable = true)
	private String codtipoiden;
	
	@Column(name = "url_tracking", length = 255, nullable = true)
	private String urlTracking;
	
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_proveedor implements Serializable {

		private static final long serialVersionUID = 3617234287764373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "codpro", length = 11, nullable = false)
		private String codpro;
	}

}
