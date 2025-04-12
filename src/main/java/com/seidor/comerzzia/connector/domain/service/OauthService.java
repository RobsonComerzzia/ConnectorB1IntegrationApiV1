package com.seidor.comerzzia.connector.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.connector.api.v1.model.input.AuthenticationInput;
import com.seidor.comerzzia.connector.rest.client.RestClientToken;

@Service
public abstract class OauthService {
	
	@Value("${oauth.master.url}")
	private String urlToken;
	
	@Value("${master.username}")
	private String userNameMaster;
	
	@Value("${master.password}")
	private String passwordMaster;

	@Autowired
	private RestClientToken<AuthenticationInput> restClientToken;
	
	protected String getToken() {
		
		AuthenticationInput ouathInput = AuthenticationInput.builder()
				.username(userNameMaster)
				.password(passwordMaster)
				.build();
		
		return restClientToken.execute(ouathInput, urlToken).getAccess_token();
		
	}
	
	

}
