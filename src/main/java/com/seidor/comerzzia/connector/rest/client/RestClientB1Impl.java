package com.seidor.comerzzia.connector.rest.client;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seidor.comerzzia.connector.api.v1.model.GuidB1Model;
import com.seidor.comerzzia.connector.api.v1.model.VerifyB1Model;
import com.seidor.comerzzia.connector.api.v1.model.input.GuidB1ModelInput;
import com.seidor.comerzzia.connector.api.v1.model.input.SetReceivedB1Input;
import com.seidor.comerzzia.connector.api.v1.model.input.VerifyB1ModelInput;
import com.seidor.comerzzia.constant.Constants;
import com.seidor.comerzzia.domain.exception.FileNameNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RestClientB1Impl implements RestClientB1<GuidB1ModelInput, GuidB1Model, VerifyB1ModelInput, VerifyB1Model> {
	
	private static String NAME_CLASS = "[RestClientB1Impl]";
	
	@Override
	public GuidB1Model getGuid(GuidB1ModelInput body, String url) throws FileNameNotFoundException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		RestClient restClient = RestClient.create();
		
		GuidB1Model resultError = new GuidB1Model();
		
		ResponseEntity<GuidB1Model> result = null;
		
		String urlFinal = url + "/guid";
		
		result = restClient.get()
				.uri(urlFinal)
				.header(Constants.API_KEY, body.getApiKey())
				.header(Constants.TOKEN, body.getToken())
				.retrieve()
		        .onStatus(httpStatusCode -> httpStatusCode.value() == 422, (req, res) -> {
		        	String json = new String(res.getBody().readAllBytes());
		        	resultError.setGuid(mapper.readValue(json, new TypeReference<GuidB1Model>(){}).getGuid());
		        	resultError.setMessage(mapper.readValue(json, new TypeReference<GuidB1Model>(){}).getMessage());
		         })
		        .onStatus(httpStatusCode -> httpStatusCode.value() == 404, (req, res) -> {
		        	String json = new String(res.getBody().readAllBytes());
		        	resultError.setGuid(mapper.readValue(json, new TypeReference<GuidB1Model>(){}).getGuid());
		        	resultError.setMessage(mapper.readValue(json, new TypeReference<GuidB1Model>(){}).getMessage());
		         })
		        .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
		        	String json = new String(res.getBody().readAllBytes());
		        	log.error("{} - ERRO url {}: {}", NAME_CLASS, urlFinal, json);
		         })
		        .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
		        	String json = new String(res.getBody().readAllBytes());
		        	log.error("{} - ERRO url {}: {}", NAME_CLASS, urlFinal, json);
		         })	
				.toEntity(GuidB1Model.class);
		
		if (resultError.getGuid() != null)
			return resultError;
		
		return result.getBody();
	}

	@Override
	public VerifyB1Model getData(VerifyB1ModelInput body, String url) throws FileNameNotFoundException {

		RestClient restClient = RestClient.create();
		
		String urlFinal = url + "/data";
		
		VerifyB1Model result = restClient.get()
				  .uri(urlFinal)
				  .header(Constants.API_KEY, body.getApiKey())
				  .header(Constants.GUID, body.getGuid())
				  .retrieve()
			      .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
			    	  String json = new String(res.getBody().readAllBytes());
			    	  log.error("{} - ERRO: {}", NAME_CLASS, json);
			       })
			       .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
			        	String json = new String(res.getBody().readAllBytes());
			        	log.error("{} - ERRO url {}: {}", NAME_CLASS, urlFinal, json);
			         })	
				  .body(VerifyB1Model.class);
		
		if (result.getStatus().equals(Constants.PENDING)) {
			throw new FileNameNotFoundException(NAME_CLASS + " - Arquivo ainda não está disponível para o guid: " + body.getGuid());
		} else if (result.getStatus().equals(Constants.NO_DATA)) {
			log.error("{} - Não há dados disponíveis para o guid: {}. Status: {}", NAME_CLASS, body.getGuid(), result.getStatus());
		}
		
		return result;
		
	}

	@Override
	public void setReceived(SetReceivedB1Input body, String url) throws Exception {
		
		RestClient restClient = RestClient.create();
		
		try {
			restClient.post()
			.uri(url + "/received")
			.header(Constants.API_KEY, body.getApiKey())
			.header(Constants.GUID, body.getGuid())
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
			log.error("{} - Falha no received: ", NAME_CLASS, e.getMessage());
		}
		
	}

}
