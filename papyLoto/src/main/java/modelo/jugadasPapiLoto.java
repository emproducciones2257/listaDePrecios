/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author EMProducciones
 */
public class jugadasPapiLoto {
    
    private ArrayList <loto> jugadas = new ArrayList();
    
    public void getJugada (loto jugada) {
        
        jugadas.add(jugada);
    }
   
    public ArrayList<loto> getJugadas() {
        return jugadas;
    }

    public void setJugadas(ArrayList<loto> jugadas) {
        this.jugadas = jugadas;
    }
    
    public void recorrerColeccion (jugadasPapiLoto l) {
        
        for (loto e : l.getJugadas()) {
            
            System.out.println(e.toString());
            
            
        }
        
        
    }

}
