/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emproducciones.aver;
import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import modelo.*;


/**
 *
 * @author Emanuel
 */
public class accionesExcel {

    ArrayList<modeloDatos> registro;
    
    public void recibirObjetoRecuperadoPDF (modeloObjetoDatos t){
        
       registro=t.getRegistro();
    }
    
    public void abrirExcel () throws FileNotFoundException, IOException{
      File file = new File("Libro Nuevo.xlsx");
      
      FileInputStream fIP = new FileInputStream(file);
      
      //Get the workbook instance for XLSX file 
      XSSFWorkbook workbook = new XSSFWorkbook(fIP);
      
      if(file.isFile() && file.exists()) {
         System.out.println("openworkbook.xlsx file open successfully.");
      } else {
         System.out.println("Error to open openworkbook.xlsx file.");
      }
    }
    
    public void leerExcel () throws FileNotFoundException, IOException  {
        
        XSSFRow fila;
         
        ArrayList <modeloDatos> productos= new ArrayList<>();
        
        int codigo=0;

        String prod="";

        double precio=0;
            
        double precioCalculado=0;
            
        modeloDatos temp = new modeloDatos();

        FileInputStream archivoEntrada = new FileInputStream(new File("temporal a ver.xlsx"));
         
        XSSFWorkbook libro = new XSSFWorkbook(archivoEntrada);
        XSSFSheet hoja = libro.getSheetAt(0);
        Iterator < Row >  rowIterator = hoja.iterator();
         
        while (rowIterator.hasNext()) {
             
            fila = (XSSFRow) rowIterator.next();
         
            Iterator  < Cell > cellIterator = fila.cellIterator();

            while ( cellIterator.hasNext()) {
                
                Cell cell = cellIterator.next();  

                switch (cell.getCellType()) {
                    
                    case NUMERIC:
                        if (codigo==0){
                            codigo=((int)cell.getNumericCellValue());
                            temp.setCodigo(codigo);
                        }else if (precio==0) {
                            precio=cell.getNumericCellValue();
                            temp.setPrecio(precio);
                        }else {
                            precioCalculado=cell.getNumericCellValue();
                            temp.setPrecioPorcentaje(precioCalculado);
                            productos.add(new modeloDatos(temp.getCodigo(), temp.getProd(), temp.getPrecio(),temp.getPrecioPorcentaje()));
                            codigo=0;
                            precio=0;
                            prod=""; 
                            precioCalculado=0;
                        }
                    break;
                    
                    case STRING:
                        prod=cell.getStringCellValue();
                        temp.setProd(prod);
                    break;
                    default:
                    break;
                } 
            }    
        }
        
        archivoEntrada.close();
      
        for (modeloDatos e : productos) {
          
          System.out.println(e.toString());
      }
    }
  
    public void crearExcel (int porcentaje) throws FileNotFoundException, IOException{
    
        XSSFWorkbook libro = new XSSFWorkbook(); 

        XSSFSheet hoja = libro.createSheet("Precios");

        XSSFRow filaTemp;

        int temporalRegistros = registro.size();
      
        int fila = 0;

        for (int i=0;i< temporalRegistros;i++) {
            filaTemp = hoja.createRow(fila++);
            int cellid = 0;
            modeloDatos tempRegitro = registro.get(i);
            XSSFCell cell = filaTemp.createCell(cellid++);
            cell.setCellValue(tempRegitro.getCodigo());
            XSSFCell cell2 = filaTemp.createCell(cellid++);
            cell2.setCellValue(tempRegitro.getProd());
            XSSFCell cell3 = filaTemp.createCell(cellid++);
            cell3.setCellValue(tempRegitro.getPrecio());
            XSSFCell cell4 = filaTemp.createCell(cellid++);
            double temp = ((tempRegitro.getPrecio()*porcentaje)/100);
            cell4.setCellValue(tempRegitro.getPrecio()+temp);  
        }

        FileOutputStream out = new FileOutputStream(new File("temporal a ver.xlsx"));
        libro.write(out);
        out.close();
        System.out.println("Archivo creado correctamente");
   }
 }
    
   
