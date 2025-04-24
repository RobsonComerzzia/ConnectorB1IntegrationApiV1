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
@Table(name="D_MEDIOS_PAGO_TBL")
@IdClass(MedioPago.pk_mediopago.class)
public class MedioPago {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "codmedpag", length = 4, nullable = false)
	private String codmedpag;

	@Column(name = "desmedpag", length = 45, nullable = false)
	private String desmedpag;

	@Column(name = "contado", length = 1, nullable = true)
	private String contado;

	@Column(name = "efectivo", length = 1, nullable = true)
	private String efectivo;

	@Column(name = "tarjeta_credito", length = 1, nullable = true)
	private String tarjetaCredito;

	@Column(name = "codtipoefec", length = 2, nullable = true)
	private String codtipoefec;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="codtipoefec", referencedColumnName="codtipoefec", insertable = false, updatable = false)
    })
	private TipoEfecto tipoEfecto;
	
	@Column(name = "visible_venta", length = 1, nullable = true)
	private String visibleVenta;

	@Column(name = "visible_tienda_virtual", length = 1, nullable = true)
	private String visibleTiendaVirtual;

	@Column(name = "visible_compra", length = 1, nullable = true)
	private String visibleCompra;

	@Column(name = "activo", length = 1, nullable = false)
	private String activo;

	@Column(name = "manual", length = 1, nullable = false)
	private String manual;

	@Column(name = "recuento_automatico_caja", length = 1, nullable = false)
	private String recuentoAutomaticoCaja;

	@Column(name = "clase_control", length = 255, nullable = true)
	private String claseControl;
	
	@Column(name = "visible_devolucion", length = 1, nullable = true)
	private String visibleDevolucion;

	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_mediopago implements Serializable {

		private static final long serialVersionUID = 3617234287764373445L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "codmedpag", length = 4, nullable = false)
		private String codmedpag;
	}

}
