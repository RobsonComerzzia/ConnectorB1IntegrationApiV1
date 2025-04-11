package com.seidor.comerzzia.connector.exception;

public class ParametroGeralNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ParametroGeralNotFoundException() {
		super("Não existe parâmetro geral cadastrado");
	}
	
}
