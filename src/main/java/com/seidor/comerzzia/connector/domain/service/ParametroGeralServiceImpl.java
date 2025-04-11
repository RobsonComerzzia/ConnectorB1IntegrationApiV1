package com.seidor.comerzzia.connector.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seidor.comerzzia.domain.exception.ParametroGeralNotFoundException;
import com.seidor.comerzzia.domain.model.master.ParametroGeral;
import com.seidor.comerzzia.domain.repository.master.ParametroGeralRepository;

@Service
public class ParametroGeralServiceImpl implements ParametroGeralService {

	@Autowired
	private ParametroGeralRepository parametroGeralRepository;
	
	@Transactional(value = "primaryTransactionManager")
	public ParametroGeral save(ParametroGeral parametroGeral) {
		parametroGeral.setId(1);
		return parametroGeralRepository.save(parametroGeral);
	}
	
	
	public ParametroGeral findOrFail() {
		return parametroGeralRepository.findById(1)
			.orElseThrow(() -> new ParametroGeralNotFoundException());
	}
	
}
