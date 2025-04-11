package com.seidor.comerzzia.connector.domain.service;

import com.seidor.comerzzia.connector.domain.model.ParametroGeral;

public interface ParametroGeralService {

	public ParametroGeral save(ParametroGeral param);
	
	public ParametroGeral findOrFail();
}
