package escalonador;

import java.io.*;
import java.util.*;


public class Escalonador {

    private static int quantum; 
    private static int interrupcoes = 0; 
    private static int instrucoes = 0; 
    private static double resultadoInterrupcoes = 0;
    private static double resultadoInstrucoes = 0;
    private final static int maxProcessos = 10; 
    private static int[] prioridade = new int[21];
        
    public static void main(String[] args) throws IOException {
        
        
        BufferedReader qt = new BufferedReader(new FileReader(new File("src/programas/quantum.txt")));
	quantum = Integer.parseInt(qt.readLine());
	qt.close();
        
        BufferedReader br = new BufferedReader(new FileReader(new File("src/programas/prioridades.txt")));
	for (int i = 0; i < maxProcessos; i++) {
            prioridade[i] = Integer.parseInt(br.readLine());
		
        }
        
        br.close();
        
	
        for (int j = 1; j <= 10; j++) {
          
            FileReader arquivo;
            
            if(j < 10) {
                arquivo = new FileReader("src/programas/0" + j + ".txt");
                
            } else {
                arquivo = new FileReader("src/programas/" + j + ".txt");
            }
            
            BufferedReader lerArq = new BufferedReader(arquivo);
            String nome = lerArq.readLine();
            String [] programa = new String [21];
            String line ="";
            int cont = 0;
            while (!("SAIDA".equals(line)) && cont < 21){
                
                line = lerArq.readLine();
                programa[cont] = line;
                cont++;
            }
            
            
            lerArq.close();
            Processo processo = new Processo (programa, nome);
            BCP bcp = new BCP(processo, prioridade[j-1]);
            TabelaProcessos.tabelaProcessos.addFirst(bcp);
            ListaProntos.listaProntos.addLast(bcp);
        }
        
               
        
        if (quantum < 10 && quantum > 0) {
            
            System.setOut(new PrintStream(new FileOutputStream("log0" + quantum + ".txt")));
                
        } else {
            
            System.setOut(new PrintStream( new FileOutputStream("log"+quantum + ".txt")));
            
        }
        
        Collections.sort(ListaProntos.listaProntos);
            
        for (BCP bcps : ListaProntos.listaProntos) {
            
            System.out.println("Carregando " + bcps.processo.getNome());
        }
            
            
        while (TabelaProcessos.tabelaProcessos.size() > 0){
                
                
            if (ListaProntos.listaProntos.size() > 0) {
                    
                int count = 1;
                    
                ListaProntos.ordenarProntos();
                                        
                    
                BCP processoAtual = ListaProntos.listaProntosOrdenados.poll();
                ListaProntos.listaProntos.remove(processoAtual);
                    
                if (processoAtual == null) continue;
                    
                processoAtual.setEstado("EXECUTANDO");
                    
                System.out.println("Executando " + processoAtual.processo.getNome() + " Créditos: " + processoAtual.getCredito());
                processoAtual.descontaCredito();
                    
                while(count <= quantum){
                        
                    String proxInstrucao = processoAtual.processo.getInstrucao(processoAtual.getPC());
                        
                    if ("COM".equals(proxInstrucao)){
                            
                        processoAtual.setPC(processoAtual.getPC()+ 1);
                    }
                        
                    else if (proxInstrucao.contains("X=")){
                            
                        processoAtual.setRegX(Integer.parseInt(proxInstrucao.substring(2)));
                            
                        processoAtual.setPC(processoAtual.getPC()+ 1);
                    }
                    
                    else if (proxInstrucao.contains("Y=")){
                        
                        processoAtual.setRegY(Integer.parseInt(proxInstrucao.substring(2)));
                            
                        processoAtual.setPC(processoAtual.getPC()+ 1);
                    }
                           
                    else if ("E/S".equals(proxInstrucao)){
                            
                        System.out.println("E/S iniciada em " + processoAtual.processo.getNome());
                            
                        processoAtual.setEstado("BLOQUEADO");
                        processoAtual.setTempoES(3);
                        ListaBloqueados.listaBloqueados.add(processoAtual);
                        ListaProntos.listaProntos.remove(processoAtual);
                            
                        System.out.println("Interrompendo "	+ processoAtual.processo.getNome() + " após " + count + " instruções");
                            
                        processoAtual.setPC(processoAtual.getPC()+ 1);
                        break;
                    }
                        
                    else if ("SAIDA".equals(proxInstrucao)){
                            
                        System.out.println(processoAtual.processo.getNome() + " terminado. X=" + processoAtual.getRegX() + ". Y=" + processoAtual.getRegY());
                            
                        processoAtual.setEstado("TERMINADO");
                            
                        ListaBloqueados.listaBloqueados.remove(processoAtual);
                        ListaProntos.listaProntos.remove(processoAtual);
                        TabelaProcessos.tabelaProcessos.remove(processoAtual);
                            
                        break;
                    }
                        
                    count++;

                }
                    
                if(processoAtual.getEstado().equals("EXECUTANDO")) {
                        
                    count--;
                    processoAtual.setEstado("PRONTO");
                        
                    if(ListaProntos.listaProntos.size() > 0){
                        
                        BCP aux = ListaProntos.listaProntosOrdenados.poll();
                        if (aux.getCredito() == processoAtual.getCredito()){
                        
                            ListaProntos.listaProntos.addFirst(aux);
                            ListaProntos.listaProntos.addFirst(processoAtual);
                            
                        }
                            
                        else ListaProntos.listaProntos.addLast(processoAtual);
                    }
                        
                    System.out.println("Interrompendo "+ processoAtual.processo.getNome() + " após " + count+ " instruções");
                }
                    
                interrupcoes++;
                instrucoes = instrucoes + count;
            }
                
            ListaBloqueados.diminuiTempoEspera();
            ListaBloqueados.fimDeES();
            TabelaProcessos.tabelaZerada();
                    
            
        }
            
        resultadoInterrupcoes = (double) interrupcoes/maxProcessos;
        resultadoInstrucoes = (double) instrucoes/interrupcoes;
        
        System.out.println("Média de interrupcoes = " + resultadoInterrupcoes);
        System.out.println("Média de instrucoes = " + resultadoInstrucoes);
        
    }
    
}
