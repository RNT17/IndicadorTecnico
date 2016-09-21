package business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Cotacao;
import model.Cotacoes;
import model.StockTrend;

public class MediaMovelSimples {
	
	public List<StockTrend> listaMediaMovelSimples(Cotacoes cotacoes, int periodo){
		List<StockTrend> listStSMA = new ArrayList<>();
		List<Cotacao> tempCotacoes = new ArrayList<>();
		
		for(Cotacao cotacao : cotacoes.getListaCotacoes()){
			if(tempCotacoes.size() < periodo-1)
				tempCotacoes.add(cotacao);
			else {				
				tempCotacoes.add(cotacao);				
				Cotacoes cotacoesPeriodo = new Cotacoes();
				cotacoesPeriodo.setListaCotacoes(tempCotacoes);
				
				double trend = this.sma(cotacoesPeriodo, periodo);	
				
				StockTrend stSMA = new StockTrend(cotacao, trend);			
				listStSMA.add(stSMA);
				
				tempCotacoes.remove(0);
			}
		}
		
		return listStSMA;
	}
	
	private double sma(Cotacoes cotacoes, int periodo){
		double totalFechamento = 0;
		
		for(Cotacao cotacao : cotacoes.getListaCotacoes())
			totalFechamento += cotacao.getClose();

		return totalFechamento / periodo;
	}
	
	public void print(double mediaCotacao, Cotacao cotacao){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dtStr = format.format(cotacao.getDate().getTime());
		System.out.println("Dia: "+dtStr+ ", sma: "+mediaCotacao);
	}
}
