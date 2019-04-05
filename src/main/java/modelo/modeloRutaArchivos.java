/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;

/**
 *
 * @author Emanuel
 */
public class modeloRutaArchivos {

    private File rutaSeleccionadaPdf;

    private String nombreArchivo;

    /////////////////////////////////////////////////
    public void setrutaSeleccionada(File a) {

        rutaSeleccionadaPdf = a;
    }

    public void setnombreArchivo(String a) {

        nombreArchivo = a;
    }

    public File getrutaSeleccionada() {

        return rutaSeleccionadaPdf;
    }

    public String getnombreArchivo() {

        return nombreArchivo;
    }
}
