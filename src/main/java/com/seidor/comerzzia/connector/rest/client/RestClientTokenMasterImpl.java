package com.seidor.comerzzia.connector.rest.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.connector.api.v1.model.ResponseTokenModel;
import com.seidor.comerzzia.connector.api.v1.model.input.AuthenticationInput;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestClientTokenMasterImpl implements RestClientToken<AuthenticationInput> {

	private static String NAME_CLASS = "[RestClientTokenMasterImpl]";
	
	@Override
	public ResponseTokenModel execute(AuthenticationInput body, String url) {
		
		RestClient restClient = RestClient.create();
		
		ResponseEntity<ResponseTokenModel> result = null;
		
		result = restClient.post()
				.uri(url)
				.body(body)
				.retrieve()
		        .onStatus(httpStatusCode -> httpStatusCode.value() == 302, (req, res) -> {
		        	String json = new String(res.getBody().readAllBytes());
		        	log.error("{} - {}", NAME_CLASS, json);
		         })
		        .onStatus(httpStatusCode -> httpStatusCode.value() == 422, (req, res) -> {
		        	String json = new String(res.getBody().readAllBytes());
		        	log.error("{} - {}", NAME_CLASS, json);
		         })
		        .onStatus(httpStatusCode -> httpStatusCode.value() == 404, (req, res) -> {
		        	String json = new String(res.getBody().readAllBytes());
		        	log.error("{} - {}", NAME_CLASS, json);
		         })
		        .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
		        	String json = new String(res.getBody().readAllBytes());
		        	log.error("{} - ERRO url {}: {}", NAME_CLASS, url, json);
		         })
		        .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
		        	String json = new String(res.getBody().readAllBytes());
		        	log.error("{} - ERRO url {}: {}", NAME_CLASS, url, json);
		         })	
				.toEntity(ResponseTokenModel.class);
		
		return result.getBody();
		
	}
	
	

}
