package com.seidor.comerzzia.connector.util;

public abstract class StringUtils {
	
	public static String tratarString(String item) {
		
		if (item == null)
			return null;
		
		return item.replace(".", "")
				.replace("-", "")
				.replace("/", "");
		
	}

}
