package com.seidor.comerzzia.connector.domain.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seidor.comerzzia.connector.domain.model.ItemPriceListB1;


public interface ItemPriceListB1Repository extends JpaRepository<ItemPriceListB1, ItemPriceListB1.pk_itemPriceListB1> {
	
	public Optional<ItemPriceListB1> findByPriceList(BigInteger priceList);

}
