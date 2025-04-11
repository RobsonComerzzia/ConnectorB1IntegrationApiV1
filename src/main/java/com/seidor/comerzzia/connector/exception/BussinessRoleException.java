package com.seidor.comerzzia.connector.exception;

public class BussinessRoleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BussinessRoleException(String mensagem) {
		super(mensagem);
	}
	
	public BussinessRoleException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
