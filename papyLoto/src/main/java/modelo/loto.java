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
public class loto {
    
    private byte numA;
    
    private byte numb;
    
    private byte numc;
    
    private String nombreJugador;

    public loto(byte numA, byte numb, byte numc, String nombreJugador, Boolean estado) {
        this.numA = numA;
        this.numb = numb;
        this.numc = numc;
        this.nombreJugador = nombreJugador;
        this.estado = estado;
    }
    
    private Boolean estado;

    public byte getNumA() {
        return numA;
    }

    public void setNumA(byte numA) {
        this.numA = numA;
    }

    public byte getNumb() {
        return numb;
    }

    public void setNumb(byte numb) {
        this.numb = numb;
    }

    public byte getNumc() {
        return numc;
    }

    public void setNumc(byte numc) {
        this.numc = numc;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString (){
        
      return nombreJugador + ", " + numA +", " +numb + ", "+ numc;
              
        
    }
    
}
