package com.seidor.comerzzia.connector.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.seidor.comerzzia.constant.Constants;

public abstract class Utils {
	
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if(pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("Page size inválido: " + pageSize);
        }
        
        int fromIndex = (page - 1) * pageSize;
        if(sourceList == null || sourceList.size() <= fromIndex){
            return Collections.emptyList();
        }
        
        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }
    
	public static <T> Integer calcPages(List<T> sourceList, Integer pageSize) {
		
		Long pages = ((long) sourceList.size() / pageSize);
		Integer resto = (sourceList.size() % pageSize);
		pages = resto > 0 ? Math.round(pages) + 1 : pages;
		
		if(pages == 0)
			pages = Long.parseLong("1");
		
		Integer totalPages = Math.round(pages);
		
		return totalPages;
	}
	
	public static String transformURL(String url) {
		
		String urlFinal = null;
		
		try {
			String hostname = InetAddress.getLocalHost().getHostName() + ":443";
			urlFinal = Constants.PROTOCOL_HTTP + hostname + url;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return urlFinal;
		
	}

}
