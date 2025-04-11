package com.seidor.comerzzia.connector.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemInput;
import com.seidor.comerzzia.connector.domain.model.ItemB1;

@Component
public class JsonItemInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ItemB1 toDomainObject(JsonItemInput jsonInput) {
		return modelMapper.map(jsonInput, ItemB1.class);
	}
	
	public List<ItemB1> toCollectionModel(List<JsonItemInput> items) {
		return items.stream()
				.map(input -> modelMapper.map(input, ItemB1.class))
				.collect(Collectors.toList());
	}
	
	public void copyToDomainObject(JsonItemInput jsonInput, ItemB1 itemB1) {
		modelMapper.map(jsonInput, itemB1);
	}
	
}
