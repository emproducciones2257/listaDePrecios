package emproducciones.aver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.TextArea;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import modelo.*;

public class accionesPDF {
    modeloObjetoDatos pdfExtraido = new modeloObjetoDatos();
    
    
	
    public ArrayList<modeloDatos> extraerTextoPdf (File ruta, TextArea area, int p) {
   	System.out.println("Prueba uno");
	try {
            //cargo el documento
            PDDocument documento = PDDocument.load(ruta);
			
            // creo una instancia de la clase que me permite extraer el texto del pdf
			
            PDFTextStripper extraccion = new PDFTextStripper();
			
            String textoRecuperado = extraccion.getText(documento);
			
            documento.close();
                        
            String temp = quitarEncabezado(textoRecuperado);
			
            area.appendText(temp);
                        
            pruebaDelimitador(temp,p); 
	
	} catch (InvalidPasswordException e) {
                    // TODO Auto-generated catch block

            } catch (IOException e) {
                    // TODO Auto-generated catch block

            }	
        return pdfExtraido.getRegistro();
	}
        
        public String quitarEncabezado(String t){
            String textoListo="";
            
            String temp;
            
            Scanner escaner = new Scanner(t);

                while (escaner.hasNext()) {
                    temp=escaner.nextLine();
                    if ((temp).equals("Lista de Precios de Productos de ")){
                        escaner.nextLine();
                        escaner.nextLine();
                        escaner.nextLine();
                        escaner.nextLine();
                        escaner.nextLine();
                        escaner.nextLine();
                        temp=escaner.nextLine(); 
                        textoListo= textoListo+temp +"\n";
                    }else {
                        textoListo= textoListo+temp +"\n"; 
                    }
                }
        return textoListo; 
        }
        
        
        private void pruebaDelimitador (String t,int p) throws IOException{
                    
            Scanner elEscaner = new Scanner(t);
            String codigo="";
            String descripcion="";
            String precio="";
            String Leido = "";
            Double precioPorcentaje=0.0;
            char c;
            byte contador = 0;
        
            while (elEscaner.hasNext()) {
            
                Leido=elEscaner.nextLine();
            
                c = Leido.charAt(contador);
                
                while ((c != ' ')|| (c != ' ')){
                    
                    codigo=codigo+c;
                    
                    contador++;
                    
                    c = Leido.charAt(contador);
                }
                contador=0;
                
                Leido = Leido.replaceFirst(codigo, "");

                c = Leido.charAt(contador);
                
                while (c !='$'){
                    
                    descripcion=descripcion+c;
                    
                    contador++;
                    
                    c = Leido.charAt(contador);
                }

                Leido = Leido.replace(descripcion, "");
                
                precio= (Leido.replace("$",""));
                
                precio= (precio.replace(".",""));
                
                precio= (precio.replace(",","."));
                              
                modeloDatos temp = new modeloDatos(Integer.parseInt(codigo),descripcion, Double.parseDouble(precio),precioPorcentaje);
                
                pdfExtraido.setRegistro(temp);
                               
                contador=0;
                codigo="";
                descripcion="";
                precio="";
                Leido = "";
            } 
        } 

        public void recorrerColeccion (modeloObjetoDatos d){
            
            for (modeloDatos e : d.getRegistro()) {
                System.out.println(e.toString());
            }
            
        }
}