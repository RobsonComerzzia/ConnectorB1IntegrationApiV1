package com.seidor.comerzzia.connector.api.v1.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seidor.comerzzia.connector.domain.model.ImpTratamiento;
import com.seidor.comerzzia.connector.domain.model.MedioPagoVen;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Schema(description = "Modelo do fornecedor")
public class ProveedorModel {
	
	@Schema(example = "50fcf330-1832-402a-bb71-9276fd4bf092")
	@NotBlank
	@JsonProperty("activityUid")
	private String uidActividad;

	@Schema(example = "0001")
	@NotBlank
	@JsonProperty("supplierCode")
	private String codpro;

	@Schema(example = "Distribuidora Alimentos Ltda")
	@NotBlank
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
	@NotBlank
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

	@NotBlank
	@JsonProperty("taxTreatment")
	private ImpTratamiento impTratamiento;
	
	@JsonProperty("dueDateData")
	private MedioPagoVen medioPagoVen;
	
	@Schema(example = "Observações ...")
	@JsonProperty("note")
	private String observaciones;
	
	@Schema(example = "S")
	@NotBlank
	@JsonProperty("active")
	private String activo;
	
	@Schema(example = "20/06/2024 13:24:00")
	@JsonProperty("activationDate")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaAlta;
	
	@Schema(example = "05/12/2024 15:00:00")
	@JsonProperty("dischargeDate")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
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
