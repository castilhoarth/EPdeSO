package escalonador;

import java.util.*;

public class ListaProntos {
    
   protected static LinkedList<BCP> listaProntos = new LinkedList<>();
   
   protected static Queue<BCP> listaProntosOrdenados = new PriorityQueue<>();
   
    
    public static void ordenarProntos (){
        
        listaProntosOrdenados.clear();
        
        for (BCP ord : listaProntos){
            
            listaProntosOrdenados.add(ord);
        }
    }
   

}
