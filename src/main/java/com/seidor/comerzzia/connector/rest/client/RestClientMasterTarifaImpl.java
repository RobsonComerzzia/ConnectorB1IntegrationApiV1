package com.seidor.comerzzia.connector.rest.client;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.connector.api.v1.model.input.TarifaDetInput;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RestClientMasterTarifaImpl implements RestClientMaster<List<TarifaDetInput>> {

	private static String NAME_CLASS = "[RestClientMasterTarifaImpl]";
	
	@Override
	public void execute(List<TarifaDetInput> body, String url, String token) {
		
		RestClient restClient = RestClient.create();
		
		try {
			restClient.post()
			.uri(url)
			.body(body)
		    .headers(httpHeaders -> {
		        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		        httpHeaders.setBearerAuth(token);
		    })
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
	        .onStatus(httpStatusCode -> httpStatusCode.value() == 404, (req, res) -> {
	        	String json = new String(res.getBody().readAllBytes());
	        	log.error("{} - Erro: {}", NAME_CLASS, json);
	         })
	        .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
	        	String json = new String(res.getBody().readAllBytes());
	        	log.error("{} - ERRO: {}", NAME_CLASS, json);
	         })	
			.toBodilessEntity();			
		} catch (Exception e) {
			log.error("{} - Falha ao atualizar dados de Tarifas no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
		
	}

}
