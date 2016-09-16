package model;

import java.text.SimpleDateFormat;

public class StockTrend {
	
	Cotacao quotation;
	double trendValue;
	String data;
	
	public StockTrend(){}
	
	public StockTrend(Cotacao cotacao, double trendValue){
		this.quotation = cotacao;
		this.trendValue = trendValue;
	}
	
	public Cotacao getQuotation() {
		return quotation;
	}
	
	public void setQuotation(Cotacao quotation) {
		this.quotation = quotation;
	}
	
	public double getTrendValue() {
		return trendValue;
	}
	
	public void setTrendValue(double trendValue) {
		this.trendValue = trendValue;
	}
	
	public String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		return data = sdf.format(this.quotation.getDate().getTime());
	}
}
