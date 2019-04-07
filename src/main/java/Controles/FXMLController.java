package Controles;

import emproducciones.aver.accionesExcel;
import emproducciones.aver.accionesPDF;
import emproducciones.aver.utilidades;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.modeloRutaArchivos;


public class FXMLController implements Initializable {

    @FXML
    private TextArea texto;
   
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnMostrar;
    @FXML
    private TextField areaNombre;
    @FXML
    private Button cosasExcel;
    @FXML 
    private TextField txtPorcentaje;
    
    @FXML
    private TitledPane tPaneE;
    
    @FXML
    private TextField areaNombreExcel;
    
    
    private File rutaSeleccionadaPdf;
    
    private File rutaSeleccionadaExcel;
    
    modeloRutaArchivos rutaPdf = new modeloRutaArchivos();
    
    modeloRutaArchivos rutaExcel = new modeloRutaArchivos();
    
    utilidades utilidad = new utilidades();
    
    @FXML
    private void accionBuscar (){
        
        rutaPdf=utilidad.buscarArchivo(new FileNameExtensionFilter("Documento PDF", "pdf"));

        if (rutaPdf.getnombreArchivo()!=null){
            
            rutaSeleccionadaPdf=rutaPdf.getrutaSeleccionada();
            
            areaNombre.setText(rutaPdf.getnombreArchivo());    
        btnMostrar.setDisable(false);
        btnLimpiar.setDisable(false);
        cosasExcel.setDisable(false);
        cosasExcel.setDisable(false);
        areaNombreExcel.setDisable(false);
            
        }
        
       
            
        }
    
    @FXML
    private void accionMostrar() throws IOException {
        accionesPDF p = new accionesPDF();

        p.extraerTextoPdf(rutaSeleccionadaPdf, texto, Integer.parseInt(txtPorcentaje.getText()));
   
    }
    
    @FXML
    
    private void accionBorrar (){
    texto.setText("");
    btnMostrar.setDisable(true);
    btnLimpiar.setDisable(true);
    areaNombre.setText("");
    cosasExcel.setDisable(true);
    areaNombreExcel.setDisable(true);
    }

    @FXML
    private void accionExcel () throws IOException{
        
        rutaExcel=utilidad.buscarArchivo(new FileNameExtensionFilter("Documento Excel", "xlsx"));
        
        rutaSeleccionadaExcel = rutaExcel.getrutaSeleccionada();

        areaNombreExcel.setText(rutaExcel.getnombreArchivo());

        //accionesExcel excel = new accionesExcel();
      
        //excel.leerExcel();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
