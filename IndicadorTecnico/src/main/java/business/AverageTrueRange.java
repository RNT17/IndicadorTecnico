package business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Cotacao;
import model.Cotacoes;
import model.StockTrend;

public class AverageTrueRange {
	
	public List<StockTrend> listaAverageTrueRange(Cotacoes cotacoes, int periodo){
		List<StockTrend> listStATR = new ArrayList<>();
		List<Double> listaAverageTrueRange = new ArrayList<>();
		List<Cotacao> tempCotacoes = new ArrayList<>();
		StockTrend stATR = null;
		
		for(Cotacao cotacao : cotacoes.getListaCotacoes()){
			if (tempCotacoes.size() < periodo) {				
				tempCotacoes.add(cotacao);
			} else {
				
				if (listaAverageTrueRange.isEmpty()) {
					Cotacoes cotacoesPeriodo = new Cotacoes(tempCotacoes);
					listaAverageTrueRange.add(cotacoesPeriodo.firstAtr(periodo));
					
					stATR = new StockTrend(cotacao, cotacoesPeriodo.firstAtr(periodo));
					listStATR.add(stATR);
				}
				
				tempCotacoes.add(cotacao);				
				Cotacoes cotacoesPeriodo = new Cotacoes(tempCotacoes);
				
				double atrAnterior = listaAverageTrueRange.get(listaAverageTrueRange.size() - 1);
				double trend = this.calculaAverageTrueRange(cotacoesPeriodo, atrAnterior, periodo);
				listaAverageTrueRange.add(trend);

				stATR = new StockTrend(cotacao, trend);
				listStATR.add(stATR);
				
				tempCotacoes.remove(0);
			}
		}
		
		return listStATR;
	}
	
	private double calculaAverageTrueRange(Cotacoes cotacoes, double atrAnterior, int periodo) {
		
		double trAtual = cotacoes.getCotacao(periodo).getTrueRange();
		
		double atr = ((atrAnterior * (periodo-1)) + trAtual) / periodo;
		
		return atr;
	}
	
	@SuppressWarnings("unused")
	private void print(double mediaCotacao, Cotacao cotacao){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dtStr = format.format(cotacao.getDate().getTime());
		System.out.println("Dia: "+dtStr+ ", ATR: "+mediaCotacao);
	}
}
