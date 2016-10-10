package YahooFinance;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import business.CotacoesFactory;
import model.Cotacao;
import model.Cotacoes;

public class YahooFinance {
	
	public Cotacoes getCotacoes(String acao, Calendar dataInicio, Calendar dataFim) throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String strDataInicio = format.format(dataInicio.getTime());
		String strDataFim = format.format(dataFim.getTime());
		
		String URL_DIAGNOSTIC = "&diagnostics=false&env=store://datatables.org/alltableswithkeys";
		String URL_YAHOOAPI = "http://query.yahooapis.com/v1/public/yql?q=%20select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20in%20"
							+ "(%22" + acao + "%22)%20and%20" 
							+ "startDate%20=%20%22" + strDataInicio + "%22%20and%20" + "endDate%20=%20%22" + strDataFim + "%22%20" 
							+ URL_DIAGNOSTIC;		
		
		Cotacoes cotacoes = null;
		try {						
			
			String urlFinance = URL_YAHOOAPI;
			URL url = new URL(urlFinance);
			String textFromURL = extractTextFromURL(url);
			
			CotacoesFactory cotacoesNegocio = new CotacoesFactory();
			cotacoes = cotacoesNegocio.criarCotacoes(textFromURL);
			
		} catch (MalformedURLException e) {
			System.out.println("Erro ao criar URL. Formato invalido.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Erro ao acessar URL. Verifique sua conexão com a internet.");
			System.exit(1);
		}

		return cotacoes;
	}
		
	public void setPeriodo(Calendar dataInicio, int periodo){
		dataInicio.add(Calendar.MONTH, -1);
	}

	private String extractTextFromURL(URL url) throws MalformedURLException, IOException {

		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();

		ConvertInputStream convert = new ConvertInputStream();
		return convert.getStringFromInputStream(in);
	}

	public void printStockList(Cotacoes stockList) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		for (Cotacao st : stockList.getListaCotacoes()) 
		{
			String dtStr = format.format(st.getDate().getTime());
			System.out.println("Data: " + dtStr);
			//System.out.println("Open: " + st.getOpen());
			//System.out.println("Low: " + st.getLow());
			//System.out.println("High: " + st.getHigh());
			//System.out.println("Volume: " + st.getVolume());
			System.out.println("Close: " + st.getClose());
			//System.out.println("Fechamento Anterior: " + st.getAdj_close());
			//System.out.println("Variacao: " + st.getVariacao());
			//System.out.println("Ordem dia: " + st.getIndexDia());
			//System.out.println("TR: " + st.getTrueRange() + "\n");
		}
	}

}
