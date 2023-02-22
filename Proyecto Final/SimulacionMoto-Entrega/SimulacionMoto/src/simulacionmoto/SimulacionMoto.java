/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the lector.
 */
package simulacionmoto;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author ismae
 */
public class SimulacionMoto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Declaracion de variables
        System.out.println("-----------------INICIO--------------------");
        Configurador confi=new Configurador("Parametros.txt");
        LectorArchivos lector = new LectorArchivos();
        System.out.println("-----------------LEYENDO DATOS--------------------");
        //Lectura de datos motoyBMSS
        RestriccionesMotoYBMS restricciones= new RestriccionesMotoYBMS();
        lector.cargaDatosMotoYBMS(confi.getRutaMotoYBMS(),restricciones);
        //Lectura de datos circuito
        Circuito circuito=new Circuito(restricciones);
        String rutaCircuito=confi.getRutaCircuito();
        lector.cargaDatosCircuito(rutaCircuito,circuito);
        System.out.println(restricciones.toString());
        
        System.out.println("-----------------CALCULADO RANGO DE VELOCIDADES DE LOS SECTORES--------------------");
        circuito.calcularRangoVelocidades();
        //circuito.mostrarPorPantalla();
        
        System.out.println("-----------------GENERANDO COMPORTAMIENTO PILOTOS--------------------");
        int numPilotos= confi.getNPilotos();
        ArrayList<Piloto> pilotosFactibles= new ArrayList<>();
        ArrayList<Piloto> mejoresPilotos= new ArrayList<>();
        
        GeneradorPilotos generadorPilotos = new GeneradorPilotos(circuito,numPilotos,restricciones,pilotosFactibles);
        generadorPilotos.generarSinBMS(confi.getNumVueltas());
        //Pilotos que cumplen las restricciones maximas de la moto y bms
        System.out.println("-----------------PILOTOS FACTIBLES--------------------");
        for(int i=0;i<pilotosFactibles.size();i++){
            System.out.println("+++++++++++++++Piloto Factble "+i+" ++++++++++++: ");
            pilotosFactibles.get(i).mostrarComportamiento();
            pilotosFactibles.get(i).moto.mostrarMayoresValores();
            System.out.println("Tiempo de vuelta: "+pilotosFactibles.get(i).tiempo+" s");
            //pilotosFactibles.get(i).mostrarComportamiento();
            mejoresPilotos.add(pilotosFactibles.get(i));
            //Guardamos todos los piloto en un array para luego sacar los mejores
        }
        //Por cada bms de cada piloto factible generamos mas pilotosFactibles aleatorios dados ese bms
        //En un futuro se podria implementar con los pilotos que generen mejor tiempo pero actualmente genera muy pocos factibles
        System.out.println("-----------------GENERANDO PILOTOS DADOS LOS BMS DE LOS MEJORES PILOTOS--------------------");
        ArrayList<Piloto> pilotosFactiblesDadoBMS;
        ArrayList<BMS> configuracionesBMS=  new ArrayList<>();
        for(int i=0;i<pilotosFactibles.size();i++){
            pilotosFactiblesDadoBMS=new ArrayList<>();
            configuracionesBMS.add(pilotosFactibles.get(i).getBms());
            System.out.println("/////////////////////Generando pilotos para el siguiente BMS///////////////////////");
            System.out.println(configuracionesBMS.get(i).toString());
            
            GeneradorPilotos generadorPilotos2 = new GeneradorPilotos(circuito,numPilotos,restricciones,pilotosFactiblesDadoBMS);
            generadorPilotos2.generarDadoUnBMS(configuracionesBMS.get(i),confi.getNumVueltas());
            for(int k=0;k<generadorPilotos2.pilotosBMS.size();k++){
                mejoresPilotos.add(generadorPilotos2.pilotosBMS.get(k));
                //Los guardamos
            }
        }
        int numMejoresPilotos=10;
        System.out.println("-----------------MEJORES "+numMejoresPilotos+" PILOTOS--------------------");
        generadorPilotos.ordenarPilotosTiempo(mejoresPilotos);
        
        generadorPilotos.mostrarXMejoresPilotos(mejoresPilotos,numMejoresPilotos);
        /////////////////////////LOG///////////////////////////
        String ficheroSalidaComportamiento= "SalidaComportamientoMejoresPilotos.csv";
        Logger lg1 = new Logger(ficheroSalidaComportamiento, generadorPilotos.getLogComportamiento().toString());
        String ficheroSalidaBMS="SalidaBMSMejoresPilotos.csv";
        Logger lg2=new Logger(ficheroSalidaBMS,generadorPilotos.getLogBMS().toString());
        String ficheroSalidaVelocidadesLimiteSectores="VelocidadesLimiteSectores.csv";
        Logger lg3=new Logger(ficheroSalidaVelocidadesLimiteSectores,circuito.getLogVelocidadesLimiteSectores().toString());
        
        System.out.println("----------------------FIN-------------------");
    
    }
}