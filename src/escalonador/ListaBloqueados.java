/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escalonador;

import java.util.*;

/**
 *
 * @author caiokatsumi
 */
public class ListaBloqueados {
    
    protected static LinkedList<BCP> listaBloqueados = new LinkedList<>();
    
    public static void diminuiTempoEspera() {
		for (BCP aux : listaBloqueados) {
			if (aux.getTempoES() > 0) aux.setTempoES(aux.getTempoES() - 1);
                        //System.out.println(aux.processo.getNome() + aux.getTempoES());//diminui 1 da espera
		}
	}

    public static void fimDeES() {
        
        int terminouES = 0;
		
	//conta quantos processos tem a espera igual a zero
        
        Collection<BCP> cloneCol = new LinkedList<>(listaBloqueados);
        Iterator<BCP> itr1 = cloneCol.iterator();
            
        while(itr1.hasNext()){
                
                BCP aux = itr1.next();
                if (aux.getTempoES() == 0) {
                
		aux.setEstado("PRONTO");
		ListaProntos.listaProntos.addLast(aux);
                listaBloqueados.remove(aux);
		//terminouES++;
                
            } else break;
        }


    }
}
