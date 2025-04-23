package com.seidor.comerzzia.connector.domain.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seidor.comerzzia.connector.domain.model.ItemB1;

import jakarta.persistence.Tuple;

@Repository
public interface ItemB1Repository extends JpaRepository<ItemB1, ItemB1.pk_itemB1> {
	
	public Optional<ItemB1> findByItemCodeAndNcmCode(BigInteger itemCode, String ncmCode);
	
	public List<ItemB1> findByItemCodeInAndNcmCodeIn(List<BigInteger> itemsCode, List<String> ncmsCode);
	
	@Query(value = "select tax.state, item.item_code, item.ncm_code, tax.cst_icms, tax.icms "
			+ "from item_b1 item "
			+ "inner join tax_b1 tax "
			+ "on item.ncm_code = tax.ncm_code "
			+ "where item.update_date > item.last_send_date or item.last_send_date is null", nativeQuery = true)
	public List<Tuple> findItemTaxes();
	
	@Query(value = "select item_code, item_name, code_bars "
			+ "from item_b1 "
			+ "where update_date > last_send_date "
			+ "or last_send_date is null", nativeQuery = true)
	public List<Tuple> findItems();

}
