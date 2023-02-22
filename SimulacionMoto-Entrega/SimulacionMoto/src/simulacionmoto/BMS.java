/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;
/**
 *
 * @author ismae
 */
class BMS {
    /**
     * Float con el voltaje máximo de la batería
     */
    Float voltajeMax;
    /**
     * Float con la temperatura máxima de la batería
     */
    Float temperaturaMax;
    /**
     * Float con el voltaje de reactivación de la batería
     */
    Float voltajeReactivación;
    /**
     * Float con la temperatura segura de la batería
     */
    Float temperaturaSegura;
    
    /**
     * Constructor del BMS
     */
    public BMS() {
        this.voltajeMax = 0.0f;
        this.temperaturaMax = 0.0f;
        this.voltajeReactivación = 0.0f;
        this.temperaturaSegura = 0.0f;
    }

    //////////////////////////GETTERS//////////////////////////
    /**
     * Getter del voltajeMax
     * @return Float con el voltajeMax
     */
    public float getVoltajeMax() {
        return voltajeMax;
    }
    /**
     * Getter de la temperaturaMax
     * @return Float con la temperaturaMax
     */
    public float getTemperaturaMax() {
        return temperaturaMax;
    }
    /**
     * Getter del voltajeMaxCelulas
     * @return Float con el voltajeMaxCelulas
     */
    /*public float getVoltajeMaxCelulas() {
        return voltajeMaxCelulas;
    }*/
    /**
     * Getter del voltajeReactivacion
     * @return Float con el voltajeReactivacion
     */
    public float getVoltajeReactivación() {
        return voltajeReactivación;
    }
    /**
     * Getter de la temperaturaSegura
     * @return Float con la temperaturaSegura
     */
    public float getTemperaturaSegura() {
        return temperaturaSegura;
    }

    /////////////////////SETTERS//////////////////////
    /**
     * Setter del voltajeMax
     * @param Float con el voltajeMax 
     */
    public void setVoltajeMax(float voltajeMax) {
        this.voltajeMax = voltajeMax;
    }
    /**
     * Setter de la temperaturaMax
     * @param Float con la temperaturaMax 
     */
    public void setTemperaturaMax(float temperaturaMax) {
        this.temperaturaMax = temperaturaMax;
    }
    /**
     * Setter del voltajeReactivacion
     * @param Float con el voltajeReactivación 
     */
    public void setVoltajeReactivación(float voltajeReactivación) {
        this.voltajeReactivación = voltajeReactivación;
    }
    /**
     * Getter de la temperaturaSegura
     * @param Float con la temperaturaSegura 
     */
    public void setTemperaturaSegura(float temperaturaSegura) {
        this.temperaturaSegura = temperaturaSegura;
    }
    /**
     * Método para convertir a String la información del BMS
     * @return String con los datos
     */
    @Override
    public String toString() {
        return "BMS{" + "voltajeMax=" + voltajeMax + ", temperaturaMax=" + temperaturaMax + ", voltajeReactivaci\u00f3n=" + voltajeReactivación + ", temperaturaSegura=" + temperaturaSegura + '}';
    }
}