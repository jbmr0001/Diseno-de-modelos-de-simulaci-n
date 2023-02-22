/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;


class Moto {
    /**
     * Float con la velocidad actual de la moto
     */
    float velocidad;
    /**
     * Float con la carga total de la batería
     */
    float cargaTotalBateria;
    /**
     * Float con la temperatura máxima que puede alcanzar la moto
     */
    float tempMax;
    /**
     * Float con la última aceleración de la moto
     */
    float aceleracion;
    /**
     * Float con la temperatura inicial de la moto
     */
    float tempIni;
    /**
     * Float con la batería actual de la moto
     */
    float bateriaActual;
    /**
     * Float con la mayor velocidad alcanzada por parte de la moto
     */
    float mayorVelocidadAlcanzada;
    /**
     * Float con la mayor temperatura alcanzada en la batería de la moto
     */
    float mayorTemperaturaAlcanzada;
    /**
     * Float con la temperaturaActual
     */
    float temperatura;
    /**
     * Float con la mayor aceleración alcanzada
     */
    float mayorAceleracionRealizada;
    /**
     * Float con el voltaje máximo de la batería de la moto
     */
    float voltajeMaximo;
    /**
     * Float con el mayor voltaje alcanzado
     */
    float mayorVoltajeAlcanzado;
    /**
     * Boolean para indicar si está frenando
     */
    boolean frenadaActiva;
    /**
     * Boolean para indicar si está refrigerando
     */
    boolean refrigeracionActiva;
    /**
     * Float con el último voltaje alcanzado por la moto
     */
    float voltaje;
    /**
     * Float con la cantidad de grados que se refrigeran
     */
    float refrigerante;
    /**
     * Entero con el número de veces que puede refrigerar
     */
    float cargasRefrigerante;
    /**
     * Float con la potencia del motor
     */
    float kmMotor;
    /**
     * Constructor de la clase
     * @param restricciones RestriccionesMotoYBMS con las restricciones maximas que puede alcanzar la moto y el BMS
     */
    public Moto(RestriccionesMotoYBMS restricciones) {
        this.velocidad=0;
        this.aceleracion=0;
        this.voltaje=0;
        this.voltajeMaximo=restricciones.getVolt_max_bateria();
        this.cargaTotalBateria=restricciones.getCapacidadBateria();
        this.tempMax=restricciones.getTemperatura_max();
        this.tempIni=restricciones.getTem_inicial();
        this.bateriaActual=cargaTotalBateria;
        this.mayorVelocidadAlcanzada=0;
        this.mayorTemperaturaAlcanzada=0;
        this.mayorAceleracionRealizada=0;
        this.mayorVoltajeAlcanzado=0;
        this.frenadaActiva=false;
        this.refrigeracionActiva=false;
        this.cargasRefrigerante=restricciones.getCapacidadEnfriado();
        this.refrigerante=restricciones.getFuerzaEnfriado();
        this.kmMotor=restricciones.getPotencia_motor();
    }
    /**
     * Boolean para indicar si hay batería disponible
     * @return Boolean que indica si hay batería
     */
    boolean hayBateria() {
        if(bateriaActual/cargaTotalBateria >= Constantes.porcentajeBateriaBaja){ //considaremos que esto es cargaTotalBateria baja 
                                       // y que si llega a este porcentaje 
                                       //entra en modo "ahorro de cargaTotalBateria"
                                       
            return true;
        }
        return false;
    }

    /**
     * Función para materializar la aceleración en la moto
     * @param distanciaAcelerada Float con la distancia que se acelerar
     * @param nuevaVelocidad Float con la velocidad a la que se acelera
     */
    void acelerar(float distanciaAcelerada,float nuevaVelocidad) {
        Float tiempo=distanciaAcelerada/nuevaVelocidad;
        Float velocidadAnterior=velocidad;
        Float incrementoDeVelocidad=nuevaVelocidad-velocidadAnterior;
        Float aceleracion=((incrementoDeVelocidad)/tiempo)/Constantes.factorConversionAceleracion;
        
        this.nuevaVelocidad(nuevaVelocidad);//Establecemos la nueva velocidad
        this.nuevaAceleracion(aceleracion);//Establecemos la nueva aceleracion
        //actualizamos la moto
        consumirbateria(aceleracion,tiempo);
        incrementarTemperatura(aceleracion,tiempo);
        incrementarVoltaje(aceleracion,tiempo);
  
    }
    /**
     * Función para materializar la frenada de la moto
     * @param distanciaFrenada Float con la distancia frenada
     * @param nuevaVelocidad Float con la nueva velocidad tras frenar
     */
    void frenar(float distanciaFrenada,float nuevaVelocidad) {
        
        Float tiempo=distanciaFrenada/nuevaVelocidad;
        Float velocidadAnterior=velocidad;
        Float deceleracion=((nuevaVelocidad-velocidadAnterior)/tiempo)/100;
        
        //como la deceleración es negativa, un consumo es como una recarga
        consumirbateria(deceleracion,tiempo);
        incrementarTemperatura(deceleracion,tiempo);
        incrementarVoltaje(deceleracion,tiempo);
        this.velocidad=nuevaVelocidad;
    }
    /**
     * Función para actualizar la velocidad de la moto
     * @param nuevaVelocidad Float con la nueva velocidad 
     */
    void nuevaVelocidad(Float nuevaVelocidad){
        //Comprobamos si es la menor aceleracion
        //Habrá casos que se supere la velocidad maxima de la moto, esto es porque es cuesta abajo
        if(nuevaVelocidad>this.mayorVelocidadAlcanzada){
            this.mayorVelocidadAlcanzada=nuevaVelocidad;
        }
        this.velocidad=nuevaVelocidad;
    }
    /**
     * Función para actualizar la aceleración de la moto
     * @param nuevaAceleracion Float con la nueva aceleración de la moto
     */
    void nuevaAceleracion(Float nuevaAceleracion){
        //Comprobamos si es la mayor aceleracion
        if(nuevaAceleracion>this.mayorAceleracionRealizada){
            this.mayorAceleracionRealizada=nuevaAceleracion;
        }
        this.aceleracion=nuevaAceleracion;
    }
    
    //////////GETTERS//////////////////
    /**
     * Getter del la batería que queda por consumir
     * @return Float con la batería que queda por consumir
     */
    float getConsumoRestante() {
        return cargaTotalBateria-bateriaActual;
    }
    /**
     * Getter de la velocidad actual
     * @return Float con la velocidad actual de la moto
     */
    public float getVelocidad() {
        return velocidad;
    }
    /**
     * Getter de la carga total de la batería
     * @return Float con la carga total de la batería
     */
    public float getCargaTotalBateria() {
        return cargaTotalBateria;
    }
    /**
     * Getter de la temperatura máxima que puede alcanzar la batería
     * @return 
     */
    public float getTempLimite() {
        return tempMax;
    }
    /**
     * Getter de la última aceleración que ha realizado la moto
     * @return Float con la última aceleración que ha realizado la moto
     */
    public float getAceleracion() {
        return aceleracion;
    }
    /**
     * Getter de la temperatura inicial de la batería de la moto
     * @return 
     */
    public float getTempIni() {
        return tempIni;
    }
    /**
     * Getter de la batería actual
     * @return Float con la batería que queda
     */
    public float getBateriaActual() {
        return bateriaActual;
    }
    /**
     * Getter de la mayor velocidad alcanzada por la moto
     * @return 
     */
    public float getMayorVelocidadAlcanzada() {
        return mayorVelocidadAlcanzada;
    }
    /**
     * Getter de la mayor temperatura alcanzada por la batería de la moto
     * @return 
     */
    public float getMayorTemperaturaAlcanzada() {
        return mayorTemperaturaAlcanzada;
    }
    /**
     * Getter de la temperatura actual de la batería de la moto
     * @return 
     */
    public float getTemperatura() {
        return temperatura;
    }
    /**
     * Getter de la mayor aceleración realizada por la moto
     * @return Float con la mayor aceleración realizada por la moto
     */
    public float getMayorAceleracionRealizada() {
        return mayorAceleracionRealizada;
    }
    /**
     * Getter del voltaje máximo posible que puede alcanzar la moto
     * @return Float con el máximo voltaje alcanzable
     */
    public float getVoltajeMaximo() {
        return voltajeMaximo;
    }
    /**
     * Getter del mayor voltaje alcanzado por la batería de la moto
     * @return Float con el mayor voltaje alcanzado por la batería de la moto
     */
    public float getMayorVoltajeAlcanzado() {
        return mayorVoltajeAlcanzado;
    }
    /**
     * Función para mostrar por pantalla los datos de la moto
     */
    void mostrarMayoresValores(){
        System.out.println("Mayor velocidad alcanzada: "+this.mayorVelocidadAlcanzada+" Km/h");
        System.out.println("Mayor aceleración alcanzada: "+this.mayorAceleracionRealizada+" m/s2");
        System.out.println("Mayor temperatura alcanzada: "+this.mayorTemperaturaAlcanzada +" ºC");
        System.out.println("Consumo: "+this.getConsumoRestante()+ " A/h");
        System.out.println("Mayor voltaje alcanzado: "+this.mayorVoltajeAlcanzado+" V");
        System.out.println("Voltaje de reactivación: "+this.getVoltajeReactivacion()+" V");
        System.out.println("Temperatura segura: "+this.getTemperaturaSegura()+" ºC");
    }
    /**
     * Función para incrementar la temperatura mayor alcanzada de la batería de la moto
     * @param aceleracion Float con la aceleración que incrementa la temperatura
     * @param tiempo Float con el tiempo de aceleración
     */
    private void incrementarTemperatura(float aceleracion,float tiempo) {
        
        if(this.kmMotor*tiempo*aceleracion>this.mayorTemperaturaAlcanzada){
            this.mayorTemperaturaAlcanzada=this.kmMotor*tiempo;
        }
    }
    /**
     * Función para consumir batría de la moto
     * @param aceleracion Float con la aceleración realizada
     * @param tiempo Float con el tiempo en el que se realizó esa aceleración
     */
    private void consumirbateria(float aceleracion,float tiempo) {

        Float kwUsados=aceleracion*tiempo*(this.voltajeMaximo/5);
        Float amperiosUsados=1000*kwUsados/this.voltajeMaximo;
        this.bateriaActual=this.bateriaActual-amperiosUsados;
    }
    /**
     * Función para aumentar el mayor voltaje alcanzado en la batería de la moto
     * @param aceleracion Float con la aceleración 
     * @param tiempo Float con el tiempo de aceleración
     */
    private void incrementarVoltaje(float aceleracion,float tiempo) {
        voltaje=aceleracion*tiempo*this.kmMotor;
       if(voltaje>this.mayorVoltajeAlcanzado){
           this.mayorVoltajeAlcanzado=voltaje;
       }
    }
    /**
     * Función que indica si cumple las restricciones del BMS
     * @param bms Bms con los datos de su bms
     * @return Boolean que india si cumple las condiciones
     */
    boolean cumpleRestriccionesBMS(BMS bms) {
        boolean lasCumple=true;
        if(refrigeracionActiva && temperatura<bms.getTemperaturaSegura()){
            refrigeracionActiva=false;
        }else{
            if(temperatura>bms.getTemperaturaMax()){
                refrigeracionActiva=true;
                lasCumple=false;
            }
            
        }
        if(frenadaActiva && voltaje<bms.getVoltajeReactivación()){
            frenadaActiva=false;
        }else{
            if(voltaje>bms.getVoltajeMax()){
                frenadaActiva=true;
                lasCumple=false;
            }
        }
        return lasCumple;
    }
    /**
     * Función para refrigerar labatería
     */
    void chequeoRefrigeracion() {
        if(refrigeracionActiva && cargasRefrigerante>0){
            temperatura=temperatura-refrigerante;
            cargasRefrigerante--;
        }
    }
    /**
     * Función para calcular la temperatura segura
     * @return Float con la temperatura segura alcanzada
     */
    float getTemperaturaSegura() {
        float temSec=mayorTemperaturaAlcanzada-Constantes.constanteTemperaturaSeguridad;
        if(temSec<tempIni){
            temSec=tempIni;
        }
        return temSec;
    }
    /**
     * Float con elvoltaje de reactivación
     * @return Float con el voltaje de reactivación
     */
    float getVoltajeReactivacion() {
        float voltReact=mayorVoltajeAlcanzado/Constantes.constanteVoltajeReactivación;
        return voltReact;
    }
}
