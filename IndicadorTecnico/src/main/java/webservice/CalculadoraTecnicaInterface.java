package webservice;
import java.util.Calendar;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import model.StockTrend;

@WebService(name="CalculadoraTecnica")
public interface CalculadoraTecnicaInterface {
	
	@WebMethod(action = "calcula_mms", operationName = "CalculaMMS")
	public List<StockTrend> calcularMediaMovel(String simbol, Calendar dtInicio, Calendar dtFim, int periodo);
	
	@WebMethod(action = "calcula_mme", operationName="CalculaMME")
	public List<StockTrend> calcularMediaExponencial(String simbol, Calendar dtInicio, Calendar dtFim, int periodo);

	@WebMethod(action = "calcula_IFR", operationName="CalculaIFR")
	public List<StockTrend> calcularIndiceForcaRelativa(String simbol, Calendar dtInicio, Calendar dtFim, int periodo);

	@WebMethod(action = "calcula_ATR", operationName="CalculaATR")
	public List<StockTrend> calcularAverageTrueRange(String simbol, Calendar dtInicio, Calendar dtFim, int periodo);
}
