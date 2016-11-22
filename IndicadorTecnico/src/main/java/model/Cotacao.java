package model;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "quote")
@XmlAccessorType (XmlAccessType.FIELD)
public class Cotacao implements Comparable<Cotacao>{

	Calendar date;
	private double open;
	private double high;
	private double low;
	private double close;
	private double volume;
	private double adj_close;
	private double variacao;
	private double trueRange;


	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getAdj_close() {
		return adj_close;
	}

	public void setAdj_close(double adj_close) {
		this.adj_close = adj_close;
	}
	
	public double getVariacao(){
		return variacao;
	}
	
	public void setVariacao(double variacao){
		this.variacao = variacao;
	}
	
	public double getTrueRange(){
		
		double highLow = this.high - this.low;
		double highCloseP = this.high - this.getAdj_close();
		double lowCloseP = this.low - this.getAdj_close();
		
		if(this.getAdj_close() == 0)
			trueRange = highLow;		
		else
			trueRange = Math.max(Math.abs(highLow), Math.max(Math.abs(highCloseP), Math.abs(lowCloseP)));
		
		return trueRange;
	}

	@Override
	public int compareTo(Cotacao o) {
		return getDate().compareTo(o.getDate());
	}
}
