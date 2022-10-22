/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escalonador;

/**
 *
 * @author caiokatsumi
 */
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
