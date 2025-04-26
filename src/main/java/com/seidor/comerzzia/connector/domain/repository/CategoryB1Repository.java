package com.seidor.comerzzia.connector.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seidor.comerzzia.connector.domain.model.CategoryB1;

public interface CategoryB1Repository extends JpaRepository<CategoryB1, CategoryB1.pk_categoryB1> {
	
	public List<CategoryB1> findByCodeIn(List<String> codes);

}
