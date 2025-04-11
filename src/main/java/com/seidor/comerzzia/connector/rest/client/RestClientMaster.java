package com.seidor.comerzzia.connector.rest.client;

public interface RestClientMaster<T> {

	public void execute(T body, String url, String token);
	
}
