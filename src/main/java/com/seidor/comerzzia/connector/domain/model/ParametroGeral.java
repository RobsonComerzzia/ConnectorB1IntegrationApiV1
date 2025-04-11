package com.seidor.comerzzia.connector.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="parametro_geral")
public class ParametroGeral {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "id", length = 1, nullable = false)
	private Integer id; 
	
	@Column(name = "uid_actividad", length = 40, nullable = true)
	private String uidActividad;
	
}
