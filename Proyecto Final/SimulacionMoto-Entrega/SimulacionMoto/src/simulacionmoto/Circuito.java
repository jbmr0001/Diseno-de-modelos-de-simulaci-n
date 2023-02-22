/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;

import java.util.ArrayList;

/**
 *
 * @author Pc
 */
public class Circuito {
    /**
    * Lista de Float con la distancia de cada sector
    */
    ArrayList<Float> distanciaSectores;
    /**
    * Lista de Float con la curvatura en grados de cada sector
    */
    ArrayList<Float> curvaSectores;
    /**
    * Lista de Float con la pendiente en cada sector
    */
    ArrayList<Float> pendienteSectores;
    /**
    * Lista de Double con la velocidad máxima que calcularemos a la que se podrá ir en cada sector
    */
    ArrayList<Double> velocidadMaximaCalculada;
    /**
    * Logger para escribir los datos de salida en un archivo csv
    */
    private final StringBuilder logVelocidadLímite;
    /**
     * Restricciones de la moto
     */
    RestriccionesMotoYBMS restricciones;

    /**
     * Constructor de la clase
     */
    public Circuito(RestriccionesMotoYBMS restricciones) {
        this.distanciaSectores = new ArrayList();
        this.curvaSectores = new ArrayList();
        this.pendienteSectores = new ArrayList();
        this.velocidadMaximaCalculada = new ArrayList();
        this.logVelocidadLímite=new StringBuilder();
        this.restricciones=restricciones;
       
    }
    ////////////////////////////////Getters/////////////////////////////
    /**
     * Getter de la Lista de distancias de cada sector
     * @return Una lista de Float con los datos
     */
    public ArrayList<Float> getDistanciaSectores() {
        return distanciaSectores;
    }
    /**
     * Getter de la Lista de grado de curvaturas de cada sector
     * @return Una lista de Float con los datos
     */
    public ArrayList<Float> getCurvaSectores() {
        return curvaSectores;
    }

    /**
     * Getter de la Lista de pendientes de cada sector
     * @return Una lista de Float con los datos
     */
    public ArrayList<Float> getPendienteSectores() {
        return pendienteSectores;
    }

    /**
     * Getter de la Lista de velocidades máximas para cada sector calculadas
     * @return Una lista de Double con los datos
     */
    public ArrayList<Double> getVelocidadMaximaCalculada() {
        return velocidadMaximaCalculada;
    }
    ////////////////////////////////Setters/////////////////////////////
    /**
     * Setter de la Lista de distancias de cada sector 
     * @param distanciaSectores
     */
    public void setDistanciaSectores(ArrayList<Float> distanciaSectores) {
        this.distanciaSectores = distanciaSectores;
    }
    /**
     * Setter de la Lista de grado de curvaturas de cada sector 
     * @param curvaSector
     */
    public void setCurvaSector(ArrayList<Float> curvaSector) {
        this.curvaSectores = curvaSector;
    }
    /**
     * Setter de la Lista de pendientes de cada sector 
     * @param pendienteSector
     */
    public void setPendienteSector(ArrayList<Float> pendienteSector) {
        this.pendienteSectores = pendienteSector;
    }
    /**
     * Setter de la Lista de velocidades máximas para cada sector calculadas
     * @param velocidadMaximaCalculada 
     */
    public void setVelocidadMaximaCalculada(ArrayList<Double> velocidadMaximaCalculada) {
        this.velocidadMaximaCalculada = velocidadMaximaCalculada;
    }
    /**
     * Función para mostrar por pantalla toda la información del cirucito incluidas las velocidades maximas calculadas
     */
    void mostrarPorPantalla(){
        for(int i=0;i<this.pendienteSectores.size();i++){
            System.out.print("Sector: "+i);
            System.out.print(" Distancia: "+this.distanciaSectores.get(i));
            System.out.print(" Curvatura: "+this.curvaSectores.get(i));
            System.out.print(" Pendiente: "+this.getPendienteSectores().get(i));
            if(this.getVelocidadMaximaCalculada().isEmpty()){
                System.out.print(" Vel max calculada: "+"NO CALCULADA");
            }else{
                System.out.print(" Vel max calculada: "+this.getVelocidadMaximaCalculada().get(i));
            }
            System.out.println();
        }
    }
    /**
     * Función para calcular las velocidades máximas en cada sector
     */
    void calcularRangoVelocidades(){
        for(int i=0;i<this.distanciaSectores.size();i++){
            //valor de aceleracion lateral de una moto
            Double aceleracionLateral=Double.valueOf(restricciones.getAceleracionLateral());
            Double constante=Constantes.conversionKMHaMS;//Constante fija de la formula
            Double AR=aceleracionLateral*this.getCurvaSectores().get(i);
            Double vmax;
            Double vmaxPendienteAplicada;
            if(AR<0){
                AR=AR*-1;
            }
            Double pendiente=Double.valueOf(this.pendienteSectores.get(i));
            if(AR==0){//Si es una recta establecemos la velocidad maxima de la moto
                
                if(pendiente<0){//Si es cuesta abajo
                    vmax=Double.valueOf(this.restricciones.getVelocidad_max());//aumentamos velocidad por la pendiente
                    vmaxPendienteAplicada=vmax*(1-pendiente);
                }else{//Si es cuesta arriba
                    vmax=Double.valueOf(this.restricciones.getVelocidad_max());//disminuimos velocidad con por la pendiente
                    vmaxPendienteAplicada=vmax*(1-pendiente);
                }
            }else{
          
                if(pendiente<0){//Si es cuesta abajo
                    vmax=constante*Math.sqrt(AR);//aumentamos velocidad por la pendiente
                    vmaxPendienteAplicada=constante*Math.sqrt(AR)*(1-pendiente);
                }else{//Si es cuesta arriba
                    vmax=constante*Math.sqrt(AR);//disminuimos velocidad con por la pendiente
                    vmaxPendienteAplicada=constante*Math.sqrt(AR)*(1-pendiente);
                }
                
            }
            this.velocidadMaximaCalculada.add(vmaxPendienteAplicada);
           
            //System.out.print("Curvatura: "+this.getCurvaSectores().get(i)+" AR: "+AR+" Vmax "+vmax+" VmaxPendienteaplicada"+vmaxPendienteAplicada+" Pendiente: "+this.pendienteSectores.get(i));
            System.out.print("Pendiente: "+this.pendienteSectores.get(i)+" Vmax "+vmax+" pendiente aplicada -> "+vmaxPendienteAplicada);
            System.out.println();
        }
        System.out.println("Rangos de velocidades calculado");
    }
    /**
     * Getter del número de sectores del circuito
     * @return Entero con el numero de sectores
     */
    public int getNumSectores(){
        return this.getCurvaSectores().size();
    }
    /**
     * Getter de la distancia total del circuito
     * @param numVueltas Le pasamos el número de vueltas que vamos a dar
     * @return Double con la distancia total recorrida
     */
    public Double getDistanciaTotal(int numVueltas){
        Double sumaDistancia=0.0;
        for(int i=0;i<this.distanciaSectores.size();i++){
            sumaDistancia=sumaDistancia+this.distanciaSectores.get(i);
        }
        return sumaDistancia*numVueltas;
    }
    
    /**
     * @Brief Getter del log
     * @return Un StringBuilder con las cadenas de texto insertadas en el log BMS
     */
    public StringBuilder getLogVelocidadesLimiteSectores() {
        for(int i=0;i<velocidadMaximaCalculada.size();i++){
            this.logVelocidadLímite.append("Sector"+i);
            this.logVelocidadLímite.append(";");
            this.logVelocidadLímite.append(redondear(this.velocidadMaximaCalculada.get(i)));
            this.logVelocidadLímite.append(";");
            this.logVelocidadLímite.append("\n");
        }

        return this.logVelocidadLímite;
    }
    /**
     * Método para redondear números con dos decimales para poder pasarlos a un csv
     * @param numero A redondear
     * @return Double con el número redondeado
     */
    Double redondear(Double numero){
        return Math.round(numero*100.0)/100.0;   
    }
    
}