package com.seidor.comerzzia.connector.domain.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seidor.comerzzia.connector.domain.model.ItemPriceB1;

import jakarta.persistence.Tuple;

public interface ItemPriceB1Repository extends JpaRepository<ItemPriceB1, ItemPriceB1.pk_itemPriceB1> {
	
	public Optional<ItemPriceB1> findByItemCodeAndPriceList(BigInteger itemCode, BigInteger priceList);

	public List<ItemPriceB1> findByItemCodeInAndPriceListIn(List<BigInteger> itemCodes, List<BigInteger> pricesList);
	
	@Query(value = "select distinctrow p.item_code, pl.price_list, pl.valid_from, p.cost_price, p.price "
			+ "from item_price_b1 p "
			+ "inner join item_price_list_b1 pl "
			+ "on p.price_list = pl.price_list "
			+ "where p.update_date > p.last_send_date "
			+ "or p.last_send_date is null", nativeQuery = true)
	public Set<Tuple> findItemPrices();
	
}
