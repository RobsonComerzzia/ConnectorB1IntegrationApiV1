package com.seidor.comerzzia.connector.rest.client;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface RestClientMasterReturn<R> {

	public ResponseEntity<R> execute(List<String> param, String url, String token);
	
}
