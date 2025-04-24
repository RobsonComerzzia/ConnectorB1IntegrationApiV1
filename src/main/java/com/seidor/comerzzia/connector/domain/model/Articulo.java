package com.seidor.comerzzia.connector.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name="D_ARTICULOS_TBL")
@IdClass(Articulo.pk_articulo.class)
public class Articulo {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "codart", length = 20, nullable = false)
	private String codart;
	
	@Column(name = "desart", length = 60, nullable = false)
	private String desart;
	
	@Column(name = "formato", length = 20, nullable = true)
	private String formato;
	
	@Column(name = "codfam", length = 20, nullable = true)
	private String codfam;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="codfam", referencedColumnName="codfam", insertable = false, updatable = false)
    })
	private Familia familia;
	
	@Column(name = "codseccion", length = 20, nullable = true)
	private String codseccion;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="codseccion", referencedColumnName="codseccion", insertable = false, updatable = false)
    })
	private Seccion seccione;
	
	@Column(name = "codcat", length = 20, nullable = true)
	private String codcat;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="codcat", referencedColumnName="codcat", insertable = false, updatable = false)
    })
	private Categorizacion categorizacion;
	
	@Column(name = "codpro", length = 11, nullable = true)
	private String codpro;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="codpro", referencedColumnName="codpro", insertable = false, updatable = false)
    })
	private Proveedor proveedor;
	
	@Column(name = "referencia_proveedor", length = 40, nullable = true)
	private String referenciaProveedor;
	
	@Column(name = "dto_proveedor", precision=7, scale=2,  nullable = false)
	private BigDecimal dtoProveedor;

	@Column(name = "codfab", length = 11, nullable = true)
	private String codfab;

	@Column(name = "pmp", precision=18, scale=4, nullable = true)
	private BigDecimal pmp;

	@Column(name = "act_automatica_costo", length = 1, nullable = true)
	private String actAutomaticaCosto;

	@Column(name = "costo_actualizado", nullable = true)
	private LocalDateTime costoActualizado;

	@Column(name = "codimp", length = 2, nullable = false)
	private String codimp;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="codimp", referencedColumnName="codimp", insertable = false, updatable = false)
    })
	private ImpTipo impTipo;
	
	
	@Column(name = "observaciones", length = 255, nullable = true)
	private String observaciones;
	
	@Column(name = "activo", length = 1, nullable = false)
	private String activo;
	  
	@Column(name = "numeros_serie", length = 1, nullable = true)
	private String numerosSerie;
	
	@Column(name = "desglose1", length = 1, nullable = false)
	private String desglose1;
	
	@Column(name = "desglose2", length = 1, nullable = false)
	private String desglose2;
	
	@Column(name = "generico", length = 1, nullable = false)
	private String generico;
	
	@Column(name = "escaparate", length = 1, nullable = false)
	private String escaparate;
	
	@Column(name = "unidad_medida_alternativa", length = 4, nullable = true)
	private String unidadMedidaAlternativa;
	
	@Column(name = "cod_um_etiqueta", length = 4, nullable = true)
	private String codUmEtiqueta;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="cod_um_etiqueta", referencedColumnName="cod_um_etiqueta",  insertable = false, updatable = false)
    })
	private UnidadeMedidaEtiqueta unidadMedidaEtiqueta;
	
	@Column(name = "cantidad_um_etiqueta", nullable = true)
	private BigDecimal cantidadUmEtiqueta;
	
	@Column(name = "fecha_alta", nullable = true)
	private LocalDateTime fechaAlta;
	
	@Column(name = "version", nullable = false)
	private BigDecimal version;
	
	@Column(name = "fecha_version", nullable = true)
	private LocalDateTime fechaVersion;
	
	@Column(name = "codmarca", length = 20, nullable = true)
	private String codmarca;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="uid_actividad", referencedColumnName="uid_actividad", insertable = false, updatable = false),
        @JoinColumn(name="codmarca", referencedColumnName="codmarca",  insertable = false, updatable = false)
    })
	private Marca marca;
	
	@Column(name = "balanza_plu", nullable = true)
	private Long balanzaPlu;
	
	@Column(name = "balanza_seccion", length = 2, nullable = true)
	private String balanzaSeccion;
	
	@Column(name = "balanza_tipo_art", length = 1, nullable = true)
	private String balanzaTipoArt;
	
	@Column(name = "id_tipo_sustitucion", nullable = true)
	private Long idTipoSustitucion;
	
	@Column(name = "codgrupodes_desglose1", length = 20, nullable = true)
	private String codgrupodesDesglose1;
	
	@Column(name = "codgrupodes_desglose2", length = 20, nullable = true)
	private String codgrupodesDesglose2;
	
	@Column(name = "confirmar_precio_venta", length = 1, nullable = true)
	private String confirmarPrecioVenta;
	
	@Column(name = "item_type", nullable = true)
	private Long itemType;
	
	
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class pk_articulo implements Serializable {

		private static final long serialVersionUID = 3617234287764373442L;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "uid_actividad", length = 40, nullable = false)
		private String uidActividad;
		
		@EqualsAndHashCode.Include
		@Id
		@Column(name = "codart", length = 20, nullable = false)
		private String codart;
	}	
	
}
