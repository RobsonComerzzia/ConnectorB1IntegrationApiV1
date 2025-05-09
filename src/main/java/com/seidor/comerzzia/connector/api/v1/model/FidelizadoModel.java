package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class FidelizadoModel implements Serializable {

	private static final long serialVersionUID = 17938472020L;

	@Schema(example = "01109382")
	@NotBlank
	@JsonProperty("code")
	private String codFidelizado;	
	
	@Schema(example = "Marcelo")
	@NotBlank
	@JsonProperty("firstName")
	private String nombre;
	
	@Schema(example = "Soarez")
	@NotBlank
	@JsonProperty("lastName")
	private String apellidos;
	
	@Schema(example = "Rua Direita, 34")
	@JsonProperty("address")
	private String domicilio;
	
	@Schema(example = "Jardim dos Reis")
	@JsonProperty("district")
	private String poblacion;
	
	@Schema(example = "Vitória")
	@JsonProperty("city")
	private String localidad;
	
	@Schema(example = "Espirito Santo")
	@JsonProperty("state")
	private String provincia;
	
	@Schema(example = "05872060")
	@JsonProperty("zipCode")
	private String cp;
	
	@Schema(example = "BR")
	@JsonProperty("countryCode")
	private String codpais;
	
	@Schema(example = "CPF")
	@JsonProperty("typeCodeDoc")
	private String codtipoiden;
	
	@Schema(example = "23567890")
	@JsonProperty("numberDoc")
	private String documento;
	
	@JsonProperty("observation")
	private String observaciones;	
	
	@Schema(example = "1980-09-13")
	@JsonProperty("birthDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fechaNascimiento;
	
	@Schema(example = "M")
	@JsonProperty("gender")
	private String sexo;
	
	@Schema(example = "C")
	@JsonProperty("maritalStatus")
	private String codestcivil;
	
	@Schema(example = "S")
	@JsonProperty("active")
	private String activo;	
	
}
