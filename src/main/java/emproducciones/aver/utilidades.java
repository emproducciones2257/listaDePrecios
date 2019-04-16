/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emproducciones.aver;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.modeloDatos;
import modelo.modeloObjetoDatos;
import modelo.modeloRutaArchivos;

/**
 *
 * @author Emanuel
 */
public class utilidades {
    
    modeloRutaArchivos archivo = new modeloRutaArchivos();
    
    public modeloRutaArchivos buscarArchivo (FileNameExtensionFilter filtro) {
     
        JFileChooser b = new JFileChooser();
        
        b.setFileFilter(filtro);
        
        int returnVal = b.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            archivo.setrutaSeleccionada(b.getSelectedFile().getAbsoluteFile());
            archivo.setnombreArchivo(b.getName(b.getSelectedFile()));
        }
        return archivo;
    }
    
    public void recorrerColeccion (modeloObjetoDatos d){
            
            for (modeloDatos e : d.getRegistro()) {
                System.out.println(e.toString());
            }
        } 
}
