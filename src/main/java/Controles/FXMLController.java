package Controles;

import emproducciones.aver.accionesExcel;
import emproducciones.aver.accionesPDF;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FXMLController implements Initializable {
    
                File ruta = new File("C:\\carpeta\\documento.pdf");
		
		File ruta2 = new File("C:\\carpeta\\documento.pdf");
		
		File ruta3 = new File("C:\\carpeta\\libreria.pdf");

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
    
    @FXML
    private void accionBuscar (){
      
        JFileChooser selectorArchivo = new JFileChooser();
        FileNameExtensionFilter filtroExtension = new FileNameExtensionFilter(
            "Documento PDF", "pdf");
        selectorArchivo.setFileFilter(filtroExtension);
        int returnVal = selectorArchivo.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            rutaSeleccionadaPdf = selectorArchivo.getSelectedFile().getAbsoluteFile();
            areaNombre.setText(rutaSeleccionadaPdf.getName());
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
        
        JFileChooser selectorArchivo = new JFileChooser();
        FileNameExtensionFilter filtroExtension = new FileNameExtensionFilter(
            "Documento Excel", ".xlsx");
        selectorArchivo.setFileFilter(filtroExtension);
        int returnVal = selectorArchivo.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            rutaSeleccionadaExcel = selectorArchivo.getSelectedFile().getAbsoluteFile();
            }
        areaNombreExcel.setText(rutaSeleccionadaPdf.getName());

      accionesExcel excel = new accionesExcel();
      
      excel.leerExcel();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
