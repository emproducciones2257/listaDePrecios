package Controles;

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
import modelo.modeloObjetoDatos;
import modelo.modeloRutaArchivos;


public class FXMLController implements Initializable {

    @FXML
    private TextArea texto;
   
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnProcesar;
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
    
    modeloObjetoDatos pdfExtraido = new modeloObjetoDatos();
    
    utilidades utilidad = new utilidades();
    
    @FXML
    private void accionBuscar (){
        
        rutaPdf=utilidad.buscarArchivo(new FileNameExtensionFilter("Documento PDF", "pdf"));

        if (rutaPdf.getnombreArchivo()!=null){
            rutaSeleccionadaPdf=rutaPdf.getrutaSeleccionada();
            areaNombre.setText(rutaPdf.getnombreArchivo());    
            btnProcesar.setDisable(false);
            btnLimpiar.setDisable(false);
            cosasExcel.setDisable(false);
            cosasExcel.setDisable(false);
            areaNombreExcel.setDisable(false); 
            escibirCuadroTexto("Se cargo correctamente el archivo PDF");
            escibirCuadroTexto(" ");
            escibirCuadroTexto(" ");
            escibirCuadroTexto("/////////////////////////////////////");
        }        
    }
    
    @FXML
    private void accionProcesar() throws IOException {
        
        accionesPDF p = new accionesPDF();
        
        pdfExtraido.setColeccion(p.extraerTextoPdf(rutaSeleccionadaPdf));
        
        p.recorrerColeccion(pdfExtraido);

        escibirCuadroTexto("Archivo PDF con: " + pdfExtraido.getRegistro().size() +" registros procesados correctamente");
    }
    
    @FXML
    private void accionBorrar (){
    texto.setText("");
    btnProcesar.setDisable(true);
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
    
    public void escibirCuadroTexto (String t) {
        
        texto.appendText(t);
        texto.setWrapText(true);
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
