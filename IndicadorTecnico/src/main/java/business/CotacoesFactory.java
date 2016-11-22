package business;

import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import YahooFinance.XmlConverter;
import model.Cotacao;
import model.Cotacoes;

public class CotacoesFactory {

	public Cotacoes criarCotacoes(String textFromURL) throws JAXBException, Exception {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Cotacoes.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		XmlConverter xmlConverter = new XmlConverter();
		
		Cotacoes cotacoes = (Cotacoes) jaxbUnmarshaller.unmarshal(xmlConverter.Converter(textFromURL));
			
		setFechamentoAnterior(cotacoes);
		setTaxaDeVariacao(cotacoes);
		
		/*Ordenar da menor data para a maior data do periodo de cotações*/
		Collections.sort(cotacoes.getListaCotacoes());
		
		return cotacoes;
	}
	
	private void setFechamentoAnterior(Cotacoes cotacoes) {

		int index = 0;
		for (Cotacao cotacao : cotacoes.getListaCotacoes()) {
			cotacao.setAdj_close(0);
			if (index < cotacoes.getTotal() - 1) {
				Cotacao cotacaoAnterior = cotacoes.getCotacao(index + 1);
				cotacao.setAdj_close(cotacaoAnterior.getClose());
			}
			index++;
		}
	}
	
	private void setTaxaDeVariacao(Cotacoes cotacoes){
		int index = 0;
		for (Cotacao cotacao : cotacoes.getListaCotacoes()) {
			cotacao.setVariacao(0);
			if(index < cotacoes.getTotal() - 1){
				double variacao = 0;
				variacao = (cotacao.getClose() - cotacao.getAdj_close());				
				cotacao.setVariacao(variacao);			
			}
			index++;
		}
	}
}



