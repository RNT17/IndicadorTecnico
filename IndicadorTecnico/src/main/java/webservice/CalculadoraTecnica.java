package webservice;
import java.util.Calendar;
import java.util.List;

import javax.jws.WebService;

import model.StockTrend;

@WebService(endpointInterface="webservice.CalculadoraTecnicaInterface",
portName="CalculadoraTecnicaSoap", 
serviceName="CalculadoraTecnicaService")
public class CalculadoraTecnica implements CalculadoraTecnicaInterface {
	
	CalculadoraServiceImpl calculadoraService = new CalculadoraServiceImpl();
	
	@Override
	public List<StockTrend> calcularMediaMovel(String simbol, String dtInicio, String dtFim, int periodo){		
		return calculadoraService.calcularMediaMovel(simbol, dtInicio, dtFim, periodo);
	}
	
	@Override
	public List<StockTrend> calcularMediaExponencial(String simbol, Calendar dtInicio, Calendar dtFim, int periodo) {
		return calculadoraService.calcularMediaExponencial(simbol, dtInicio, dtFim, periodo);
	}
	
	@Override
	public List<StockTrend> calcularIndiceForcaRelativa(String simbol, Calendar dtInicio, Calendar dtFim, int periodo) {
		return calculadoraService.calcularIndiceForcaRelativa(simbol, dtInicio, dtFim, periodo);
	}
	
	@Override
	public List<StockTrend> calcularAverageTrueRange(String simbol, Calendar dtInicio, Calendar dtFim, int periodo) {
		return calculadoraService.calcularAverageTrueRange(simbol, dtInicio, dtFim, periodo);
	}
}
