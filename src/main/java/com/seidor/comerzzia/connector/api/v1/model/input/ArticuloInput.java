package com.seidor.comerzzia.connector.api.v1.model.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.AlmaceneArticuloStockInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.ArticuloCodbarInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.ArticuloUnidadeMedidaInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.CategorizacionInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.FamiliaInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.ImpTipoInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.MarcaInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.ProveedorInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.SeccionInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.TarifaDetInnerInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.UnidadeMedidaEtiquetaInnerInput;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Builder
@Schema(description = "Modelo de input do item(produto)")
public class ArticuloInput {
	
	@Schema(example = "811101")
	@NotBlank
	@JsonProperty("itemCode")
	private String codart;

	@Schema(example = "ACUCAR UNIAO REFINADO 5KG")
	@NotBlank
	@JsonProperty("itemDescr")
	private String desart;
	
	@Schema(example = "")
	@JsonProperty("format")
	private String formato;
	
	@JsonProperty("family")
	private FamiliaInnerInput familia;
	
	@JsonProperty("session")
	private SeccionInnerInput seccion;
	
	@JsonProperty("category")
	private CategorizacionInnerInput categorizacion;
	
	@JsonProperty("supplier")
	private ProveedorInnerInput proveedor;
	
	@Schema(example = "")
	@JsonProperty("supplierReference")
	private String referenciaProveedor;
	
	@Schema(example = "0.00")
	@PositiveOrZero
	@JsonProperty("supplierDiscount")
	private BigDecimal dtoProveedor;

	@Schema(example = "")
	@JsonProperty("manufacturerCode")
	private String codfab;

	@Schema(example = "0.00")
	@JsonProperty("pmp")
	private BigDecimal pmp;

	@Schema(example = "N")
	@JsonProperty("automaticCostUpdate")
	private String actAutomaticaCosto;

	@Schema(example = "2024-09-13T18:01:28")
	@JsonProperty("lastCostUpdateDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime costoActualizado;

	@JsonProperty("taxType")
	@Valid
	private ImpTipoInnerInput impTipo;
	
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
	private UnidadeMedidaEtiquetaInnerInput unidadMedidaEtiqueta;
	
	@Schema(example = "10.000")
	@JsonProperty("quantityUnitMeasureCode")
	private BigDecimal cantidadUmEtiqueta;
	
	@Schema(example = "2024-09-13T18:01:28")
	@JsonProperty("creationDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaAlta;
	
	@JsonProperty("brand")
	private MarcaInnerInput marca;
	
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

	@JsonProperty("itemUnitMeasure")
	private List<ArticuloUnidadeMedidaInnerInput> articuloUnidadesMedida;
	
	@JsonProperty("itemBarcode")
	private List<ArticuloCodbarInnerInput> codbars;
	
	@JsonProperty("itemRate")
	private List<TarifaDetInnerInput> tarifas;

	@JsonProperty("stocks")
	private List<AlmaceneArticuloStockInnerInput> stocks;

	@JsonProperty("dynamics")
	private List<DynamicInput> dynamics;

}
