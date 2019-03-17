package emproducciones.aver;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.control.TextArea;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import modelo.*;

public class accionesPDF {
	
	public void crearagregarPaginas (File ruta) {
		
		PDDocument documentoVacio = new PDDocument(); //creo una instancia para generar un doc PDF vacio
		
		try {
			
			documentoVacio.addPage(new PDPage()); //creo un objeto Page para agregarle paginas al pdf
			
			documentoVacio.addPage(new PDPage());
			
			documentoVacio.addPage(new PDPage());
			
			documentoVacio.save(ruta); // Guardo el documento vacio
			
			documentoVacio.close();
			
			System.out.println("Documento creado");
		} catch (IOException e) {
                    // TODO Auto-generated catch block

		}
		}

	public void cargarPdfCambioNombre (File ruta) {
		
		PDDocument pdf = new PDDocument();
		
		try {
			//cargo el pdf existente
		      
		      PDDocument document = PDDocument.load(ruta);
		        
		      System.out.println("PDF loaded"); 
		        
		      //Agrego una hoja en blanco 
		      document.addPage(new PDPage());  

		      //guardo el pdf 
		      document.save(ruta);

		      //cierro el pdf 
		      document.close(); 
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public void eliminarPaginaPDF (File ruta) {
		
		try {
			// cargo el documento existente

			PDDocument documento = PDDocument.load(ruta);
			
			//almaceno la cantidad de paginas que tiene el pdf
			
			int cantPaginas = documento.getNumberOfPages();
			
			//informo la cantidad de paginas
			
			System.out.println("El archivo contiene: " + cantPaginas + " paginas");
			
			//elimino la pagina que deseo
			
			documento.removePage(2);
			
			//vuelvo a informar la cantidad de paginas
			System.out.println("El archivo ahora contiene: " + documento.getNumberOfPages() + " paginas");
			
			//almaceno el documento modificado
			
			documento.save(ruta);
			
			documento.close();
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertarTextoAPdf (File ruta) {
	
		try {
			
			//cargo el pdf para escribir
			PDDocument documento = PDDocument.load(ruta);
			
			//obtengo la pagina del documento a escribir
			
			PDPage pagina = documento.getPage(1);
			
			//creo una instancia PDPageContentStream para comenzar el flujo de escritura
			
			PDPageContentStream procesoEscritura = new PDPageContentStream(documento, pagina);
			
			//indico que voy a comenzar a escribir en el pdf
			
			procesoEscritura.beginText();
			
			//indico la posicion que tendra el texto en el documento
			
			procesoEscritura.newLineAtOffset(25, 100);
			
			//indico fuente y tamaño
			
			procesoEscritura.setFont(PDType1Font.TIMES_ROMAN, 12);
			
			//escribo en el pdf
			
			procesoEscritura.showText("Hola Mundo Java");		   
			
			//finalizo la escritura
			
			procesoEscritura.endText();
			
			//cierro el flujo de escritura
			
			procesoEscritura.close();
			
			documento.save(ruta);
			
			documento.close();
			
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

	public void insertarMultiLinea(File ruta) {
		
try {
			
			//cargo el pdf para escribir
			PDDocument documento = PDDocument.load(ruta);
			
			//obtengo la pagina del documento a escribir
			
			PDPage pagina = documento.getPage(0);
			
			//creo una instancia PDPageContentStream para comenzar el flujo de escritura
			
			PDPageContentStream procesoEscritura = new PDPageContentStream(documento, pagina);
			
			//indico que voy a comenzar a escribir en el pdf
			
			procesoEscritura.beginText();
			
			//indico la posicion que tendra el texto en el documento
			
			procesoEscritura.newLineAtOffset(25, 100);
			
			//indico fuente y tamaño
			
			procesoEscritura.setFont(PDType1Font.TIMES_ROMAN, 12);
			
			//esta linea sirve para la linea inicial
			
			procesoEscritura.setLeading(14.0F);
			
			//escribo en el pdf
			
			procesoEscritura.showText("Hola Mundo Java");
			
			//hago un salto de linea
			
			procesoEscritura.newLine();
			
			procesoEscritura.showText("Segunda linea del PDF");
			
			//finalizo la escritura
			
			procesoEscritura.endText();
			
			//cierro el flujo de escritura
			
			procesoEscritura.close();
			
			documento.save(ruta);
			
			documento.close();
			
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void extraerTextoPdf (File ruta, TextArea area, int p) {
            
		
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
            
        modeloObjetoDatos listaProductos = new modeloObjetoDatos();
        
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

                listaProductos.setRegistro(temp);
                               
                contador=0;
                codigo="";
                descripcion="";
                precio="";
                Leido = "";
	}
        
        accionesExcel enviarObjetoDatosAModuloExcel = new accionesExcel();
        enviarObjetoDatosAModuloExcel.recibirObjetoRecuperadoPDF(listaProductos);
        enviarObjetoDatosAModuloExcel.crearExcel(p);
          
        }      		
}