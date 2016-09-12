package teste;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import model.Cotacao;
import model.StockTrend;
import webservice.CalculadoraServiceImpl;

public class Tester {

	public static void main(String[] args) throws Exception {		
		
		System.out.println("Loading...");
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		dataInicio.set(2016, Calendar.AUGUST, 01);
		dataFim.set(2016, Calendar.AUGUST, 10);
		
		CalculadoraServiceImpl calculadoraTecnica = new CalculadoraServiceImpl();		
		List<StockTrend> stMMS = calculadoraTecnica.calcularMediaMovel("GOOGL", "2016-08-01", "2016-08-10", 14);
		/*List<StockTrend> stEMA = calculadoraTecnica.calcularMediaExponencial("GOOGL", dataInicio, dataFim, 14);		
		List<StockTrend> stRSI = calculadoraTecnica.calcularIndiceForcaRelativa("GOOGL", dataInicio, dataFim, 14);		
		List<StockTrend> stATR = calculadoraTecnica.calcularAverageTrueRange("GOOGL", dataInicio, dataFim, 14);*/
		
		printStockTrend(stMMS);
	}
	
	public static void printStockTrend(List<StockTrend> listSt) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		for (StockTrend st : listSt){
			Cotacao quotation = st.getQuotation();
			String dtStr = format.format(quotation.getDate().getTime());
			System.out.println("Dia: "+dtStr+ ", trend Value: "+ st.getTrendValue());
		}		
	}
}
