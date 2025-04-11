package com.seidor.comerzzia.connector;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import com.seidor.comerzzia.connector.core.io.Base64ProtocolResolver;

@EnableRetry
@SpringBootApplication
public class ConnectorB1IntegrationApiV1Application {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		var app = new SpringApplication(ConnectorB1IntegrationApiV1Application.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}
