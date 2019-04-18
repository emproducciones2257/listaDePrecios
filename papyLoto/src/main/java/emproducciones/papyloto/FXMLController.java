package emproducciones.papyloto;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.jugadasPapiLoto;
import modelo.loto;


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
    
    @FXML
    public void cargarPapis() {
        byte tempA = Byte.parseByte(txtNumA.getText());
        byte tempB = Byte.parseByte(txtNumB.getText());
        byte tempC = Byte.parseByte(txtNumC.getText());
        String temNom = txtNombre.getText();
        jugadas.getJugada(new loto(tempA,tempB, tempC, temNom, Boolean.TRUE));
        limpiarComponenetes();

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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
