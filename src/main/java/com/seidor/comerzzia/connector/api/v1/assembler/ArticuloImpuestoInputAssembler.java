package com.seidor.comerzzia.connector.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.connector.api.v1.model.ItemTaxResponseModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticuloImpuestoInput;

@Component
public class ArticuloImpuestoInputAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ArticuloImpuestoInput toModel(ItemTaxResponseModel tax) {
		return modelMapper.map(tax, ArticuloImpuestoInput.class);
	}
	
	public List<ArticuloImpuestoInput> toCollectionModel(List<ItemTaxResponseModel> taxes) {
		return taxes.stream()
				.map(tax -> toModel(tax))
				.collect(Collectors.toList());
	}
	
}
