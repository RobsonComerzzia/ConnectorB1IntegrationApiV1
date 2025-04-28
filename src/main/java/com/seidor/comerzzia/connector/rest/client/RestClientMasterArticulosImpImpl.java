package com.seidor.comerzzia.connector.rest.client;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoInput;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosImpuestoModel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RestClientMasterArticulosImpImpl implements RestClientMaster<ArticulosImpuestoModel ,ArticulosImpuestoInput> {

	private static String NAME_CLASS = "[RestClientMasterImpl]";
	
	@Override
	public void executeVoid(ArticulosImpuestoInput body, String url, String token) {
		
		RestClient restClient = RestClient.create();
		
		try {
			restClient.post()
			.uri(url)
			.body(body)
		    /*.headers(httpHeaders -> {
		        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		        httpHeaders.setBearerAuth(token);
		    })*/
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
	        .onStatus(httpStatusCode -> httpStatusCode.is2xxSuccessful(), (req, res) -> {
	        	String json = new String(res.getBody().readAllBytes());
	        	log.info("{} - Sucesso {} : {}", res.getStatusCode(), NAME_CLASS, json);
	         })
	        .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
	        	String json = new String(res.getBody().readAllBytes());
	        	log.error("{} - Erro {} : {}", res.getStatusCode(), NAME_CLASS, json);
	         })
	        .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
	        	String json = new String(res.getBody().readAllBytes());
	        	log.error("{} - ERRO {}: {}", res.getStatusCode(), NAME_CLASS, json);
	         })	
			.toBodilessEntity();			
		} catch (Exception e) {
			log.error("{} - Falha ao atualizar dados de Impostos no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
	}

	@Override
	public ArticulosImpuestoModel execute(ArticulosImpuestoInput body, String url, String token) {
	
		RestClient restClient = RestClient.create();
		
		ResponseEntity<ArticulosImpuestoModel> response = null;
		
		try {
			response = restClient.post()
					.uri(url)
					.body(body)
				    /*.headers(httpHeaders -> {
				        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
				        httpHeaders.setBearerAuth(token);
				    })*/
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
			        .onStatus(httpStatusCode -> httpStatusCode.is2xxSuccessful(), (req, res) -> {
			        	String json = new String(res.getBody().readAllBytes());
			        	log.info("{} - Sucesso {} : {}", res.getStatusCode(), NAME_CLASS, json);
			         })
			        .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
			        	String json = new String(res.getBody().readAllBytes());
			        	log.error("{} - Erro {} : {}", res.getStatusCode(), NAME_CLASS, json);
			         })
			        .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
			        	String json = new String(res.getBody().readAllBytes());
			        	log.error("{} - ERRO {}: {}", res.getStatusCode(), NAME_CLASS, json);
			         })	
					.toEntity(ArticulosImpuestoModel.class);			
		} catch (Exception e) {
			log.error("{} - Falha ao atualizar dados de Impostos no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
		
		return response.getBody();
	}

}
