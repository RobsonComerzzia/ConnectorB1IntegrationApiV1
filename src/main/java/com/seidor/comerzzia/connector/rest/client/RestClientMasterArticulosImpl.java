package com.seidor.comerzzia.connector.rest.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.connector.api.v1.model.ArticuloModel;
import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RestClientMasterArticulosImpl implements RestClientMaster<List<ArticuloModel>, ArticulosInput> {

	private static String NAME_CLASS = "[RestClientMasterArticulosImpl]";
	
	@Override
	public void executeVoid(ArticulosInput body, String url, String token) {
		
		RestClient restClient = RestClient.create();
		
		try {
			restClient.post()
			.uri(url)
			.body(body.getArticulos())
			//.header(Constants.TOKEN, token)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
	        .onStatus(httpStatusCode -> httpStatusCode.value() == 200, (req, res) -> {
	        	//Todo setar tabela temporaria com data de envio
	        	log.info("{} - Processo de atualização de Produtos finalizado com sucesso.", NAME_CLASS);
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
			log.error("{} - Falha ao atualizar dados de Produtos no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
	}

	@Override
	public List<ArticuloModel> execute(ArticulosInput body, String url, String token) {
		
		RestClient restClient = RestClient.create();
		
		ResponseEntity<List<ArticuloModel>> artoculoList = null;
		
		try {
			artoculoList = restClient.post()
					.uri(url)
					.body(body.getArticulos())
					//.header(Constants.TOKEN, token)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
			        .onStatus(httpStatusCode -> httpStatusCode.value() == 200, (req, res) -> {
			        	//Todo setar tabela temporaria com data de envio
			        	log.info("{} - Processo de atualização de Produtos finalizado com sucesso.", NAME_CLASS);
			         })			
			        .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
			        	String json = new String(res.getBody().readAllBytes());
			        	log.error("{} - Erro {}: {}", res.getStatusCode(), NAME_CLASS, json);
			         })
			        .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
			        	String json = new String(res.getBody().readAllBytes());
			        	log.error("{} - ERRO {}: {}", res.getStatusCode(), NAME_CLASS, json);
			         })	
			        .toEntity(new ParameterizedTypeReference<List<ArticuloModel>>() {});					
		} catch (Exception e) {
			log.error("{} - Falha ao atualizar dados de Produtos no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
		
		return artoculoList.getBody();
	}


}
