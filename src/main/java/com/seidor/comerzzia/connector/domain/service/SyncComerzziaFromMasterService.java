package com.seidor.comerzzia.connector.domain.service;

public interface SyncComerzziaFromMasterService<T>  {
	
	public void invokeApiComerzzia(String url, String token);
	
	public T getDataFromMasterB1();

}
