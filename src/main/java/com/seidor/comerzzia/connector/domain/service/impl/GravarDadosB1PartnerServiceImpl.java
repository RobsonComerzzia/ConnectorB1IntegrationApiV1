package com.seidor.comerzzia.connector.domain.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.assembler.JsonPartnerInputDisassembler;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonPartnerInput;
import com.seidor.comerzzia.connector.domain.model.PartnerB1;
import com.seidor.comerzzia.connector.domain.repository.PartnerB1Repository;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.util.Utils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GravarDadosB1PartnerServiceImpl implements GravarDadosB1Service<List<JsonPartnerInput>> {

	@Value("${b1.size.paginate}")
	private Integer size;
	
	@Autowired
	private PartnerB1Repository partnerB1Repository; 
	
	@Autowired
	private JsonPartnerInputDisassembler jsonPartnerInputDisassebler;
	
	private static String NAME_CLASS = "[GravarDadosPartnerServiceImpl]";
	
	@Override
	@Transactional
	public void gravar(List<JsonPartnerInput> data) {
		
		log.info("{} - Gravando dados de partner na tabela temporária", NAME_CLASS);
		
		List<PartnerB1> partners = jsonPartnerInputDisassebler.toCollectionModel(data);
		
		partners = atualizarDadosPartners(partners);
		
		Integer pages = Utils.calcPages(partners, size);
		for (Integer page = 1; pages >= page; page++) {
			partnerB1Repository.saveAll(Utils.getPage(partners, page, size));
		}
				
		log.info("{} - Dados de partner gravados com sucesso na tabela temporária", NAME_CLASS);
		
	}
	
	private List<PartnerB1> atualizarDadosPartners(List<PartnerB1> partnersList){
		
		List<PartnerB1> partnersUpdated = new ArrayList<>();
		
		for (PartnerB1 partner : partnersList) {
			Optional<PartnerB1> itemB1Base = partnerB1Repository.findByDocEntry(partner.getDocEntry());
			if (itemB1Base.isPresent()) {
				itemB1Base.get().setGuid(partner.getGuid());
				itemB1Base.get().setTransType(partner.getTransType());
				itemB1Base.get().setObjType(partner.getObjType());
				itemB1Base.get().setCardCode(partner.getCardCode());
				itemB1Base.get().setCardName(partner.getCardName());
				itemB1Base.get().setCardFName(partner.getCardFName());
				itemB1Base.get().setCardType(partner.getCardType());
				itemB1Base.get().setValidFor(partner.getValidFor());
				itemB1Base.get().setValidTo(partner.getValidTo());
				itemB1Base.get().setValidFrom(partner.getValidFrom());
				itemB1Base.get().setFrozenFor(partner.getFrozenFor());
				itemB1Base.get().setFrozenTo(partner.getFrozenTo());
				itemB1Base.get().setFrozenFrom(partner.getFrozenFrom());
				itemB1Base.get().setAliasName(partner.getAliasName());
				itemB1Base.get().setFreeText(partner.getFreeText());
				itemB1Base.get().setDateTill(partner.getDateTill());
				itemB1Base.get().setLangCode(partner.getLangCode());
				itemB1Base.get().setCreateDate(partner.getCreateDate());
				itemB1Base.get().setUpdateDate(partner.getUpdateDate());
				itemB1Base.get().setUpdateDateMaster(LocalDateTime.now());
				itemB1Base.get().setStreet(partner.getStreet());
				itemB1Base.get().setBlock(partner.getBlock());
				itemB1Base.get().setState(partner.getState());
				itemB1Base.get().setZipCode(partner.getZipCode());
				itemB1Base.get().setTaxId0(partner.getTaxId0());
				itemB1Base.get().setTaxId4(partner.getTaxId4());
				itemB1Base.get().setCreditLine(partner.getCreditLine());
				itemB1Base.get().setBalance(partner.getBalance());
				partnersUpdated.add(itemB1Base.get());
			} else {
				partnersUpdated.add(partner);
			}
		}
		
		return partnersUpdated;
		
	}

}
