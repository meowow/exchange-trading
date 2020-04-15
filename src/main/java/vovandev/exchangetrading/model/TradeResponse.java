package vovandev.exchangetrading.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TradeResponse {

	@JsonProperty("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"TradeResponse{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}