package com.seidor.comerzzia.connector.api.v1.model.input.innerclass;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
@Schema(description = "Modelo de Input do fornecedor no item(produto)")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProveedorInnerInput {
	
	@Schema(example = "0001")
	@NotBlank
	@JsonProperty("supplierCode")
	private String codpro;

	@Schema(example = "Distribuidora Alimentos Ltda")
	@JsonProperty("supplierDescr")
	private String despro;

	@Schema(example = "PRO")
	@JsonProperty("supplierType")
	private String tipoProveedor;

	@Schema(example = "Distribuidora Alimentos Ltda")
	@JsonProperty("supplierTradeName")
	private String nombreComercial;

	@Schema(example = "R. da Palmeiras")
	@JsonProperty("adress")
	private String domicilio;
	
	@Schema(example = "Centro")
	@JsonProperty("neighborhood")
	private String poblacion;
	
	@Schema(example = "Ceará")
	@JsonProperty("state")
	private String provincia;
	
	@Schema(example = "61939-000")
	@JsonProperty("postalCode")
	private String cp;
	
	@Schema(example = "BR")
	@JsonProperty("contryCode")
	private String codpais;
	
	@Schema(example = "(27)9999-9999")
	@JsonProperty("telephone1")
	private String telefono1;
	
	@Schema(example = "(27)9999-9999")
	@JsonProperty("telephone2")
	private String telefono2;
	
	@Schema(example = "(27)9999-9999")
	@JsonProperty("fax")
	private String fax;
	
	@Schema(example = "Carlos da Silva")
	@JsonProperty("contactPerson")
	private String personaContacto;
	
	@Schema(example = "carlos.distribuidora@email.com.br")
	@JsonProperty("email")
	private String email;
	
	@Schema(example = "35229640004069")
	@JsonProperty("cnpjcpf")
	private String cif;

	@JsonProperty("taxTreatment")
	private ImpTratamientoInnerInput impTratamiento;
	
	@JsonProperty("dueDateData")
	private MedioPagoVenInnerInput medioPagoVen;
	
	@Schema(example = "Observações ...")
	@JsonProperty("note")
	private String observaciones;
	
	@Schema(example = "S")
	@JsonProperty("active")
	private String activo;
	
	@Schema(example = "2024-06-20T13:24:00")
	@JsonProperty("activationDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaAlta;
	
	@Schema(example = "2024-12-5T15:00:00")
	@JsonProperty("dischargeDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaBaja;
	
	@Schema(example = "Banco do Brasil S.A.")
	@JsonProperty("bank")
	private String banco;
	
	@Schema(example = "Av. Carlos Drumond Andrade, 1545")
	@JsonProperty("bankAdress")
	private String bancoDomicilio;
	
	@Schema(example = "Centro")
	@JsonProperty("bankNeighborhood")
	private String bancoPoblacion;
	
	@Schema(example = "")
	@JsonProperty("ccc")
	private String ccc;
	
	@Schema(example = "São Paulo")
	@JsonProperty("city")
	private String localidad;
	
	@Schema(example = "CNPJ")
	@JsonProperty("identityTypeCode")
	private String codtipoiden;
	
	@Schema(example = "")
	@JsonProperty("urlTracking")
	private String urlTracking;
	
}
