package com.seidor.comerzzia.connector.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seidor.comerzzia.connector.domain.model.CategoryB1;

public interface CategoryB1Repository extends JpaRepository<CategoryB1, CategoryB1.pk_categoryB1> {
	
	public List<CategoryB1> findByCodeIn(List<String> codes);
	
	@Query("select c from CategoryB1 c where c.updateDateMaster > c.lastSendDate or c.lastSendDate is null")
	public List<CategoryB1> find();

}
