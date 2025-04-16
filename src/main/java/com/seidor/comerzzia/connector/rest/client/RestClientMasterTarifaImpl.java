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
		    /*.headers(httpHeaders -> {
		        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		        httpHeaders.setBearerAuth(token);
		    })*/
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
	        .onStatus(httpStatusCode -> httpStatusCode.value() == 200, (req, res) -> {
	        	//Todo setar tabela temporaria com data de envio
	        	log.info("{} - Processo de atualização de Tarifa finalizado com sucesso.", NAME_CLASS);
	         })			
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
			log.error("{} - Falha ao atualizar dados de Tarifas no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
		
	}

}
