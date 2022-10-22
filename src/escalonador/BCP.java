package escalonador;

import java.util.*;

public class BCP implements Comparable<BCP>{
    
        Processo processo;
	private int prioridade;
        private int credito;
        private int tempoES;
	private int PC;
	private int regX;
	private int regY;
        private String estado;
	        
    public BCP (Processo processo, int prioridade){
            
        this.processo = processo;
        this.prioridade = prioridade;
        this.credito = prioridade;
        this.tempoES = 0;
        this.PC = 0;
        this.regX = 0;
        this.regY = 0;
        this.estado = "PRONTO";
            
    }  
    
    public void descontaCredito(){
        
        this.credito = this.credito - 1;
    }
    
   @Override
    public int compareTo(BCP bcp) {
            
	if (this.credito > bcp.credito) return -1;
	else if (this.credito < bcp.credito) return 1;
        else return 0;   
        
    }
        
    @Override
    public String toString(){
    
        return this.processo.getNome();
    }
    
    public int getPrioridade() {
        return prioridade;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getTempoES() {
        return tempoES;
    }

    public void setTempoES(int tempoES) {
        this.tempoES = tempoES;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getRegX() {
        return regX;
    }

    public void setRegX(int regX) {
        this.regX = regX;
    }

    public int getRegY() {
        return regY;
    }

    public void setRegY(int regY) {
        this.regY = regY;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
