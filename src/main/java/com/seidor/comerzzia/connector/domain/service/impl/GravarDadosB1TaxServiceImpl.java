package com.seidor.comerzzia.connector.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.assembler.JsonTaxInputDisassembler;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonTaxInput;
import com.seidor.comerzzia.connector.domain.model.TaxB1;
import com.seidor.comerzzia.connector.domain.repository.TaxB1Repository;
import com.seidor.comerzzia.connector.domain.service.GravarDadosB1Service;
import com.seidor.comerzzia.connector.util.Utils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GravarDadosB1TaxServiceImpl implements GravarDadosB1Service<List<JsonTaxInput>> {

	@Value("${b1.size.paginate}")
	private Integer size;
	
	@Autowired
	private TaxB1Repository taxB1Repository; 
	
	@Autowired
	private JsonTaxInputDisassembler jsonTaxInputDisassebler;
	
	private static String NAME_CLASS = "[GravarDadosB1TaxServiceImpl]";
	
	@Override
	@Transactional
	public void gravar(List<JsonTaxInput> data) {
		
		log.info("{} - Gravando dados de tax na tabela temporária", NAME_CLASS);
		
		List<TaxB1> taxes = jsonTaxInputDisassebler.toCollectionModel(data);
		
		//Seta a data de inclusão do registro.
		taxes.stream().forEach(tax -> tax.setProcessDate(LocalDateTime.now()));
		
		taxB1Repository.deleteAll();
		
		Integer pages = Utils.calcPages(taxes, size);
		for (Integer page = 1; pages >= page; page++) {
			taxB1Repository.saveAll(Utils.getPage(taxes, page, size));
		}
		
		log.info("{} - Dados de tax gravados com sucesso na tabela temporária", NAME_CLASS);
		
	}

}
