package com.seidor.comerzzia.connector.util.json;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seidor.comerzzia.connector.api.v1.model.input.JsonTaxInput;
import com.seidor.comerzzia.connector.constants.Constants;

@Component
public class ReadJsonTaxImpl implements ReadJson<List<JsonTaxInput>> {

	@Override
	public List<JsonTaxInput> jsonToObject(String url) {
		
		List<JsonTaxInput> taxas = null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			String jason = IOUtils.toString(new URL(url), Charset.forName(Constants.UTF8));
			taxas = objectMapper.readValue(jason, new TypeReference<List<JsonTaxInput>>(){});
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return taxas;

	}

}
