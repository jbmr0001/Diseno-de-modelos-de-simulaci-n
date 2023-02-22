/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;

import java.util.ArrayList;

/**
 *
 * @author ismae
 */
class RestriccionesMotoYBMS {
    /**
     * Float con el voltaje máximo que puede alcanzar la batería
     */
    public Float volt_max_bateria;
    /**
     * Float con el voltaje máximo que puede alcanzar las celdas de la batería
     */
    public Float volt_max_celulas;
    /**
     * Float con la intensidad de la batería
     */
    public Float intensidad;
    /**
     * Float con la velocidad máxima de la moto
     */
    public Float velocidad_max;
    /**
     * Float con la potencia del motor
     */
    public Float potencia_motor;
    /**
     * Float con la temperatura máxima alcanzable por la batería
     */
    public Float temperatura_max;
    /**
     * Float con el peso de la moto
     */
    public Float peso;
    /**
     * Float con la capacidad máxima de la batería
     */
    public Float capaciadBateria;
    /**
     * Float con la temperatura inicial de la batería
     */
    public Float tem_inicial;
    /**
     * Float con el número de cargas refrigerantes
     */
    public  Float cargasRefigerante;
    /**
     * Float con la temperatura que se resta al refrigerar
     */
    public Float fuerzaRefrigerante;
    /**
     * Float con la aceleración lateral máxima del vehiculo
     */
    public Float aceleracionLateral;
    /**
     * Constructor de la clase
     */
    public RestriccionesMotoYBMS() {
        this.volt_max_bateria = 0f;
        this.volt_max_celulas = 0f;
        this.intensidad = 0f;
        this.velocidad_max = 0f;
        this.potencia_motor = 48f;
        this.temperatura_max = 0f;
        this.peso = 0f;
        this.capaciadBateria = 200f;
        this.tem_inicial=0f;
        this.cargasRefigerante=0f;
        this.fuerzaRefrigerante=0f;
        this.aceleracionLateral=0.0f;
    }

    ////////////////////////GETTERS//////////////////////////
    /**
     * Getter del voltaje maximo bateria
     * @return Float con el voltaje maximo batería
     */
    public Float getVolt_max_bateria() {
        return volt_max_bateria;
    }
    /**
     * Getter del voltaje máximo celulas
     * @return Getter con el voltaje máximo celulas
     */
    public Float getVolt_max_celulas() {
        return volt_max_celulas;
    }
    /**
     * Getter de la intensidad
     * @return Float con la intensidad
     */
    public Float getIntensidad() {
        return intensidad;
    }
    /**
     * Getter de la velocidad maxima de la moto
     * @return Float con la velocidad máxima de la moto
     */
    public Float getVelocidad_max() {
        return velocidad_max;
    }
    /**
     * Getter de la potencia del motor
     * @return Float con la potencia máxima del motor
     */
    public Float getPotencia_motor() {
        return potencia_motor;
    }
    /**
     * Getter de la temperatura máxima alcanzable
     * @return Float con la temperatura máxima
     */
    public Float getTemperatura_max() {
        return temperatura_max;
    }
    /**
     * Getter del peso
     * @return Float con el peso
     */
    public Float getPeso() {
        return peso;
    }
    /**
     * Getter de la capacidad de la batería
     * @return Float con la capacidad de la batería
     */
    public Float getCapacidadBateria() {
        return capaciadBateria;
    }
    /**
     * Getter de la tempertura inicial
     * @return Float con la temperatura inicial
     */
    public Float getTem_inicial() {
        return tem_inicial;
    }
    /**
     * Getter de la aceleración lateral
     * @return Float con la aceleración lateral
     */
    public Float getAceleracionLateral() {
        return aceleracionLateral;
    }
    /**
     * Getter de las cargas refrigerantes
     * @return Float con la capacidad de refrigerado
     */
    Float getCapacidadEnfriado() {
        return this.cargasRefigerante;
    }
    /**
     * Getter de la fuerza de enfriado
     * @return Float con la fuerza de refrigerado
     */
    float getFuerzaEnfriado() {
        return this.fuerzaRefrigerante;
    }
    //////////////////////////SETTERS/////////////////////////////
    /**
     * Setter del voltaje maximo bateria
     * @param volt_max_bateria Float con el voltaje maximo batería
     */
    public void setVolt_max_bateria(Float volt_max_bateria) {
        this.volt_max_bateria = volt_max_bateria;
    }
    /**
     * Setter del voltaje maximo celulas batería
     * @param volt_max_celulas Float con el voltaje máximo de las celulas
     */
    public void setVolt_max_celulas(Float volt_max_celulas) {
        this.volt_max_celulas = volt_max_celulas;
    }
    /**
     * Setter de la intensidad
     * @param intensidad Float con la intensidad
     */
    public void setIntensidad(Float intensidad) {
        this.intensidad = intensidad;
    }
    /**
     * Setter de la velocidad máxima
     * @param velocidad_max Float con
     */
    public void setVelocidad_max(Float velocidad_max) {
        this.velocidad_max = velocidad_max;
    }
    /**
     * Setter de la potencia del motor
     * @param potencia_motor Float con la potencia del motor
     */
    public void setPotencia_motor(Float potencia_motor) {
        this.potencia_motor = potencia_motor;
    }
    /**
     * Setter de la temperatura maxima de la batería
     * @param temperatura_max Float con la batería maxima
     */
    public void setTemperatura_max(Float temperatura_max) {
        this.temperatura_max = temperatura_max;
    }
    /**
     * Setter del peso
     * @param peso Float con el peso
     */
    public void setPeso(Float peso) {
        this.peso = peso;
    }
    /**
     * Setter de la capacidad
     * @param capacidad Float con la capacidad
     */
    public void setCapacidad(Float capacidad) {
        this.capaciadBateria = capacidad;
    }
    /**
     * Setter de la temperatura inicial
     * @param tem_inicial Float con la temperatura inicial
     */
    public void setTem_inicial(Float tem_inicial) {
        this.tem_inicial = tem_inicial;
    }
    /**
     * Setter de la aceleración lateral
     * @param aceleracionLateral Float con la aceleración lateral máxima
     */
    public void setAceleracionLateral(Float aceleracionLateral) {
        this.aceleracionLateral = aceleracionLateral;
    }
    /**
     * Setter de las cargasRefrigerantes
     * @param cargasRefigerante Int con el número de cargas refrigerantes
     */
    public void setCargasRefigerante(Float cargasRefigerante) {
        this.cargasRefigerante = cargasRefigerante;
    }
    /**
     * Setter de la fuerza refrigerante
     * @param fuerzaRefirgerante Float con la carga refrigerante
     */
    public void setFuerzaRefirgerante(Float fuerzaRefirgerante) {
        this.fuerzaRefrigerante = fuerzaRefirgerante;
    }
    /**
     * Método to string
     * @return String con la información
     */
    @Override
    public String toString() {
        return "RestriccionesMotoYBMS{" + "volt_max_bateria=" + volt_max_bateria + ", volt_max_celulas=" + volt_max_celulas + ", intensidad=" + intensidad + ", velocidad_max=" + velocidad_max + ", potencia_motor=" + potencia_motor + ", temperatura_max=" + temperatura_max + ", peso=" + peso + ", capaciadBateria=" + capaciadBateria + ", tem_inicial=" + tem_inicial + ", cargasRefigerante=" + cargasRefigerante + ", fuerzaRefirgerante=" + fuerzaRefrigerante + ", aceleracionLateral=" + aceleracionLateral + '}';
    }
    
}