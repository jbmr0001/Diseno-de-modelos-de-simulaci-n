/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;

import java.util.ArrayList;
import java.util.Random;


class GeneradorPilotos {
    /**
     * Circuito con el circuito a recorrer
     */
    Circuito circuito;
    /**
     * Entero con el número de pilotos a generar
     */
    int numPilotos;
    /**
     * Restricciones que la moto ni el BMS pueden superar
     */
    RestriccionesMotoYBMS restricciones;
    /**
     * Lista de pilotos generados sin un BMS previo
     */
    ArrayList<Piloto> pilotos;
    /**
     * Lista de pilotos generados en base a un BMS
     */
    ArrayList<Piloto> pilotosBMS;
    /**
     * StringBuilder con el Logger para el comportamiento del piloto
     */
    private final StringBuilder logComportamiento;
    /**
     * String builder con el Logger para el BMS del piloto
     */
    private final StringBuilder logBMS;
    /**
     * Constructor de la clase
     * @param circuito Circuito con el circuito a recorrer
     * @param numPilotos Entero con el número de pilotos
     * @param restricciones RestriccionesMotoYBMS con las restricciones que nose pueden generar
     * @param pilotos Lista de pilotos en la que devolver los pilotos generados
     */
    public GeneradorPilotos(Circuito circuito, int numPilotos, RestriccionesMotoYBMS restricciones,ArrayList<Piloto> pilotos) {
        this.circuito = circuito;
        this.numPilotos = numPilotos;
        this.restricciones = restricciones;
        this.pilotos=pilotos;
        this.pilotosBMS=new ArrayList<>();
        this.logComportamiento = new StringBuilder();
        this.logBMS=new StringBuilder();
    }
    /**
     * Función para generar pilotos sin haber establecido un BMS previo que los limite
     */
    void generarSinBMS(int numVueltas) {
        
        for (int i = 0; i < numPilotos; i++) {
            Piloto newPiloto= new Piloto(restricciones,numVueltas);
            newPiloto.setComportamiento(circuito);
            //Aquellos pilotos que no sobrepasen las restricciones moto y BMS serán declarados como factibles
            if(newPiloto.esFactible(newPiloto.getBms(),restricciones)){
                newPiloto.calcularTiempoVuelta(circuito);
                pilotos.add(newPiloto);
                
            }
        }
        System.out.println("Total pilotos generados: "+this.numPilotos);
        System.out.println("Pilotos factibles generados: "+this.pilotos.size());
    }
    /**
     * Función para generar pilotos en base a un BMS que los limita
     * @param bms BMS con las restricciones a aplicar en la generación
     */
    void generarDadoUnBMS(BMS bms,int numVueltas){
        for (int i = 0; i < numPilotos; i++) {
            //Establecemos las restricciones sacandolas del BMS que recibimos
            restricciones = new RestriccionesMotoYBMS();
            Float temMax=bms.getTemperaturaMax();
            Float voltajeMax=bms.getVoltajeMax();
            restricciones.volt_max_bateria=voltajeMax;
            restricciones.temperatura_max=temMax;
            Piloto newPiloto= new Piloto(restricciones,numVueltas);
            newPiloto.setBMSQ(true);
            newPiloto.setComportamiento(circuito);
            //Aquellos pilotos que no sobrepasen las restricciones moto y BMS serán declarados como factibles
            if(newPiloto.esFactible(newPiloto.getBms(),restricciones)){
                newPiloto.calcularTiempoVuelta(circuito);
                pilotos.add(newPiloto);//Array en el que guardamos los pilotos BMS de esta generación
                pilotosBMS.add(newPiloto);//Array para guardar todos los pilotos BMS generados
            }
            
        }
        System.out.println("Total pilotos dado un BMS generados: "+this.numPilotos);
        System.out.println("Pilotos factibles generados: "+this.pilotos.size());
    }
    
    /**
     * Algoritmo de ordenación de listas de pilotos en base a su tiempo de vuelta usando el método de la burbuja
     * @param todosPilotos Lista con los pilotos a ordenar
     */
    void ordenarPilotosTiempo(ArrayList<Piloto> todosPilotos){
        Piloto temporal;
        for(int i=1;i<todosPilotos.size();i++){
            for(int j=0;j<todosPilotos.size()-1;j++){
                if(todosPilotos.get(j).getTiempoVuelta()>todosPilotos.get(j+1).getTiempoVuelta()){
                    temporal=todosPilotos.get(j);
                    todosPilotos.set(j,todosPilotos.get(j+1));
                    todosPilotos.set(j+1, temporal);
                }
            }
        }
    }
    /**
     * Función para mostrar los n mejores pilotos de una lista
     * @param todosPilotos Lista de pilotos 
     * @param numero Entero con los n mejores pilotos a mostrar
     */
    void mostrarXMejoresPilotos(ArrayList<Piloto> todosPilotos,int numero){
        System.out.println("Ordenados por mejores tiempos");
        //Excepción para casos en los que se pidan mas pilotos que pilotos generados
        if(numero>todosPilotos.size()){
            numero=todosPilotos.size();
            System.out.print("!!!! No se han generado suficientes pilotos factibles !!!! \n");
            System.out.print("Sólo se mostrarán: "+numero+" pilotos. \n");
        }
        //Escribimos la información numero pilotos en los archivos de salida de datos
        for(int i=0;i<numero;i++){
            System.out.print(todosPilotos.get(i).getTiempoVuelta()+" s "+todosPilotos.get(i).getBms().toString()+
                    " Distancia acelerada por sector"+todosPilotos.get(i).distanciaAceleradaSector.toString()+
                    " Distancia Frenada por sector"+todosPilotos.get(i).distanciaFrenadaSector.toString()+" Velocidad por sector "+todosPilotos.get(i).velocidadSector.toString());
            //todosPilotos.get(i).mostrarComportamiento();
            //Logs BMS
            //Primera fila del csv
            logBMS.append("MEJOR PILOTO  ").append(i+1).append(" ;").append("\n");
            logBMS.append("TIEMPO: ").append(";").append(redondear((double)todosPilotos.get(i).getTiempoVuelta())).append("\n");
            logBMS.append("TemperaturaMax").append(";");
            logBMS.append("TemperaturaSegura").append(";");
            logBMS.append("VoltajeMax").append(";");
            logBMS.append("VoltajeReactivacion").append(";");
            logBMS.append("\n");
            //Segunda fila del csv
            logBMS.append(redondear((double)todosPilotos.get(i).getBms().getTemperaturaMax())).append(";");
            logBMS.append(redondear((double)todosPilotos.get(i).getBms().getTemperaturaSegura())).append(";");
            logBMS.append(redondear((double)todosPilotos.get(i).getBms().getVoltajeMax())).append(";");
            logBMS.append(redondear((double)todosPilotos.get(i).getBms().getVoltajeReactivación())).append(";");;
            logBMS.append("\n");
            //Logs Comportamiento
            logComportamiento.append("MEJOR PILOTO : ").append(i).append(" \n");
            logComportamiento.append("DISTANCIA ACELERADA SECTOR: ").append(";");
            for(int j=0;j<todosPilotos.get(i).distanciaAceleradaSector.size();j++){
                logComportamiento.append(redondear((double)todosPilotos.get(i).distanciaAceleradaSector.get(j))).append(";");
            }
            logComportamiento.append("\n");
            logComportamiento.append("DISTANCIA FRENADA SECTOR: ").append(";");
            for(int j=0;j<todosPilotos.get(i).distanciaFrenadaSector.size();j++){
                logComportamiento.append(redondear((double)todosPilotos.get(i).distanciaFrenadaSector.get(j))).append(";");
            }
            logComportamiento.append("\n");
            logComportamiento.append("VELOCIDAD POR SECTOR: ").append(";");
            for(int j=0;j<todosPilotos.get(i).velocidadSector.size();j++){
                logComportamiento.append(redondear((double)todosPilotos.get(i).velocidadSector.get(j))).append(";");
            }
            logComportamiento.append("\n");
            System.out.println();
 
        }
    }
    
    /**
     * @Brief Getter del log
     * @return Un StringBuilder con las cadenas de texto insertadas en el log comportamiento
     */
    public StringBuilder getLogComportamiento() {
        return this.logComportamiento;
    }
    
    /**
     * @Brief Getter del log
     * @return Un StringBuilder con las cadenas de texto insertadas en el log BMS
     */
    public StringBuilder getLogBMS() {
        return this.logBMS;
    }
    
    /**
     * Función para redondear un número con dos dígitos tras la coma
     * @param numero Double con el número a redondear
     * @return Double con el número redondeado
     */
    Double redondear(Double numero){
        return Math.round(numero*100.0)/100.0;   
    }
}
