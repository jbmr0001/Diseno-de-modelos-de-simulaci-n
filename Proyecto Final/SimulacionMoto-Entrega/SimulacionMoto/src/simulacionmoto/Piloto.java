/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;

import java.util.ArrayList;

class Piloto {
    /**
     * BMS con el BMS asociado al piloto
     */
    BMS bms;
    /**
     * Lista con las distancias aceleradas en cada setor
     */
    ArrayList<Float> distanciaAceleradaSector;
    /**
     * Lista con la velocidad en cada sector
     */
    ArrayList<Float> velocidadSector;
    /**
     * Lista con la distancia frenada en cada sector
     */
    ArrayList<Float> distanciaFrenadaSector;
    /**
     * Float con el tiempo del recorrido realizado en segundos
     */
    Float tiempo;
    /**
     * Float con la batería restante
     */
    Float bateriaRestante;
    /**
     * Moto con la moto que conduce el piloto
     */
    Moto moto;
    /**
     * Entero con el numero de vueltas que da el piloto
     */
    int numVueltas;
    /**
     * Boolean que indica si es un piloto generado a partir de un BMS
     */
    private boolean hayBMS;
    /**
     * Float con la velocidad media del piloto
     */
    Float velocidadMedia;
    /**
     * Circuito con el circuito
     */
    Circuito circuito;
    /**
     * Constructor del Piloto
     * @param restricciones RestriccionesMotoYBMS con las restricciones de la moto y del BMS 
     * @param numVueltas Entero con el número de vueltas a simular
     */
    public Piloto(RestriccionesMotoYBMS restricciones,int numVueltas) {
        this.distanciaAceleradaSector = new ArrayList<>();
        this.velocidadSector = new ArrayList<>();
        this.distanciaFrenadaSector = new ArrayList<>();
        this.velocidadMedia=0.0f;
        this.tiempo = 0.0f;
        this.moto=new Moto(restricciones);
        this.bms=new BMS();
        this.numVueltas=numVueltas;
        this.hayBMS=false; // inicialmenta creamos el piloto sin BMS
        this.circuito=new Circuito(restricciones);
    }
    
    /**
     * Función para establecer elcomportamiento del piloto
     * @param circuito Circuito con el circuito a recorrer
     */
    void setComportamiento(Circuito circuito) {
        this.circuito=circuito;
        //Inicalizamos los vectores
        for(int i=0;i<circuito.getNumSectores()*numVueltas;i++){
            this.distanciaAceleradaSector.add(Float.valueOf(0));
            this.distanciaFrenadaSector.add(Float.valueOf(0));
            this.velocidadSector.add(Float.valueOf(0));
        }

        GeneraRandom generadorRandom=new GeneraRandom();
        for(int i=0;i<circuito.getNumSectores()*numVueltas;i++){//Generamos sectores de todas las vueltas
            Double velocidadMaximaSector=circuito.getVelocidadMaximaCalculada().get(i%numVueltas); 
            Float velocidadActual=moto.getVelocidad();
            Float distanciaSector=circuito.getDistanciaSectores().get(i%numVueltas);
            //Si vamos mas lento que la velocidad límite del sector, aceleramos
            if(velocidadActual<velocidadMaximaSector && (!hayBMS || moto.cumpleRestriccionesBMS(bms))){
                //Generamos aceleración aleatoria en base a la distribución uniforme
                //Array que contiene la distancia acelerada y la velocidad tras acelerar en el sector
                ArrayList<Float> aceleracion= (ArrayList<Float>)generadorRandom.generarAceleracionAleatoria(distanciaSector,velocidadActual,velocidadMaximaSector).clone();
                Float newDist=aceleracion.get(0);
                distanciaAceleradaSector.set(i,newDist);
                Float newVel=aceleracion.get(1);
                velocidadSector.set(i,newVel);
                //Materializamos aceleración
                moto.acelerar(aceleracion.get(0),aceleracion.get(1));
            //Si vamos mas rápido que la velocidad límite del sector, frenamos     
            }else{
                //Generamos frenada aleatoria en base a la distribución uniforme
                //Array que contiene la distancia frenada y la velocidad tras frenar en el sector
                ArrayList<Float> frenada=(ArrayList<Float>) generadorRandom.generarFrenadaAleatoria(distanciaSector,velocidadActual,velocidadMaximaSector).clone();
                Float newFren=frenada.get(0);
                distanciaFrenadaSector.set(i,newFren);
                Float newVel=frenada.get(1);
                velocidadSector.set(i,newVel);
                //Refrigeramos en la frenada si hay BMS
                if(hayBMS){
                    moto.chequeoRefrigeracion();
                }
                //Materializamos frenada
                moto.frenar(frenada.get(0),frenada.get(1));

            }
            
        }
        this.calcularTiempoVuelta(circuito);
        this.crearBMS();
        //this.mostrarComportamiento();
        //moto.mostrarMayoresValores();

    }
    /**
     * Función para calcular el tiempo del recorrido del piloto
     * @param circuito Circuito con el circuito a recorrer
     */
    void calcularTiempoVuelta(Circuito circuito){
        Float sumaVelocidades=0.0f;
        //Hacemos la media
        for(int i=0;i<velocidadSector.size();i++){
            sumaVelocidades=sumaVelocidades+velocidadSector.get(i);
        }
        
        velocidadMedia=sumaVelocidades/velocidadSector.size();
        Float velocidadMS=velocidadMedia/Float.valueOf(Constantes.conversionKMHaMS.toString());
        tiempo=(Float.valueOf(circuito.getDistanciaTotal(numVueltas).toString())/velocidadMS); //Pasamos metros segundo a km hora
        
    }
    /**
     * Getter del tiempo de la vuelta
     * @return Float con el tiempo de la vuelta
     */
    float getTiempoVuelta(){
        return tiempo;
    }
    /**
     * Función para crear el BMS de este piloto
     */
    private void crearBMS() {
        //Creamos el BMS sacando los valores maores alcanzados por la moto
        bms.setTemperaturaMax(moto.getMayorTemperaturaAlcanzada());
        bms.setVoltajeMax(moto.getMayorVoltajeAlcanzado());
        bms.setTemperaturaSegura(moto.getTemperaturaSegura());
        bms.setVoltajeReactivación(moto.getVoltajeReactivacion());
    }
    /**
     * Función para comprobar si un piloto es factible, cumple las restricciones de la moto y del BMS
     * @param bms BMS del piloto
     * @param restricciones Restricciones de la moto y del BMS
     * @return Boolean que indica si es factible
     */
    public boolean esFactible(BMS bms,RestriccionesMotoYBMS restricciones){
        if(moto.getMayorTemperaturaAlcanzada()<restricciones.getTemperatura_max()){
            if(moto.getMayorVoltajeAlcanzado()<restricciones.getVolt_max_bateria()){
                if(moto.getConsumoRestante()<restricciones.getCapacidadBateria()){
                    return true;
                }
            }   
        }
        
        return false;
    }

    /////////////////////GETTERS//////////////////////////
    /**
     * Getter del BMS
     * @return BMS con el bms
     */
    public BMS getBms() {
        return bms;
    }
    /**
     * Getter de la lista de distancias aceleradas por sector
     * @return Lista de Float con las distancias aceleradas
     */
    public ArrayList<Float> getDistanciaAceleradaSector() {
        return distanciaAceleradaSector;
    }
    /**
     * Getter de las distancias frenadas por sector
     * @return Lista de Float con las distancias frenadas por sector
     */
    public ArrayList<Float> getDistanciaFrenadaSector() {
        return distanciaFrenadaSector;
    }
    /**
     * Getter de la batería restante
     * @return 
     */
    public Float getBateriaRestante() {
        return bateriaRestante;
    }
    
    ////////////////////SETTERS/////////////////////////
    /**
     * Setter del BMS
     * @param bms BMS a establecer en el piloto
     */
    public void setBms(BMS bms) {
        this.bms = bms;
    }
    /**
     * Setter de la distancia acelerada por sector
     * @param distanciaAceleradaSector Lista de distancias Float
     */
    public void setDistanciaAceleradaSector(ArrayList<Float> distanciaAceleradaSector) {
        this.distanciaAceleradaSector = distanciaAceleradaSector;
    }
    /**
     * Setter de la distancia frenada por sector
     * @param distanciaFrenadaSector Lista de distancias Float
     */
    public void setDistanciaFrenadaSector(ArrayList<Float> distanciaFrenadaSector) {
        this.distanciaFrenadaSector = distanciaFrenadaSector;
    }
    /**
     * Setter del tiempo de vuelta
     * @param tiempo Float con el tiempo de vuelta
     */
    public void setTiempo(Float tiempo) {
        this.tiempo = tiempo;
    }
    /**
     * Setter de la batería restante
     * @param bateriaRestante Float con la batería restante
     */
    public void setBateriaRestante(Float bateriaRestante) {
        this.bateriaRestante = bateriaRestante;
    }
    /**
     * Función para mostrar el comportamiento del piloto en cada sector
     */
    void mostrarComportamiento(){
        System.out.println("------------Comportamiento Piloto -----------------");
        System.out.println("Distancia acelerada en cada sector");
        System.out.println(this.distanciaAceleradaSector.toString());
        System.out.println("Distancia distancia frenada en cada sector");
        System.out.println(this.distanciaFrenadaSector.toString());
        System.out.println("Velocidad en cada sector");
        System.out.println(this.velocidadSector.toString());
        System.out.println("velocidad media: "+velocidadMedia+" Km/H");
        System.out.println("DistanciaRecorrida: "+circuito.getDistanciaTotal(numVueltas)+" Metros");
        System.out.println("Tiempo: "+tiempo+" s");
        
    }
    /**
     * Setter de BMS a ture
     * @param b Boolean para establecer que el piloto es creado a través de un BMS
     */
    void setBMSQ(boolean b) {
        this.hayBMS=b;
    }
    
}