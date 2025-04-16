package com.seidor.comerzzia.connector.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seidor.comerzzia.connector.api.controller.openapi.IntegracaoB1ErpToCmzControllerOpenApi;
import com.seidor.comerzzia.connector.api.v1.model.IntegracaoB1Model;
import com.seidor.comerzzia.connector.core.security.CheckSecurity;
import com.seidor.comerzzia.connector.domain.service.ScheduleProcessService;


@RestController
@RequestMapping(path = "/v1/erptocmz/b1", produces = MediaType.APPLICATION_JSON_VALUE)
public class IntegracaoB1ErpToCmzController implements IntegracaoB1ErpToCmzControllerOpenApi<IntegracaoB1Model> {

	@Autowired
	private ScheduleProcessService startProcessService;

	@CheckSecurity.AllCliendIdPermissioes.CanReadWriteAndIsAuthenticated
	@Override
	@PostMapping()
	@ResponseStatus(HttpStatus.OK)
	public IntegracaoB1Model insert() {

		return startProcessService.startProcess();
		
	}
	
}
