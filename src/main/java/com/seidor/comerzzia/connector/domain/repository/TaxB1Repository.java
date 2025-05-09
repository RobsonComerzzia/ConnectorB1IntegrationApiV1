package com.seidor.comerzzia.connector.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seidor.comerzzia.connector.domain.model.TaxB1;

public interface TaxB1Repository extends JpaRepository<TaxB1, TaxB1.pk_taxB1> {
	
	List<TaxB1> findByNcm(String ncm);

}
