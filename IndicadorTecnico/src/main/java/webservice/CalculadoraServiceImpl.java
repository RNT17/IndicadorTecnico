package webservice;

import java.util.Calendar;
import java.util.List;

import YahooFinance.YahooFinance;

import business.AverageTrueRange;
import business.IndiceForcaRelativa;
import business.MediaMovelExponencial;
import business.MediaMovelSimples;

import model.Cotacoes;
import model.StockTrend;;

public class CalculadoraServiceImpl {
	
	public List<StockTrend> calcularMediaMovel(String simbol, Calendar dtInicio, Calendar dtFim, int periodo) {
		List<StockTrend> stockTrend = null;		

		try {
			YahooFinance yahooFinance = new YahooFinance();				
			yahooFinance.setPeriodo(dtInicio, periodo);			
			Cotacoes cotacoes = yahooFinance.getCotacoes(simbol, dtInicio, dtFim);

			MediaMovelSimples sma = new MediaMovelSimples();
			stockTrend = sma.listaMediaMovelSimples(cotacoes, periodo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return stockTrend;
	}
	
	public List<StockTrend> calcularMediaExponencial(String simbol, Calendar dtInicio, Calendar dtFim, int periodo) {
		List<StockTrend> stockTrend = null;

		try {
			YahooFinance yahooFinance = new YahooFinance();
			yahooFinance.setPeriodo(dtInicio, periodo);
			Cotacoes cotacoes = yahooFinance.getCotacoes(simbol, dtInicio, dtFim);

			MediaMovelExponencial ema = new MediaMovelExponencial();
			stockTrend = ema.listaMediaMovelExponencial(cotacoes, periodo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return stockTrend;
	}
	
	public List<StockTrend> calcularIndiceForcaRelativa(String simbol, Calendar dtInicio, Calendar dtFim, int periodo) {
		List<StockTrend> stockTrend = null;

		try {
			YahooFinance yahooFinance = new YahooFinance();
			dtInicio.add(Calendar.DAY_OF_MONTH, -22);
			Cotacoes cotacoes = yahooFinance.getCotacoes(simbol, dtInicio, dtFim);

			IndiceForcaRelativa rsi = new IndiceForcaRelativa();
			stockTrend = rsi.listaIndiceForcaRelativa(cotacoes, periodo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stockTrend;
	}
	
	public List<StockTrend> calcularAverageTrueRange(String simbol, Calendar dtInicio, Calendar dtFim, int periodo) {
		List<StockTrend> stockTrend = null;

		try {
			YahooFinance yahooFinance = new YahooFinance();
			yahooFinance.setPeriodo(dtInicio, periodo);
			Cotacoes cotacoes = yahooFinance.getCotacoes(simbol, dtInicio, dtFim);

			AverageTrueRange atr = new AverageTrueRange();
			stockTrend = atr.listaAverageTrueRange(cotacoes, periodo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stockTrend;
	}
	
}
