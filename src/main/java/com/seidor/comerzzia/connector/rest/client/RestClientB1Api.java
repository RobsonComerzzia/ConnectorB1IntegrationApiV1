package com.seidor.comerzzia.connector.rest.client;

import com.seidor.comerzzia.connector.api.v1.model.input.GuidB1ModelInput;

public interface RestClientB1Api<R> {
	
	public R getData(GuidB1ModelInput body, String url) throws Exception;

}
