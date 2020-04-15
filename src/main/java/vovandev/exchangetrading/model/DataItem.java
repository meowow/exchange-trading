package vovandev.exchangetrading.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class DataItem{

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("trades")
	private int trades;

	@JsonProperty("homeNotional")
	private int homeNotional;

	@JsonProperty("volume")
	private int volume;

	@JsonProperty("foreignNotional")
	private int foreignNotional;

	@JsonProperty("high")
	private BigDecimal high;

	@JsonProperty("low")
	private BigDecimal low;

	@JsonProperty("lastSize")
	private int lastSize;

	@JsonProperty("close")
	private BigDecimal close;

	@JsonProperty("open")
	private BigDecimal open;

	@JsonProperty("timestamp")
	private String timestamp;

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setTrades(int trades){
		this.trades = trades;
	}

	public int getTrades(){
		return trades;
	}

	public void setHomeNotional(int homeNotional){
		this.homeNotional = homeNotional;
	}

	public int getHomeNotional(){
		return homeNotional;
	}

	public void setVolume(int volume){
		this.volume = volume;
	}

	public int getVolume(){
		return volume;
	}

	public void setForeignNotional(int foreignNotional){
		this.foreignNotional = foreignNotional;
	}

	public int getForeignNotional(){
		return foreignNotional;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public void setLastSize(int lastSize){
		this.lastSize = lastSize;
	}

	public int getLastSize(){
		return lastSize;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"symbol = '" + symbol + '\'' +
			",trades = '" + trades + '\'' + 
			",homeNotional = '" + homeNotional + '\'' + 
			",volume = '" + volume + '\'' + 
			",foreignNotional = '" + foreignNotional + '\'' + 
			",high = '" + high + '\'' + 
			",low = '" + low + '\'' + 
			",lastSize = '" + lastSize + '\'' + 
			",close = '" + close + '\'' +
			",open = '" + open + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}