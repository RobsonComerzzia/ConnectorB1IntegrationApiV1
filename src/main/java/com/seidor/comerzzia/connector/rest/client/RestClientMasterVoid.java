package com.seidor.comerzzia.connector.rest.client;

public interface RestClientMasterVoid<T> {

	public void execute(T body, String url, String token);
	
}
