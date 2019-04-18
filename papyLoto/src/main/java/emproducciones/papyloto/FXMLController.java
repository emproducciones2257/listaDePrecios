package emproducciones.papyloto;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import modelo.jugadasPapiLoto;
import modelo.loto;
import modelo.validaciones;


public class FXMLController implements Initializable {
    @FXML
    private Button btnCarga;
    
    @FXML
    private Button btnSortear;
    
    @FXML
    private TextField txtNumA;
    
    @FXML
    private TextField txtNumB;
    
    @FXML
    private TextField txtNumC;
    
    @FXML
    private TextField txtNombre;
    
    private final jugadasPapiLoto jugadas = new jugadasPapiLoto();
    
    validaciones uti = new validaciones();
    
    @FXML
    public void cargarPapis() {
        
        byte tempA = Byte.parseByte(txtNumA.getText());
        byte tempB = Byte.parseByte(txtNumB.getText());
        byte tempC = Byte.parseByte(txtNumC.getText());
        String temNom = txtNombre.getText();
        if(uti.verificarNumeros(tempA,tempB, tempC)){
            jugadas.getJugada(new loto(tempA,tempB, tempC, temNom, Boolean.TRUE));
            limpiarComponenetes();
        }else{
            mensajeAInformar();
            
        }
        

    }
    
    @FXML
    public void mostrarRegistro (){
        
        jugadas.recorrerColeccion(jugadas);
        limpiarComponenetes();  
    }
    
    private void limpiarComponenetes () {
        txtNombre.setText("");
        txtNumA.setText("");
        txtNumB.setText("");
        txtNumC.setText("");
    }
    
    public static void mensajeAInformar() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error al cargar Papi Loto");
        alert.setHeaderText(null);
        alert.setContentText("Revisar los numeros");

        alert.showAndWait();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
