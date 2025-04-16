package com.seidor.comerzzia.connector.domain.service;

import org.springframework.scheduling.annotation.Async;

public interface SyncComerzziaFromMasterService<T>  {
	
	@Async("asyncB1Executor")
	public void invokeApiComerzzia(String url, String token);
	
	public T getDataFromMasterB1();

}
