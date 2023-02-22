/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Pc
 */
public class Logger {
    
    /**
     * @Brief Constructor del logger
     * @param ruta String con la ruta del archivo log
     * @param contenido String con el contenido del log
     */
    Logger(String ruta, String contenido ){
        crearLog(ruta,contenido);
    }
    
    /**
     * @Brief Función de creación del log
     * @param ruta
     * @param contenido 
     */
    public static void crearLog(String ruta, String contenido) {
        try {
            //Creamos un archivo en la ruta dada con el contenido recibido
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            pw.println(contenido);
            pw.close();
            System.out.println("Log "+ruta+" escrito con exito.");
        } catch (IOException e) {
            System.out.println("Log "+ruta+" se pudo escribir.");
        }
    }
}
