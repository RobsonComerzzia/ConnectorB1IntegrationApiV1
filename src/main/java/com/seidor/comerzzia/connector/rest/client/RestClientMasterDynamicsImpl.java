package com.seidor.comerzzia.connector.rest.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.connector.api.v1.model.DynamicArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.input.DynamicArticuloInput;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RestClientMasterDynamicsImpl implements RestClientMaster<List<DynamicArticuloModel>, List<DynamicArticuloInput>> {

	private static String NAME_CLASS = "[RestClientMasterDynamicsImpl]";
	
	@Override
	public void executeVoid(List<DynamicArticuloInput> body, String url, String token) {
		
		RestClient restClient = RestClient.builder()
                .defaultHeader(org.springframework.http.HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
		
		try {
			restClient.post()
			.uri(url)
			.body(body)
			.retrieve()		
	        .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
	        	String json = new String(res.getBody().readAllBytes());
	        	log.error("{} - Erro {}: {}", res.getStatusCode(), NAME_CLASS, json);
	         })
	        .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
	        	String json = new String(res.getBody().readAllBytes());
	        	log.error("{} - ERRO {}: {}", res.getStatusCode(), NAME_CLASS, json);
	         })	
			.toBodilessEntity();			
		} catch (Exception e) {
			log.error("{} - Falha ao atualizar dados de Dynamics no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
		
	}

	@Override
	public List<DynamicArticuloModel> execute(List<DynamicArticuloInput> body, String url, String token) {

		RestClient restClient = RestClient.builder()
                .defaultHeader(org.springframework.http.HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
		
		ResponseEntity<List<DynamicArticuloModel>> categorizacionList = null;
		
		try {
			categorizacionList = restClient.post()
			.uri(url)
			.body(body)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()		
	        .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
	        	String json = new String(res.getBody().readAllBytes());
	        	log.error("{} - Erro {}: {}", res.getStatusCode(), NAME_CLASS, json);
	         })
	        .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
	        	String json = new String(res.getBody().readAllBytes());
	        	log.error("{} - ERRO {}: {}", res.getStatusCode(), NAME_CLASS, json);
	         })	
			.toEntity(new ParameterizedTypeReference<List<DynamicArticuloModel>>() {});			
		} catch (Exception e) {
			log.error("{} - Falha ao atualizar dados de Dynamics no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
		
		return categorizacionList.getBody();
	}

}
