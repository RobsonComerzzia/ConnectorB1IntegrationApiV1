package com.seidor.comerzzia.connector.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

	public static <T> Object jsonToObject(String json, T clazz) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Object obj = new Object();
		try {
			obj = mapper.readValue(json, new TypeReference<T>(){});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return obj;

	}

}
