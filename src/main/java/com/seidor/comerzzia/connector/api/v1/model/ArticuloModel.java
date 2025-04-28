package com.seidor.comerzzia.connector.api.v1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seidor.comerzzia.connector.domain.model.UnidadeMedidaEtiqueta;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Schema(description = "Modelo do item(produto)")
public class ArticuloModel {
	
	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;

	@Schema(example = "811101")
	@NotBlank
	@JsonProperty("itemCode")
	private String codart;

	@Schema(example = "ACUCAR UNIAO REFINADO 5KG")
	@NotBlank
	@JsonProperty("itemDescr")
	private String desart;
	
	@Schema(example = "FORMATO")
	@JsonProperty("format")
	private String formato;
	
	@JsonProperty("family")
	private FamiliaModel familia;
	
	@JsonProperty("session")
	private SeccionModel seccione;
	
	@JsonProperty("category")
	private CategorizacionModel categorizacion;
	
	@JsonProperty("supplier")
	private ProveedorModel proveedor;
	
	@Schema(example = "")
	@NotBlank
	@JsonProperty("supplierReference")
	private String referenciaProveedor;
	
	@Schema(example = "0.00")
	@NotBlank
	@JsonProperty("supplierDiscount")
	private BigDecimal dtoProveedor;

	@Schema(example = "")
	@NotBlank
	@JsonProperty("manufacturerCode")
	private String codfab;

	@Schema(example = "0.00")
	@JsonProperty("pmp")
	private BigDecimal pmp;

	@Schema(example = "N")
	@JsonProperty("automaticCostUpdate")
	private String actAutomaticaCosto;

	@Schema(example = "2024-09-11 09:04:20")
	@JsonProperty("lastCostUpdateDate")
	private LocalDateTime costoActualizado;

	@JsonProperty("taxType")
	private ImpTipoModel impTipo;
	
	@Schema(example = "Observações ....")
	@JsonProperty("observations")
	private String observaciones;
	
	@Schema(example = "S")
	@NotBlank
	@JsonProperty("active")
	private String activo;
	  
	@Schema(example = "N")
	@JsonProperty("serialNumbersActive")
	private String numerosSerie;
	
	@Schema(example = "N")
	@NotBlank
	@JsonProperty("combination1Active")
	private String desglose1;
	
	@Schema(example = "N")
	@NotBlank
	@JsonProperty("combination2Active")
	private String desglose2;
	
	@Schema(example = "N")
	@NotBlank
	@JsonProperty("generic")
	private String generico;
	
	@Schema(example = "N")
	@NotBlank
	@JsonProperty("showCase")
	private String escaparate;
	
	@Schema(example = "Kg")
	@JsonProperty("unitMeasureAltCode")
	private String unidadMedidaAlternativa;
	
	@JsonProperty("unitMeasureCode")
	private UnidadeMedidaEtiqueta unidadMedidaEtiqueta;
	
	@Schema(example = "10.000")
	@JsonProperty("quantityUnitMeasureCode")
	private BigDecimal cantidadUmEtiqueta;
	
	@Schema(example = "2024-06-20T13:24:00")
	@JsonProperty("creationDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaAlta;
	
	@Schema(example = "05")
	@JsonProperty("version")
	private BigDecimal version;
	
	@Schema(example = "2024-06-20T13:24:00")
	@JsonProperty("versionDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaVersion;
	
	@JsonProperty("brand")
	private MarcaModel marca;
	
	@Schema(example = "")
	@JsonProperty("scalePLU")
	private Long balanzaPlu;
	
	@Schema(example = "")
	@JsonProperty("scaleSection")
	private String balanzaSeccion;
	
	@Schema(example = "U")
	@JsonProperty("scaleItemType")
	private String balanzaTipoArt;
	
	@Schema(example = "0")
	@JsonProperty("replacementTypeId")
	private Long idTipoSustitucion;
	
	@Schema(example = "")
	@JsonProperty("combination1GroupCode")
	private String codgrupodesDesglose1;
	
	@Schema(example = "")
	@JsonProperty("combination2GroupCode")
	private String codgrupodesDesglose2;
	
	@Schema(example = "N")
	@JsonProperty("confirmSalesPrice")
	private String confirmarPrecioVenta;
	
	@Schema(example = "1")
	@JsonProperty("itemType")
	private Long itemType;
	

}
