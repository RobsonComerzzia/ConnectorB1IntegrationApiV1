package com.seidor.comerzzia.connector.domain.service;

import java.util.concurrent.CompletableFuture;

public interface SyncComerzziaFromMasterService<T>  {
	
	public CompletableFuture<Void> invokeApiComerzzia(String url, String token);
	
	public T getDataFromMasterB1();

}
