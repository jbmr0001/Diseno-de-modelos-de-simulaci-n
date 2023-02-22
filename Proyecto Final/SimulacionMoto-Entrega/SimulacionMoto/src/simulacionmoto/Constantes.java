/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;

/**
 *
 * @author Pc
 */
public interface Constantes {
    /**
     * Constante para la coversión de Km/h a m/s
     */
    public final Double conversionKMHaMS=3.6;
    public final Float constanteLimiteInicialVelocidad=0.75f;
    public final Float porcentajeBateriaBaja=0.05f;
    public final int factorConversionAceleracion=100;
    public final int constanteTemperaturaSeguridad=10;
    public final int constanteVoltajeReactivación=2;
}
