package com.seidor.comerzzia.connector.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	INVALID_DATA("/dados-invalidos", "Dados inválidos"),
	ACCESS_DENIED("/acesso-negado", "Acesso negado"),
	SYSTEM_ERROR("/erro-de-sistema", "Erro de sistema"),
	INVALID_PARAMETER("/parametro-invalido", "Parâmetro inválido"),
	INCOMPREHENSIBLE_MESSAGE("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTITY_IN_USE("/entidade-em-uso", "Entidade em uso"),
	BUSINESS_RULE_ERROR("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://seidor.com.br" + path;
		this.title = title;
	}
	
}
