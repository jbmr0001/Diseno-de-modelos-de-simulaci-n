
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ismae
 */
public class EditorArchivos {
    
    ArrayList<ArrayList<Float>> circuito;
    
    public EditorArchivos(){
        circuito = new ArrayList<>();
    }
    
    public void cargaDatosCircuito(String ruta){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        circuito.clear();         //se borra el circuito anterior, si lo hubiese
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File (ruta);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea = null;
            while((linea=br.readLine())!=null){
                String[] fila=linea.split("\\t");   //separamos por la tabulación
                ArrayList<Float> nuevo=null;
                for (int i = 0; i < fila.length; i++) {
                    nuevo.add(Float.valueOf(fila[i]));
                }
                circuito.add(nuevo);
            }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    }
    
    public ArrayList<ArrayList<Float>> getCircuito(){
        return circuito;
    }
}
