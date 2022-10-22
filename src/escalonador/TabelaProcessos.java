package escalonador;

import static escalonador.ListaProntos.listaProntos;
import java.util.LinkedList;

public class TabelaProcessos {
    
    protected static LinkedList<BCP> tabelaProcessos = new LinkedList<>();
    
    
    public static void tabelaZerada() {
       
		for (BCP aux : tabelaProcessos) {
                    
			if (aux.getCredito() != 0) return; 
		}
                
		for (BCP aux : tabelaProcessos) {
                    
			aux.setCredito(aux.getPrioridade());
		}		
    }
}
