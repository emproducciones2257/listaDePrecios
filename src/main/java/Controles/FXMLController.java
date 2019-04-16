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
    private Button btnBuscarExcel;
    @FXML
    private Button btnLimpiarExcel;
    @FXML
    private Button btnProcesarExcel;
    
    @FXML
    private TextField areaNombreExcel;

    private File rutaSeleccionadaPdf;
    
    private File rutaSeleccionadaExcel;
    
    modeloRutaArchivos rutaPdf = new modeloRutaArchivos();
    
    modeloRutaArchivos rutaExcel = new modeloRutaArchivos();
    
    modeloObjetoDatos pdfExtraido = new modeloObjetoDatos();
    
    modeloObjetoDatos excelExtraido = new modeloObjetoDatos();
    
    utilidades utilidad = new utilidades();
    
    @FXML
    private void accionBuscar (){
        
        rutaPdf=utilidad.buscarArchivo(new FileNameExtensionFilter("Documento PDF", "pdf"));

        if (rutaPdf.getnombreArchivo()!=null){
            rutaSeleccionadaPdf=rutaPdf.getrutaSeleccionada();
            areaNombre.setText(rutaPdf.getnombreArchivo());    
            cambioEstadoComponente(false);
            escibirCuadroTexto("Se cargo correctamente el archivo PDF");
            escibirCuadroTexto("\r\n");
            escibirCuadroTexto("//////////////////////////////////");
            escibirCuadroTexto("\r\n");
        }        
    }
    
    @FXML
    private void accionProcesar() throws IOException {
        
        accionesPDF p = new accionesPDF();
        
        pdfExtraido.setColeccion(p.extraerTextoPdf(rutaSeleccionadaPdf));
        
        utilidad.recorrerColeccion(pdfExtraido);

        escibirCuadroTexto("Archivo PDF con: " + pdfExtraido.getRegistro().size()+ " registros procesados correctamente");
       
    }
    
    @FXML
    private void accionBorrar (){
        cambioEstadoComponente(true);
        texto.setText("");
        areaNombre.setText("");
        areaNombreExcel.setText("");
    }

    @FXML
    private void accionBuscarExcel () throws IOException{
        
        rutaExcel=utilidad.buscarArchivo(new FileNameExtensionFilter("Documento Excel", "xlsx"));
        
        rutaSeleccionadaExcel = rutaExcel.getrutaSeleccionada();

        areaNombreExcel.setText(rutaExcel.getnombreArchivo());
    }
    
    @FXML
    private void accionProcesarExcel() throws IOException {
        
        accionesExcel e = new accionesExcel();
        
        excelExtraido.setColeccion(e.leerExcel(rutaSeleccionadaExcel));
        
        utilidad.recorrerColeccion(pdfExtraido);
        
        escibirCuadroTexto("\r\n");
        escibirCuadroTexto("//////////////////////////////////");
        escibirCuadroTexto("\r\n");

        escibirCuadroTexto("Archivo Excel con: " + pdfExtraido.getRegistro().size()+ " registros procesados correctamente");
    }
    
    public void escibirCuadroTexto (String t) {
        
        texto.appendText(t);
        texto.setWrapText(true);
        
    }
    
    public void cambioEstadoComponente (Boolean e) {
        btnProcesar.setDisable(e);
        btnLimpiar.setDisable(e);
        areaNombreExcel.setDisable(e);
        btnBuscarExcel.setDisable(e);
        btnProcesarExcel.setDisable(e);
        btnLimpiarExcel.setDisable(e);  
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
