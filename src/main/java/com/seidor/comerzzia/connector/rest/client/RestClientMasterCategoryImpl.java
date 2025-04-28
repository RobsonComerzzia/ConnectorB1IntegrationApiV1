package com.seidor.comerzzia.connector.rest.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.connector.api.v1.model.CategorizacionModel;
import com.seidor.comerzzia.connector.api.v1.model.input.CategorizacionInput;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RestClientMasterCategoryImpl implements RestClientMaster<List<CategorizacionModel>, List<CategorizacionInput>> {

	private static String NAME_CLASS = "[RestClientMasterCategoryImpl]";
	
	@Override
	public void executeVoid(List<CategorizacionInput> body, String url, String token) {
		
		RestClient restClient = RestClient.create();
		
		try {
			restClient.post()
			.uri(url)
			.body(body)
		    /*.headers(httpHeaders -> {
		        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		        httpHeaders.setBearerAuth(token);
		    })*/
			//.accept(MediaType.APPLICATION_JSON)
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
			log.error("{} - Falha ao atualizar dados de Categorias no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
		
	}

	@Override
	public List<CategorizacionModel> execute(List<CategorizacionInput> body, String url, String token) {

		RestClient restClient = RestClient.create();
		
		ResponseEntity<List<CategorizacionModel>> categorizacionList = null;
		
		try {
			categorizacionList = restClient.post()
			.uri(url)
			.body(body)
		    /*.headers(httpHeaders -> {
		        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		        httpHeaders.setBearerAuth(token);
		    })*/
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
			.toEntity(new ParameterizedTypeReference<List<CategorizacionModel>>() {});			
		} catch (Exception e) {
			log.error("{} - Falha ao atualizar dados de Tarifas no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
		
		return categorizacionList.getBody();
	}

}
