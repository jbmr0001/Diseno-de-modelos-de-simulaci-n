/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ismae
 */
public class Configurador {
    /**
     * Lista para almacenar la ruta del archivo de datos del circuito
     */
    ArrayList<String> rutaArchivoCircuito;
    /**
     * Lista para almacenar la ruta del archivo de datos de restricciones moto y bms
     */
    ArrayList<String> rutaArchivoMotoYBMS;
    /**
     * Lista para almacenar el número de pilotos
     */
    ArrayList<Integer> nPilotos;
    /**
     * Entero con el número de vueltas a dar al circuito
     */
    ArrayList<Integer> numVueltas;
    /**
     * Constructor de la clase
     * @param ruta String con la ruta del fichero de parámetros
     */
    Configurador(String ruta){
        this.rutaArchivoCircuito = new ArrayList<>();
        this.rutaArchivoMotoYBMS = new ArrayList<>();
        this.nPilotos = new ArrayList<>();
        this.numVueltas=new ArrayList<>();
        FileReader f=null;
        String linea;
        try{
            f= new FileReader(ruta);
            BufferedReader b=new BufferedReader(f);
            while((linea=b.readLine())!=null){
                String[] split=linea.split("=");
                switch(split[0]){
                    case "rutaCircuito":
                        String[] v=split[1].split(";");
                        for(int i=0;i<v.length;i++){
                            this.rutaArchivoCircuito.add(v[i]);
                        }
                        break;
                    case "rutaMotoyBMS":
                        String[] v2=split[1].split(";");
                        for(int i=0;i<v2.length;i++){
                            this.rutaArchivoMotoYBMS.add((v2[i]));
                        }
                        break;
                    case "nPilotos":
                        String[] v3=split[1].split(";");
                        for(int i=0;i<v3.length;i++){
                            this.nPilotos.add(Integer.parseInt(v3[i]));
                        }
                        break;
                    case "numVueltas":
                        String[] v4=split[1].split(";");
                        for(int i=0;i<v4.length;i++){
                            this.numVueltas.add(Integer.parseInt(v4[i]));
                        }
                        break;
                }
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    /**
     * Getter de la ruta del archivo de datos del circuito
     * @return String con la ruta del circuito
     */
    String getRutaCircuito(){
        return this.rutaArchivoCircuito.get(0);
    }
    /**
     * Getter de la ruta del archivo de datos y restricciones de la moto y el BMS
     * @return String con la ruta del archivo
     */
    String getRutaMotoYBMS(){
        return this.rutaArchivoMotoYBMS.get(0);
    }
    /**
     * Getter de el número de pilotos a generar
     * @return Entero con el número de pilotos a generar
     */
    int getNPilotos() {
        return this.nPilotos.get(0);
    }
    /**
     * Getter del número de vueltas a dar al circuito
     * @return Entero con el número de vueltas
     */
    int getNumVueltas(){
        return this.numVueltas.get(0);
    }
}
