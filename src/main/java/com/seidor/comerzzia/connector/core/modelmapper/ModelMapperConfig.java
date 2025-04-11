package com.seidor.comerzzia.connector.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		
		
		
		/*
		var articuloInputToArticuloTypeMap = modelMapper.createTypeMap(ArticuloInput.class, Articulo.class);
		
		articuloInputToArticuloTypeMap.<String>addMapping(
				src -> src.getUnidadMedidaEtiqueta().getCodUmEtiqueta(),
				(dest, value) -> dest.setCodUmEtiqueta(value));
		*/
		
		
		return modelMapper;
	}
	
}
