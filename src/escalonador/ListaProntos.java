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
public class ListaProntos {
    
   protected static LinkedList<BCP> listaProntos = new LinkedList<>();
   
   //protected static SortedSet<BCP> listaProntosOrdenados = new TreeSet<>();
   protected static Queue<BCP> listaProntosOrdenados = new PriorityQueue<>();
   
   public static void filaZerada() {
       
		for (BCP aux : listaProntos) {
                    
			if (aux.getCredito() != 0) return; 
		}
		//se todos os créditos forem iguais a 0, atualiza todos os créditos à sua prioridade correspondente
		for (BCP aux : listaProntos) {
			aux.setCredito(aux.getPrioridade());
		}		
    }
    
    public static void ordenarProntos (){
        
        listaProntosOrdenados.clear();
        
        for (BCP ord : listaProntos){
            
            //System.out.println(ord);
            listaProntosOrdenados.add(ord);
        }
    }
   

}
