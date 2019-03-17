/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Emanuel
 */
public class modeloObjetoDatos {
    
    private ArrayList<modeloDatos> registro = new ArrayList<>();
    
    public void setRegistro (modeloDatos r) {
        
        registro.add(r);
    }
    
    public ArrayList<modeloDatos> getRegistro () {
        
        return registro;
    }
    
    
    
}
