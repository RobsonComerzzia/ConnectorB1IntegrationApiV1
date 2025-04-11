package com.seidor.comerzzia.connector.api.v1.model.input;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemsGravarInput {
	
	private List<JsonItemInput> items;
	
	private List<JsonItemPriceInput> itemsPrice;
	
	private List<JsonItemPriceListInput> itemsPriceList;

}
