package com.seidor.comerzzia.connector.rest.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.connector.api.v1.model.input.GuidB1ModelInput;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonCategoryInput;
import com.seidor.comerzzia.connector.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RestClientB1ApiImpl implements RestClientB1Api<JsonCategoryInput> {

	private static String NAME_CLASS = "[RestClientB1ApiImpl]";
	
	@Override
	public JsonCategoryInput getData(GuidB1ModelInput body, String url) throws Exception {
		
		ResponseEntity<JsonCategoryInput> categories = null;
		
		RestClient restClient = RestClient.builder()
				.baseUrl(url)
				.build();
		
		try {
			categories = restClient.get()
							.uri(uriBuilder -> uriBuilder
								.build())
							.header(Constants.API_KEY, body.getApiKey())
							.header(Constants.TOKEN, body.getToken())
							.retrieve()
					        .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
					        	String json = new String(res.getBody().readAllBytes());
					        	log.error("{} - Erro {} : {}", res.getStatusCode(), NAME_CLASS, json);
					         })
					        .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
					        	String json = new String(res.getBody().readAllBytes());
					        	log.error("{} - ERRO {}: {}", res.getStatusCode(), NAME_CLASS, json);
					         })	
							.toEntity(JsonCategoryInput.class);		
		} catch (Exception e) {
			log.error("{} - Falha ao recuperar dados de categoria: ", NAME_CLASS, e.getLocalizedMessage());
		}
		return categories.getBody();
		
	}

}
