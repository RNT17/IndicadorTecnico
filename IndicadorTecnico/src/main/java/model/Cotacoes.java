package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "results")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cotacoes {
   
    @XmlElement(name = "quote")
    private List<Cotacao> listaCotacao = null;
    
    public Cotacoes () { }
    
    public Cotacoes (List<Cotacao> listaCotacoes) {
    	this.listaCotacao = listaCotacoes;
    }
    
    public List<Cotacao> getListaCotacoes() {
        return this.listaCotacao;
    }
 
    public void setListaCotacoes(List<Cotacao> listaCotacao) {
        this.listaCotacao = listaCotacao;
    }
    
    public Cotacao getCotacao(int posicao){
    	return this.listaCotacao.get(posicao);
    }
    
    public int getTotal(){
    	return this.listaCotacao.size();
    }
    
    public double firstAtr(){
    	double atr = 0;
    	for(Cotacao cotacao : this.listaCotacao)
    		atr += cotacao.getTrueRange();
    	
    	if(this.getTotal() > 0)
    		return  atr / this.getTotal();
    	else 
    		return atr / 1;
    }
    
    public double firstAtr(int periodo) {
		double atr = 0;
		for(Cotacao cotacao : this.listaCotacao) {
			atr += cotacao.getTrueRange();
		}		
		return atr / periodo;
	}
}
