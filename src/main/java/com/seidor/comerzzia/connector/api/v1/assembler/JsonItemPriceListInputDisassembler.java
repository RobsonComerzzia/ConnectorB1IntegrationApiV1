package com.seidor.comerzzia.connector.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceListInput;
import com.seidor.comerzzia.connector.domain.model.ItemPriceListB1;

@Component
public class JsonItemPriceListInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ItemPriceListB1 toDomainObject(JsonItemPriceListInput jsonInput) {
		return modelMapper.map(jsonInput, ItemPriceListB1.class);
	}
	
	public List<ItemPriceListB1> toCollectionModel(List<JsonItemPriceListInput> items) {
		return items.stream()
				.map(input -> modelMapper.map(input, ItemPriceListB1.class))
				.collect(Collectors.toList());
	}
	
	public void copyToDomainObject(JsonItemPriceListInput jsonInput, ItemPriceListB1 itemB1) {
		modelMapper.map(jsonInput, itemB1);
	}
	
}
