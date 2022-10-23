package escalonador;

import java.util.*;


public class ListaBloqueados {
    
    protected static LinkedList<BCP> listaBloqueados = new LinkedList<>();
    
    public static void diminuiTempoEspera() {
		for (BCP aux : listaBloqueados) {
			if (aux.getTempoES() > 0) aux.setTempoES(aux.getTempoES() - 1);
		}
	}

    public static void fimDeES() {
        		
        Collection<BCP> cloneCol = new LinkedList<>(listaBloqueados);
        Iterator<BCP> itr1 = cloneCol.iterator();
            
        while(itr1.hasNext()){
                
                BCP aux = itr1.next();
                if (aux.getTempoES() == 0) {
                
		aux.setEstado("PRONTO");
		ListaProntos.listaProntos.addLast(aux);
                listaBloqueados.remove(aux);
                
            } else break;
        }
    }
}
