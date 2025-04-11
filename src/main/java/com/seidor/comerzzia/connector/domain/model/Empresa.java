package com.seidor.comerzzia.connector.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="empresa")
public class Empresa {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "uid_actividad", length = 40, nullable = false)
	private String uidActividad;
	
	@Column(name = "codemp", length = 4, nullable = false)
	private String codemp;
	
	@Column(name = "desemp", length = 45, nullable = false)
	private String desemp;
	
	@Column(name = "cnpj", length = 14, nullable = false)
	private String cnpj;
	
}
