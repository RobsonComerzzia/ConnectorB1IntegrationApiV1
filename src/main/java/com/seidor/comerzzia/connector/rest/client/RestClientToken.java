package com.seidor.comerzzia.connector.rest.client;

import com.seidor.comerzzia.connector.api.v1.model.ResponseTokenModel;

public interface RestClientToken<T> {

	public ResponseTokenModel execute(T body, String url);
	
}
