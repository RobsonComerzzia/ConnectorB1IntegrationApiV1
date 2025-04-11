package com.seidor.comerzzia.connector.domain.service;

import java.util.concurrent.CompletableFuture;

public interface IntegracaoB1CmzProcessService {
	
	public CompletableFuture<Void> processIntegration(Class<?>[] types, Object[] values, String url);

}
