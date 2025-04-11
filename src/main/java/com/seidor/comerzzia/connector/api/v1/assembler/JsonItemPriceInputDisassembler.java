package com.seidor.comerzzia.connector.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceInput;
import com.seidor.comerzzia.connector.domain.model.ItemPriceB1;

@Component
public class JsonItemPriceInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ItemPriceB1 toDomainObject(JsonItemPriceInput jsonInput) {
		return modelMapper.map(jsonInput, ItemPriceB1.class);
	}
	
	public List<ItemPriceB1> toCollectionModel(List<JsonItemPriceInput> items) {
		return items.stream()
				.map(input -> modelMapper.map(input, ItemPriceB1.class))
				.collect(Collectors.toList());
	}
	
	public void copyToDomainObject(JsonItemPriceInput jsonInput, ItemPriceB1 itemB1) {
		modelMapper.map(jsonInput, itemB1);
	}
	
}
