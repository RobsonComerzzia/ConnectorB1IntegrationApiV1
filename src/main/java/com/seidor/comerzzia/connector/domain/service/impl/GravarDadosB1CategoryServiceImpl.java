package com.seidor.comerzzia.connector.domain.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.assembler.JsonCategoryInputDisassembler;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonCategoryInput;
import com.seidor.comerzzia.connector.api.v1.model.input.innerclass.JsonCategoryInnerInput;
import com.seidor.comerzzia.connector.domain.model.CategoryB1;
import com.seidor.comerzzia.connector.domain.repository.CategoryB1Repository;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.util.Utils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GravarDadosB1CategoryServiceImpl implements GravarDadosB1Service<List<JsonCategoryInnerInput>> {

	@Value("${b1.size.paginate}")
	private Integer size;
	
	@Autowired
	private CategoryB1Repository categoryB1Repository; 
	
	@Autowired
	private JsonCategoryInputDisassembler jsonCategoryInputDisassembler;
	
	private static String NAME_CLASS = "[GravarDadosB1CategoryServiceImpl]";
	
	@Override
	@Transactional
	public void gravar(List<JsonCategoryInnerInput> data) {
		
		log.info("{} - Gravando dados de category na tabela temporária", NAME_CLASS);
		
		List<CategoryB1> categories = jsonCategoryInputDisassembler.toCollectionModel(data);
		
		categories = this.atualizarDadosCategory(categories);
		
		Integer pages = Utils.calcPages(categories, size);
		for (Integer page = 1; pages >= page; page++) {
			categoryB1Repository.saveAll(Utils.getPage(categories, page, size));
		}
		
		log.info("{} - Dados de category gravados com sucesso na tabela temporária", NAME_CLASS);
		
	}
	
	private List<CategoryB1> atualizarDadosCategory(List<CategoryB1> categories){
		
		List<CategoryB1> categoryUpdated = new ArrayList<CategoryB1>();
		
		List<String> codesList = categories.stream().map(category -> category.getCode()).toList();
		
		List<CategoryB1> categoriesBase = categoryB1Repository.findByCodeIn(codesList);
		
		for (CategoryB1 category : categories) {
			Optional<CategoryB1> categoryB1Base = categoriesBase.stream()
					.filter(c -> c.getCode().equals(category.getCode()))
					.findFirst();
			
			if (categoryB1Base.isPresent()) {
				categoryB1Base.get().setName(category.getName());
				categoryB1Base.get().setCreateDate(category.getCreateDate());
				categoryB1Base.get().setUpdateDate(category.getUpdateDate());
				categoryB1Base.get().setUpdateDateMaster(LocalDateTime.now());
				categoryUpdated.add(categoryB1Base.get());
			} else {
				categoryUpdated.add(category);
			}
		}
		
		return categoryUpdated;
		
	}

}
