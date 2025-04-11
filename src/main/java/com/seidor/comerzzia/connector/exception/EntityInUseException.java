package com.seidor.comerzzia.connector.exception;

public class EntityInUseException extends BussinessRoleException {

	private static final long serialVersionUID = 1L;

	public EntityInUseException(String mensagem) {
		super(mensagem);
	}
	
}
