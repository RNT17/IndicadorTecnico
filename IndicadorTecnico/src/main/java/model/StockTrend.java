package model;

public class StockTrend {
	
	Cotacao quotation;
	double trendValue;
	
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
	
}
