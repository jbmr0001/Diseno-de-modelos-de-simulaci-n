/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author ismae
 */
public class LectorArchivos {
    /**
     * Lista de Float para leer los datos del circuito (distancia,curvatura y pendiente)
     */
    ArrayList<ArrayList<Float>> circuito;
    /**
     * Lista de Float para leer los datos de los valores máximos que pueden tomar una moto y un BMS
     */
    public ArrayList<Float> valores_motoybms;
  
    /**
     * Constructor de la clase
     */
    public LectorArchivos(){
        this.circuito = new ArrayList<>();
        this.valores_motoybms = new ArrayList<>();
        
    }
    /**
     * Función para cargar los datos del circuito
     * @param ruta String con la ruta del circuito
     * @param cir Circuito con el circuito en el que almacenar los datos
     */
    public void cargaDatosCircuito(String ruta,Circuito cir ){
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
                String[] fila=linea.split(",");   //separamos por la tabulación
                ArrayList<Float> nuevo=new ArrayList<>();
                //for (int i = 0; i < fila.length; i++) {
                    //nuevo.add(Float.valueOf(fila[i]));
                //}
                cir.getDistanciaSectores().add(Float.valueOf(fila[0]));
                cir.getCurvaSectores().add(Float.valueOf(fila[1]));
                cir.getPendienteSectores().add(Float.valueOf(fila[2]));
                
                //circuito.add(nuevo);
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
               System.out.println("Fichero "+ruta+" leído");
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
            System.out.println("Fichero "+ruta+" no leído");
            
         }
      }
    }
    /**
     * Función para cargar los datos del archivo de restricciones de la moto y del bms
     * @param ruta String con la ruta del archivo
     * @param restricciones RestriccionesMotoYBMS para almacenar las restricciones
     */
    public void cargaDatosMotoYBMS(String ruta,RestriccionesMotoYBMS restricciones){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        this.valores_motoybms.clear();   
        //se borra el circuito anterior, si lo hubiese
        //System.out.println(ruta);
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File (ruta);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea = null;
            while((linea=br.readLine())!=null){
                String[] fila=linea.split(",");   //separamos por coma
                Float valor=null;
                
                valor=(Float.valueOf(fila[1]));
                //System.out.print(fila[0]+": ");
                //System.out.println(fila[1]);
                
                this.valores_motoybms.add(valor);
            }
            //asignamos los vaores leídos
            restricciones.setVolt_max_bateria(this.valores_motoybms.get(0));
            restricciones.setVolt_max_celulas(this.valores_motoybms.get(1));
            restricciones.setIntensidad(this.valores_motoybms.get(2));
            restricciones.setVelocidad_max(this.valores_motoybms.get(3));
            restricciones.setPotencia_motor(this.valores_motoybms.get(4));
            restricciones.setTemperatura_max(this.valores_motoybms.get(5));
            restricciones.setPeso(this.valores_motoybms.get(6));
            restricciones.setCapacidad(this.valores_motoybms.get(7));
            restricciones.setAceleracionLateral(this.valores_motoybms.get(8));
            restricciones.setFuerzaRefirgerante(this.valores_motoybms.get(9));
            restricciones.setCargasRefigerante(this.valores_motoybms.get(10));
            
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
               System.out.println("Fichero "+ruta+" leído");
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
            System.out.println("Fichero "+ruta+" no leído");
         }
      }
    }
    /**
     * Getter del circuito con los datos insertados
     * @return Lista de Float con los datos del circuito
     */
    public ArrayList<ArrayList<Float>> getCircuito(){
        return circuito;
    }
    /**
     * Getter de los datos de restricciones de Moto y BMS
     * @return Lista de float con los valores de Moto y BMS
     */
    public ArrayList<Float> getValores_motoybms() {
        return valores_motoybms;
    }
}
