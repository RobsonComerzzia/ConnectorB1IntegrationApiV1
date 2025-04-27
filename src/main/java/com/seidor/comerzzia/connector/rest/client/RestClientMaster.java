package com.seidor.comerzzia.connector.rest.client;

public interface RestClientMaster<R, T> {

	public void executeVoid(T body, String url, String token);
	
	public R execute(T body, String url, String token);
	
}
