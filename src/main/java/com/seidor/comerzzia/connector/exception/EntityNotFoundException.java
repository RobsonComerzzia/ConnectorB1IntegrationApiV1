package com.seidor.comerzzia.connector.exception;

public abstract class EntityNotFoundException extends BussinessRoleException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}
