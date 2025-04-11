package com.seidor.comerzzia.connector.rest.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.connector.api.v1.model.input.ArticulosInput;
import com.seidor.comerzzia.connector.util.Utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RestClientMasterArticulosImpl implements RestClientMaster<ArticulosInput> {

	private static String NAME_CLASS = "[RestClientMasterImpl]";
	
	@Override
	public void execute(ArticulosInput body, String url, String token) {
		
		RestClient restClient = RestClient.create();
		String urlFinal = null;
		
		urlFinal = Utils.transformURL(url);
		
		try {
			restClient.post()
			.uri(urlFinal)
			.body(body)
			//.header(Constants.TOKEN, token)
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
			log.error("{} - Falha ao atualizar dados de Clientes no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
	}


}
