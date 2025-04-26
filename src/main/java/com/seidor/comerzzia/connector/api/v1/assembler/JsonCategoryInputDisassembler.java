package com.seidor.comerzzia.connector.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.JsonCategoryInnerInput;
import com.seidor.comerzzia.connector.domain.model.CategoryB1;

@Component
public class JsonCategoryInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryB1 toDomainObject(JsonCategoryInnerInput jsonInput) {
		return modelMapper.map(jsonInput, CategoryB1.class);
	}
	
	public List<CategoryB1> toCollectionModel(List<JsonCategoryInnerInput> items) {
		return items.stream()
				.map(input -> modelMapper.map(input, CategoryB1.class))
				.collect(Collectors.toList());
	}
	
	public void copyToDomainObject(JsonCategoryInnerInput jsonInput, CategoryB1 itemB1) {
		modelMapper.map(jsonInput, itemB1);
	}
	
}
