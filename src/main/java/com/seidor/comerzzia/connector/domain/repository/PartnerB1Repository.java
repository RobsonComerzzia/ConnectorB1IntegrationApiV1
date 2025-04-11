package com.seidor.comerzzia.connector.domain.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seidor.comerzzia.connector.domain.model.PartnerB1;

public interface PartnerB1Repository extends JpaRepository<PartnerB1, PartnerB1.pk_partnerB1> {
	
	public Optional<PartnerB1> findByDocEntry(BigInteger docEntry);
	
	public List<PartnerB1> findByDocEntryIn(List<BigInteger> docentrys);

}
