/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.DecimalFormat;

/**
 *
 * @author Emanuel
 */
public class modeloDatos {

    private int codigo;

    private String prod;

    private Double precio;
    
    private Double precioPorcentaje;
    
    private String pruebaCambio;
    
    
     public modeloDatos (int c, String p, Double d, double preCal) {
       
         codigo=c;
         
         prod= p;
         
         precio=d;
         
         precioPorcentaje=preCal;
        
    }
     public modeloDatos () {

    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the prod
     */
    public String getProd() {
        return prod;
    }

    /**
     * @param prod the prod to set
     */
    public void setProd(String prod) {
        this.prod = prod;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
        
    }
    
    /**
     * @return the precio con porcentaje
     */
    public Double getPrecioPorcentaje() {
        return precioPorcentaje;
    }

    /**
     * @param PrecioPorcentaje
     */
    public void setPrecioPorcentaje(Double PrecioPorcentaje) {
        this.precioPorcentaje = PrecioPorcentaje;
        
    }
    
    @Override
    public String toString (){
        
        return "Codigo: " + codigo + " Descripcion: "+ prod + " Precio: " + precio + " Precio Porcentaje: "+ precioPorcentaje;
   
    }
    
    

}
