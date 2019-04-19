/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author EMProducciones
 */
public class validaciones {
    
    public Boolean verificarNumeros(byte a, byte b, byte c) {
        
        Boolean estado;
        
        if ((a<=24)&& (b<=24)&&(c<=24)){
            estado=true;
        }else
            estado=false;
    return estado;
    }
    
}
