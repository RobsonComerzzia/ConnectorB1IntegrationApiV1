package com.seidor.comerzzia.connector.domain.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seidor.comerzzia.connector.domain.model.ItemPriceB1;

public interface ItemPriceB1Repository extends JpaRepository<ItemPriceB1, ItemPriceB1.pk_itemPriceB1> {
	
	public Optional<ItemPriceB1> findByItemCodeAndPriceList(BigInteger itemCode, BigInteger priceList);

	public List<ItemPriceB1> findByItemCodeInAndPriceListIn(List<BigInteger> itemCodes, List<BigInteger> pricesList);
}
