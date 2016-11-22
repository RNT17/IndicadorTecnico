package business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import model.Cotacao;
import model.Cotacoes;
import model.StockTrend;

public class MediaMovelExponencial {
	
	/**
	 * Formula:
	 * Step 1: K = 2 / (periodo + 1)
	 * Step 2: k * (Fechamento - EMA dia anterior) + EMA dia anterior 
	 * @param cotacoes
	 * @param periodo
	 * @return Uma lista de StockTrend.
	 */
	public List<StockTrend> listaMediaMovelExponencial(Cotacoes cotacoes, int periodo){
		List<StockTrend> listStEMA = new ArrayList<>();
		List<Double> listaMediaMovelExponencial = new ArrayList<>();
		List<Cotacao> tempCotacoes = new ArrayList<>();

		BigDecimal k = 	new BigDecimal(2).divide(
						new BigDecimal(periodo).add(
						new BigDecimal(1))
						, 4, RoundingMode.HALF_EVEN
		);
		
		int index = 0;
		double trend = 0;
		
		for(Cotacao cotacao : cotacoes.getListaCotacoes()){
			if(tempCotacoes.size() < periodo-1)
				tempCotacoes.add(cotacao);
			else {				
				tempCotacoes.add(cotacao);				
				Cotacoes cotacoesPeriodo = new Cotacoes();
				cotacoesPeriodo.setListaCotacoes(tempCotacoes);
				
				if(listaMediaMovelExponencial.isEmpty()){
					trend = this.sma(cotacoesPeriodo, periodo);				
					listaMediaMovelExponencial.add(trend);
				}else{
					double EMAanterior = listaMediaMovelExponencial.get(++index - 1);					
					trend = k.doubleValue() * (cotacao.getClose() - EMAanterior) + EMAanterior;
					listaMediaMovelExponencial.add(trend);
				}
				
				StockTrend stEMA = new StockTrend(cotacao, trend);
				listStEMA.add(stEMA);
				tempCotacoes.remove(0);
			}
		}
		
		return listStEMA;
	}
	
	private double sma(Cotacoes cotacoes, int periodo){
		double totalFechamento = 0;		
		
		for(Cotacao cotacao : cotacoes.getListaCotacoes())
			totalFechamento += cotacao.getClose();		
		
		return totalFechamento / periodo;
	}
}
