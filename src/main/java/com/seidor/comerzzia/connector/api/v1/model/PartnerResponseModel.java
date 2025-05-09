package com.seidor.comerzzia.connector.api.v1.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PartnerResponseModel implements Serializable {

	private static final long serialVersionUID = 13122341543L;
	
	private String nombre;
	
	private String apellidos;
	
	private String domicilio;
	
	private String poblacion;
	
	private String localidad;
	
	private String provincia;
	
	private String cp;
	
	private String cnpj;
	
	private String cpf;
	
	private String creditLine;

}
