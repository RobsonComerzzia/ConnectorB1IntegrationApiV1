package com.seidor.comerzzia.connector.domain.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seidor.comerzzia.connector.domain.model.PartnerB1;

import jakarta.persistence.Tuple;

public interface PartnerB1Repository extends JpaRepository<PartnerB1, PartnerB1.pk_partnerB1> {
	
	public Optional<PartnerB1> findByDocEntry(BigInteger docEntry);
	
	public List<PartnerB1> findByDocEntryIn(List<BigInteger> docentrys);
	
	public Optional<PartnerB1> findByTaxId0OrTaxId4(String cnpj, String cpf);
	
	public Optional<PartnerB1> findByCardName(String nombre);
	
	@Query(value = 
			"select "
			+ "  left(card_name, LOCATE(' ', card_name) - 1) as nombre "
			+ ", left(right(card_name, (CHARACTER_LENGTH(card_name) - (LOCATE(' ', card_name)))), 45) as apellidos "		
			+ ", left(trim(street), 50) as domicilio "
			+ ", block as poblacion "
			+ ", city as localidad "
			+ ", state as provincia "
			+ ", zip_code as cp "
			+ ", left(tax_id0, 20) as cnpj "
			+ ", left(tax_id4, 20) as cpf "
			+ ", credit_line as credito "
			+ "from partner_b1 "
			+ "where update_date_master > last_send_date or last_send_date is null "
			+ "and left(card_name, LOCATE(' ', card_name) - 1) <> '' "
			+ "and right(card_name, (CHARACTER_LENGTH(card_name) - (LOCATE(' ', card_name)))) is not null "
			+ "and (tax_id0 <> '' or tax_id4 <> '')", nativeQuery = true)
	public List<Tuple> findPartners();

}
