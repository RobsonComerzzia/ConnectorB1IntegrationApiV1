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
import com.seidor.comerzzia.connector.api.v1.model.input.JsonItemPriceListInput;
import com.seidor.comerzzia.connector.constants.Constants;


@Component
public class ReadJsonItemPriceIListmpl implements ReadJson<List<JsonItemPriceListInput>> {

	@Override
	public List<JsonItemPriceListInput> jsonToObject(String url) {
		
		List<JsonItemPriceListInput> items = null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			String jason = IOUtils.toString(new URL(url), Charset.forName(Constants.UTF8));
			items = objectMapper.readValue(jason, new TypeReference<List<JsonItemPriceListInput>>(){});
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return items;

	}

}
