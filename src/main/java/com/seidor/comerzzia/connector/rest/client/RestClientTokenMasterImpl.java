package com.seidor.comerzzia.connector.rest.client;

import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.seidor.comerzzia.connector.api.v1.model.ResponseTokenModel;
import com.seidor.comerzzia.connector.api.v1.model.input.AuthenticationBodyInput;
import com.seidor.comerzzia.connector.api.v1.model.input.AuthenticationInput;
import com.seidor.comerzzia.connector.constants.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestClientTokenMasterImpl implements RestClientToken<AuthenticationInput> {

	private static String NAME_CLASS = "[RestClientTokenMasterImpl]";
	
	@Override
	public ResponseTokenModel execute(AuthenticationInput body, String url) {
		
		log.info("{} - Obtendo Token da Master API", NAME_CLASS);
		
		AuthenticationBodyInput body2 = AuthenticationBodyInput.builder()
				.grant_type(body.getGranType())
				.scope(body.getScope())
				.build();
		
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(Constants.GRAN_TYPE, body2.getGrant_type());
        formData.add(Constants.SCOPE, body2.getScope());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Authorization", RestClientTokenMasterImpl.getBasicAuthenticationHeader(body.getUsername(), body.getPassword()));
        
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
        
        RestTemplate restTemplate = new RestTemplate();
   
        ResponseEntity<ResponseTokenModel> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseTokenModel.class);
		
		return response.getBody();
		
	}
	
	private static final String getBasicAuthenticationHeader(String username, String password) {
	    String valueToEncode = username + ":" + password;
	    return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
	}
	

}
