package com.seidor.comerzzia.connector.rest.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.connector.domain.model.Articulo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class RestClientMasterReturnArticulosFindImpl implements RestClientMasterReturn<List<Articulo>> {

	private static String NAME_CLASS = "[RestClientMasterArticulosFindImpl]";
	
	@Override
	public ResponseEntity<List<Articulo>> execute(List<String> param, String url, String token) {
		
		ResponseEntity<List<Articulo>> articulos = null;
		
		RestClient restClient = RestClient.builder()
				.baseUrl(url)
                .defaultHeader(org.springframework.http.HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.build();
		
		try {
			articulos = restClient.get()
							.uri(uriBuilder -> uriBuilder
								.queryParam("codarts", param)
								.build())
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
							.toEntity(new ParameterizedTypeReference<List<Articulo>>() {});		
		} catch (Exception e) {
			log.error("{} - Falha ao recuperar dados de Produto no Comerzzia: ", NAME_CLASS, e.getLocalizedMessage());
		}
		return articulos;
	}
	
}
