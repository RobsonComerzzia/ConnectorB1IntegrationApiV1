package com.seidor.comerzzia.connector.rest.client;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import com.seidor.comerzzia.connector.api.v1.model.input.SetReceivedB1Input;
import com.seidor.comerzzia.connector.exception.FileNameNotFoundException;

public interface RestClientB1Json<InputGuid, GuidModel, VerifyInput, VerifyModel> {
	
	public GuidModel getGuid(InputGuid body, String url) throws FileNameNotFoundException;
	
    @Retryable(retryFor = FileNameNotFoundException.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
	public VerifyModel getData(VerifyInput body, String url) throws FileNameNotFoundException;
    
    public void setReceived(SetReceivedB1Input body, String url) throws Exception;

}
