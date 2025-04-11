package com.seidor.comerzzia.connector.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.connector.api.v1.model.input.JsonTaxInput;
import com.seidor.comerzzia.connector.domain.model.TaxB1;;

@Component
public class JsonTaxInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public TaxB1 toDomainObject(JsonTaxInput jsonInput) {
		return modelMapper.map(jsonInput, TaxB1.class);
	}
	
	public List<TaxB1> toCollectionModel(List<JsonTaxInput> taxes) {
		return taxes.stream()
				.map(input -> modelMapper.map(input, TaxB1.class))
				.collect(Collectors.toList());
	}
	
	public void copyToDomainObject(JsonTaxInput jsonInput, TaxB1 taxB1) {
		modelMapper.map(jsonInput, taxB1);
	}
	
}
