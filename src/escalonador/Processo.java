package escalonador;


public class Processo {
    
    private String[] instrucoes;
    private String nome;
    
    public Processo(String [] instrucoes, String nome){
        
        this.instrucoes = instrucoes;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstrucao(int i) {
        return instrucoes[i];
    }
    
}
