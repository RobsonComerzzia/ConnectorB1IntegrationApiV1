package com.seidor.comerzzia.connector.domain.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.assembler.JsonItemInputDisassembler;
import com.seidor.comerzzia.connector.api.v1.assembler.JsonItemPriceInputDisassembler;
import com.seidor.comerzzia.connector.api.v1.assembler.JsonItemPriceListInputDisassembler;
import com.seidor.comerzzia.connector.api.v1.model.input.ItemsGravarInput;
import com.seidor.comerzzia.connector.domain.model.ItemB1;
import com.seidor.comerzzia.connector.domain.model.ItemPriceB1;
import com.seidor.comerzzia.connector.domain.model.ItemPriceListB1;
import com.seidor.comerzzia.connector.domain.repository.ItemB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceB1Repository;
import com.seidor.comerzzia.connector.domain.repository.ItemPriceListB1Repository;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.util.StringUtils;
import com.seidor.comerzzia.connector.util.Utils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GravarDadosB1ItemServiceImpl implements GravarDadosB1Service<ItemsGravarInput> {

	@Value("${b1.size.paginate}")
	private Integer size;
	
	@Autowired
	private ItemB1Repository itemB1Repository; 
	
	@Autowired
	private ItemPriceB1Repository itemPriceB1Repository; 
	
	@Autowired
	private ItemPriceListB1Repository itemPriceListB1Repository; 
	
	@Autowired
	private JsonItemInputDisassembler jsonItemInputDisassebler;
	
	@Autowired
	private JsonItemPriceInputDisassembler jsonItemPriceInputDisassebler;
	
	@Autowired
	private JsonItemPriceListInputDisassembler jsonItemPriceListInputDisassebler;
	
	private static String NAME_CLASS = "[GravarDadosB1ItemServiceImpl]";
	
	@Override
	@Transactional
	public void gravar(ItemsGravarInput data) {
		
		log.info("{} - Gravando dados de item na tabela temporária", NAME_CLASS);
		
		List<ItemB1> items = jsonItemInputDisassebler.toCollectionModel(data.getItems());
		
		List<ItemPriceB1> itemsPrice = jsonItemPriceInputDisassebler.toCollectionModel(data.getItemsPrice());
		
		List<ItemPriceListB1> itemsPriceList = jsonItemPriceListInputDisassebler.toCollectionModel(data.getItemsPriceList());
		
		items = atualizarDadosItems(items);
		
		Integer pages = Utils.calcPages(items, size);
		for (Integer page = 1; pages >= page; page++) {
			itemB1Repository.saveAll(Utils.getPage(items, page, size));
		}
		
		itemsPrice = atualizarDadosItemsPrice(itemsPrice);
		
		pages = Utils.calcPages(itemsPrice, size);
		for (Integer page = 1; pages >= page; page++) {
			itemPriceB1Repository.saveAll(Utils.getPage(itemsPrice, page, size));
		}
		
		itemsPriceList = atualizarDadosItemsPriceList(itemsPriceList);
		
		pages = Utils.calcPages(itemsPriceList, size);
		for (Integer page = 1; pages >= page; page++) {
			itemPriceListB1Repository.saveAll(Utils.getPage(itemsPriceList, page, size));
		}
		
		log.info("{} - Dados de item (item, price, price list e unit) gravados com sucesso na tabela temporária", NAME_CLASS);
		
	}
	
	private List<ItemB1> atualizarDadosItems(List<ItemB1> items){
		
		List<ItemB1> itemsUpdated = new ArrayList<ItemB1>();
		
		List<String> itemsCode = items.stream().map(itemCode -> itemCode.getItemCode()).toList();
		
		List<String> ncmsCodesList = items.stream().map(ncmCode -> StringUtils.tratarString(ncmCode .getNcmCode())).toList();
		
		List<ItemB1> itemsBase = itemB1Repository.findByItemCodeInAndNcmCodeIn(itemsCode, ncmsCodesList);
		
		for (ItemB1 item : items) {
			Optional<ItemB1> itemB1Base = itemsBase.stream()
					.filter(itemNcm -> itemNcm.getItemCode().equals(item.getItemCode()))
					.filter(itemNcm -> itemNcm.getNcmCode().equals(StringUtils.tratarString(item.getNcmCode())))
					.findFirst();
			
			if (itemB1Base.isPresent()) {
				itemB1Base.get().setGuid(item.getGuid());
				itemB1Base.get().setDocEntry(item.getDocEntry());
				itemB1Base.get().setTransType(item.getTransType());
				itemB1Base.get().setObjType(item.getObjType());
				itemB1Base.get().setItemName(item.getItemName());
				itemB1Base.get().setItemClass(item.getItemClass());
				itemB1Base.get().setValidFor(item.getValidFor());
				itemB1Base.get().setValidFrom(item.getValidFrom());
				itemB1Base.get().setValidTo(item.getValidTo());
				itemB1Base.get().setFrozenFor(item.getFrozenFor());
				itemB1Base.get().setFrozenTo(item.getFrozenTo());
				itemB1Base.get().setFrozenFrom(item.getFrozenFrom());
				itemB1Base.get().setSellItem(item.getSellItem());
				itemB1Base.get().setCreateDate(item.getCreateDate());
				itemB1Base.get().setCodeBars(item.getCodeBars());
				itemB1Base.get().setSUoMEntry(item.getSUoMEntry());
				itemB1Base.get().setU_cmzb1_categ(item.getU_cmzb1_categ());
				itemB1Base.get().setSalUnitMsr(item.getSalUnitMsr());
				itemB1Base.get().setNumInSale(item.getNumInSale());
				itemB1Base.get().setSalPackMsr(item.getSalPackMsr());
				itemB1Base.get().setSalPackUnit(item.getSalPackUnit());
				itemB1Base.get().setU_cmzB1_venda_unit(item.getU_cmzB1_venda_unit());
				itemB1Base.get().setUpdateDate(LocalDateTime.now());
				itemsUpdated.add(itemB1Base.get());
			} else {
				if (item.getNcmCode() != null && item.getItemName() != null)
					itemsUpdated.add(item);
			}
		}
		
		return itemsUpdated;
		
	}
	
	private List<ItemPriceB1> atualizarDadosItemsPrice(List<ItemPriceB1> itemsPrice){
		
		List<ItemPriceB1> itemsUpdated = new ArrayList<>();
		
		List<String> itemsCode = itemsPrice.stream().map(itemCode -> itemCode.getItemCode()).toList();
		
		List<String> pricesList = itemsPrice.stream().map(priceList -> priceList.getPriceList()).toList();
		
		List<ItemPriceB1> itemsPriceBase = itemPriceB1Repository.findByItemCodeInAndPriceListIn(itemsCode, pricesList);
		
		for (ItemPriceB1 item : itemsPrice) {
			Optional<ItemPriceB1> itemB1Base = itemsPriceBase.stream()
					.filter(itemPrice -> itemPrice.getItemCode().equals(item.getItemCode()))
					.filter(itemPrice -> itemPrice.getPriceList().equals(item.getPriceList()))
					.findFirst();
			
			if (itemB1Base.isPresent()) {
				itemB1Base.get().setGuid(item.getGuid());
				itemB1Base.get().setPrice(item.getPrice());
				itemB1Base.get().setUomCode(item.getUomCode());
				itemB1Base.get().setUpdateDate(LocalDateTime.now());
				itemsUpdated.add(itemB1Base.get());
			} else {
				itemsUpdated.add(item);
			}
		}
		
		return itemsUpdated;
		
	}
	
	private List<ItemPriceListB1> atualizarDadosItemsPriceList(List<ItemPriceListB1> itemsPriceList){
		
		List<ItemPriceListB1> itemsUpdated = new ArrayList<>();
		
		List<String> priceLists = itemsPriceList.stream().map(itemCode -> itemCode.getPriceList()).toList();
		
		List<ItemPriceListB1> itemsPriceListB1Base = itemPriceListB1Repository.findByPriceListIn(priceLists);
		
		for (ItemPriceListB1 item : itemsPriceList) {
			
			Optional<ItemPriceListB1> itemPriceListB1Base = itemsPriceListB1Base.stream()
					.filter(itemPrice -> itemPrice.getPriceList().equals(item.getPriceList()))
					.findFirst();
			
			if (itemPriceListB1Base.isPresent()) {
				itemPriceListB1Base.get().setGuid(item.getGuid());
				itemPriceListB1Base.get().setListName(item.getListName());
				itemPriceListB1Base.get().setValidFor(item.getValidFor());
				itemPriceListB1Base.get().setValidFrom(item.getValidFrom());
				itemPriceListB1Base.get().setValidTo(item.getValidTo());
				itemPriceListB1Base.get().setUpdateDate(LocalDateTime.now());
				itemsUpdated.add(itemPriceListB1Base.get());
			} else {
				itemsUpdated.add(item);
			}
		}
		
		return itemsUpdated;
		
	}

}
