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
    
    public void crearLibroExcel () throws FileNotFoundException, IOException {
      //Create Blank workbook
      XSSFWorkbook libro = new XSSFWorkbook();
      

      //Create file system using specific name
      FileOutputStream out = new FileOutputStream(new File("Libro Nuevo.xlsx"));

      //write operation workbook using file out object 
      libro.write(out);
      out.close();
      System.out.println("Libro Creado Correctamente");
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
    
    public void crearLibroConDatos () throws FileNotFoundException, IOException{
        
          //Create blank workbook
      XSSFWorkbook libro = new XSSFWorkbook(); 

      //Create a blank sheet
      XSSFSheet hoja = libro.createSheet("INFO");

      //Create row object
      XSSFRow row;

      //This data needs to be written (Object[])
      Map < String, Object[] > empinfo = 
      new TreeMap < String, Object[] >();
      empinfo.put( "1", new Object[] { "EMP ID", "EMP NAME", "DESIGNATION" });
      empinfo.put( "2", new Object[] { "tp01", "Gopal", "Technical Manager" });
      empinfo.put( "3", new Object[] { "tp02", "Manisha", "Proof Reader" });
      empinfo.put( "4", new Object[] { "tp03", "Masthan", "Technical Writer" });
      empinfo.put( "5", new Object[] { "tp04", "Satish", "Technical Writer" });
      empinfo.put( "6", new Object[] { "tp05", "Krishna", "Technical Writer" });
      empinfo.put( "7", new Object[] { "tp06", "Marquez", "Programador" });
      
      //Iterate over data and write to sheet
      Set < String > clave = empinfo.keySet();
      int fila = 0;

      for (String key : clave) {
         row = hoja.createRow(fila++);
         Object [] objectArr = empinfo.get(key);
         int cellid = 0;

         for (Object obj : objectArr) {
            XSSFCell cell = row.createCell(cellid++);
            cell.setCellValue((String)obj);
         }
      }

      //Write the workbook in file system
      FileOutputStream out = new FileOutputStream(new File("Writesheet.xlsx"));
      libro.write(out);
      out.close();
      System.out.println("Writesheet.xlsx written successfully");
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
    
    
    public void tiposDatos () throws FileNotFoundException, IOException{
        
      XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook.createSheet("cell types");
      
      XSSFRow row = spreadsheet.createRow((short) 2);
      row.createCell(0).setCellValue("Type of Cell");
      row.createCell(1).setCellValue("cell value");
      
      row = spreadsheet.createRow((short) 3);
      row.createCell(0).setCellValue("set cell type BLANK");
      row.createCell(1);
      
      row = spreadsheet.createRow((short) 4);
      row.createCell(0).setCellValue("set cell type BOOLEAN");
      row.createCell(1).setCellValue(true);
      
      row = spreadsheet.createRow((short) 5);
      row.createCell(0).setCellValue("set cell type ERROR");
      row.createCell(1).setCellValue(3);
      
      row = spreadsheet.createRow((short) 6);
      row.createCell(0).setCellValue("set cell type date");
      row.createCell(1).setCellValue(new Date());
      
      row = spreadsheet.createRow((short) 7);
      row.createCell(0).setCellValue("set cell type numeric");
      row.createCell(1).setCellValue(20);
      
      row = spreadsheet.createRow((short) 8);
      row.createCell(0).setCellValue("set cell type string");
      row.createCell(1).setCellValue("A String");
      
      FileOutputStream out = new FileOutputStream(new File("typesofcells.xlsx"));
      workbook.write(out);
      out.close();
      System.out.println("typesofcells.xlsx written successfully");
   }
    
    public void formulas () throws IOException {
        
              XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook.createSheet("formula");
      XSSFRow row = spreadsheet.createRow(1);
      XSSFCell cell = row.createCell(1);
      
      cell.setCellValue("A = ");
      cell = row.createCell(2);
      cell.setCellValue(2);
      row = spreadsheet.createRow(2);
      cell = row.createCell(1);
      cell.setCellValue("B = ");
      cell = row.createCell(2);
      cell.setCellValue(4);
      row = spreadsheet.createRow(3);
      cell = row.createCell(1);
      cell.setCellValue("Total = ");
      cell = row.createCell(2);
      
      // Create SUM formula
      cell.setCellType(FORMULA);
      cell.setCellFormula("SUM(C2:C3)");
      cell = row.createCell(3);
      cell.setCellValue("SUM(C2:C3)");
      row = spreadsheet.createRow(4);
      cell = row.createCell(1);
      cell.setCellValue("POWER =");
      cell=row.createCell(2);
      
      // Create POWER formula
      cell.setCellType(FORMULA);
      cell.setCellFormula("POWER(C2,C3)");
      cell = row.createCell(3);
      cell.setCellValue("POWER(C2,C3)");
      row = spreadsheet.createRow(5);
      cell = row.createCell(1);
      cell.setCellValue("MAX = ");
      cell = row.createCell(2);
      
      // Create MAX formula
      cell.setCellType(FORMULA);
      cell.setCellFormula("MAX(C2,C3)");
      cell = row.createCell(3);
      cell.setCellValue("MAX(C2,C3)");
      row = spreadsheet.createRow(6);
      cell = row.createCell(1);
      cell.setCellValue("FACT = ");
      cell = row.createCell(2);
      
      // Create FACT formula
      cell.setCellType(FORMULA);
      cell.setCellFormula("FACT(C3)");
      cell = row.createCell(3);
      cell.setCellValue("FACT(C3)");
      row = spreadsheet.createRow(7);
      cell = row.createCell(1);
      cell.setCellValue("SQRT = ");
      cell = row.createCell(2);
      
      // Create SQRT formula
      cell.setCellType(FORMULA);
      cell.setCellFormula("SQRT(C5)");
      cell = row.createCell(3);
      cell.setCellValue("SQRT(C5)");
      workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
      FileOutputStream out = new FileOutputStream(new File("formula.xlsx"));
      workbook.write(out);
      out.close();
      System.out.println("fromula.xlsx written successfully");
   }

    public void crearExcel (int porcentaje) throws FileNotFoundException, IOException{
        
          //Create blank workbook
      XSSFWorkbook libro = new XSSFWorkbook(); 

      //Create a blank sheet
      XSSFSheet hoja = libro.createSheet("Precios");

      //Create row object
      XSSFRow filaTemp;

      
      /*registros.add(new modeloDatos(123, "Articulo 1", 25.0));
      registros.add(new modeloDatos(456, "Cosa 2", 456.3));
      registros.add(new modeloDatos(789, "goyete 3", 25.0));*/
      
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

      //Write the workbook in file system
      FileOutputStream out = new FileOutputStream(new File("temporal a ver.xlsx"));
      libro.write(out);
      out.close();
      System.out.println("Writesheet.xlsx written successfully");
   }
 }
    
   
