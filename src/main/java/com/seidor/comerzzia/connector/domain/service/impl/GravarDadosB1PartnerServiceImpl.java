package com.seidor.comerzzia.connector.domain.service.impl;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.assembler.JsonPartnerInputDisassembler;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonPartnerInput;
import com.seidor.comerzzia.connector.domain.model.ItemB1;
import com.seidor.comerzzia.connector.domain.model.PartnerB1;
import com.seidor.comerzzia.connector.domain.repository.PartnerB1Repository;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.util.StringUtils;
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
		
		List<BigInteger> docEntries = partnersList.stream().map(p -> p.getDocEntry()).toList();
		
		List<PartnerB1> partnersBase = partnerB1Repository.findByDocEntryIn(docEntries);
		
		for (PartnerB1 partner : partnersList) {
			
			Optional<PartnerB1> partnerBase = partnersBase.stream()
					.filter(docEntry -> docEntry.getDocEntry().equals(partner.getDocEntry()))
					.findFirst();
			
			if (partnerBase.isPresent()) {
				partnerBase.get().setGuid(partner.getGuid());
				partnerBase.get().setTransType(partner.getTransType());
				partnerBase.get().setObjType(partner.getObjType());
				partnerBase.get().setCardCode(partner.getCardCode());
				partnerBase.get().setCardName(partner.getCardName());
				partnerBase.get().setCardFName(partner.getCardFName());
				partnerBase.get().setCardType(partner.getCardType());
				partnerBase.get().setValidFor(partner.getValidFor());
				partnerBase.get().setValidTo(partner.getValidTo());
				partnerBase.get().setValidFrom(partner.getValidFrom());
				partnerBase.get().setFrozenFor(partner.getFrozenFor());
				partnerBase.get().setFrozenTo(partner.getFrozenTo());
				partnerBase.get().setFrozenFrom(partner.getFrozenFrom());
				partnerBase.get().setAliasName(partner.getAliasName());
				partnerBase.get().setFreeText(partner.getFreeText());
				partnerBase.get().setDateTill(partner.getDateTill());
				partnerBase.get().setLangCode(partner.getLangCode());
				partnerBase.get().setCreateDate(partner.getCreateDate());
				partnerBase.get().setUpdateDate(partner.getUpdateDate());
				partnerBase.get().setUpdateDateMaster(LocalDateTime.now());
				partnerBase.get().setStreet(partner.getStreet());
				partnerBase.get().setBlock(partner.getBlock());
				partnerBase.get().setState(partner.getState());
				partnerBase.get().setZipCode(partner.getZipCode());
				partnerBase.get().setTaxId0(partner.getTaxId0());
				partnerBase.get().setTaxId4(partner.getTaxId4());
				partnerBase.get().setCreditLine(partner.getCreditLine());
				partnerBase.get().setBalance(partner.getBalance());
				partnersUpdated.add(partnerBase.get());
			} else {
				partnersUpdated.add(partner);
			}
		}
		
		return partnersUpdated;
		
	}

}
