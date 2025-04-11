package com.seidor.comerzzia.connector.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.connector.api.v1.model.input.JsonPartnerInput;
import com.seidor.comerzzia.connector.domain.model.PartnerB1;

@Component
public class JsonPartnerInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PartnerB1 toDomainObject(JsonPartnerInput jsonInput) {
		return modelMapper.map(jsonInput, PartnerB1.class);
	}
	
	public List<PartnerB1> toCollectionModel(List<JsonPartnerInput> partners) {
		return partners.stream()
				.map(input -> modelMapper.map(input, PartnerB1.class))
				.collect(Collectors.toList());
	}
	
	public void copyToDomainObject(JsonPartnerInput jsonInput, PartnerB1 partnerB1) {
		modelMapper.map(jsonInput, partnerB1);
	}
	
}
