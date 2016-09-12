package business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Cotacao;
import model.Cotacoes;
import model.StockTrend;

public class IndiceForcaRelativa {

	public List<StockTrend> listaIndiceForcaRelativa(Cotacoes cotacoes, int periodo){
		
		List<StockTrend> listStRSI = new ArrayList<>();
		List<Double> listaIndiceForcaRelativa = new ArrayList<>();
		List<Cotacao> tempCotacoes = new ArrayList<>();		
		double mediaGanho = 0;
		double mediaPerda = 0;		
		double trend = 0;
		StockTrend stRSI = null;
		
		for(Cotacao cotacao : cotacoes.getListaCotacoes()){
			if(tempCotacoes.size() < periodo)
				tempCotacoes.add(cotacao);
			else {				
				tempCotacoes.add(cotacao);				
				Cotacoes cotacoesPeriodo = new Cotacoes();
				cotacoesPeriodo.setListaCotacoes(tempCotacoes);
				
				if(listaIndiceForcaRelativa.isEmpty()){
					trend = this.ifr(cotacoesPeriodo, periodo);
					
					mediaGanho = this.mediaGanho(cotacoesPeriodo, periodo);
					mediaPerda = this.mediaPerda(cotacoesPeriodo, periodo);
				}else{
					
					if(cotacao.getVariacao() > 0){
						mediaGanho = ((mediaGanho * (periodo-1)) + cotacao.getVariacao()) / periodo;
						mediaPerda = ((mediaPerda * (periodo-1)) + 0) / periodo;
					} else {
						mediaGanho = ((mediaGanho * (periodo-1)) + 0) / periodo;
						mediaPerda = ((mediaPerda * (periodo-1)) + Math.abs(cotacao.getVariacao())) / periodo;
					}
										
					if(mediaPerda == 0){
						listaIndiceForcaRelativa.add(100.00);
						
						stRSI = new StockTrend(cotacao, trend);
						listStRSI.add(stRSI);
						
					}else if(mediaGanho == 0){
						listaIndiceForcaRelativa.add(0.00);
						
						stRSI = new StockTrend(cotacao, trend);
						listStRSI.add(stRSI);
					}else{					
						double forcaRelativa = mediaGanho / mediaPerda;
						trend = 100 - (100 / (1 + forcaRelativa));
						listaIndiceForcaRelativa.add(trend);
						
						stRSI = new StockTrend(cotacao, trend);
						listStRSI.add(stRSI);
					}
				}
				
				listaIndiceForcaRelativa.add(trend);
				tempCotacoes.remove(0);
			}
		}
		
		return listStRSI;
	}
		
	private double ifr(Cotacoes cotacoes, int periodo) {
		double ifr = 0;
		
		double mediaGanhos = this.mediaGanho(cotacoes, periodo);
		double mediaPerdas = this.mediaPerda(cotacoes, periodo);

		double forcaRelativa = mediaGanhos / mediaPerdas;
		
		ifr = 100 - (100 / (1 + forcaRelativa));
		return ifr;
	}

	private double mediaGanho(Cotacoes cotacoes, int periodo) {
		double mediaGanho = 0;

		for (Cotacao cotacao : cotacoes.getListaCotacoes()) {
			if (cotacao.getVariacao() > 0){
				mediaGanho += cotacao.getVariacao();
			}
		}

		mediaGanho = mediaGanho / periodo;

		return mediaGanho;
	}

	private double mediaPerda(Cotacoes cotacoes, int periodo) {
		double mediaPerda = 0;

		for (Cotacao cotacao : cotacoes.getListaCotacoes()) {
			if (cotacao.getVariacao() < 0){
				mediaPerda += Math.abs(cotacao.getVariacao());
			}
		}

		mediaPerda = mediaPerda / periodo;

		return mediaPerda;
	}

	@SuppressWarnings("unused")
	private void print(double mediaCotacao, Cotacao cotacao){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dtStr = format.format(cotacao.getDate().getTime());
		System.out.println("Dia: "+dtStr+ ", IFR: "+mediaCotacao);
	}
}
