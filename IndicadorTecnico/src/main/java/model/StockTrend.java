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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.data = sdf.format(this.quotation.getDate().getTime());
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
	
	public void setDate(String date){
		this.data = date;
	}
	
	public String getDate(){
		return data;
	}
}
