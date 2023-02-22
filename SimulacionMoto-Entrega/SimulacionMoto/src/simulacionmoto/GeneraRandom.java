/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionmoto;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Pc
 */
public class GeneraRandom {
    /**
     * Random para la generación de número aleatorios en una distribución uniforme
     */
    Random random;

    /**
     * Constructor de la clase
     */
    public GeneraRandom() {
        this.random=new Random();
    }
    
    /**
     * Función para generar una distancia acelerada y una velocidad aleatoria 
     * @param distanciaSector Float con la distancia del sector
     * @param velocidadActual Float con la velocidad actual de la moto
     * @param velocidadMaximaSector Double con la velocidad máxima calculada del sector
     * @return Lista de Float con la distanciaAcelerada en la primera posición y la velocidad en la segunda posición
     */
    ArrayList<Float> generarAceleracionAleatoria(Float distanciaSector,Float velocidadActual, Double velocidadMaximaSector){
        ArrayList<Float> solucion=new ArrayList();

        //Cálculo de la distancia acelerada(un random con una distribución uniforme [distanciaSector*ConstanteLimite, distanciaSector] ya que interesa que acelere la mayor distancia 
        Float distanciaAcelerada=this.random.nextFloat() * (distanciaSector - distanciaSector*Constantes.constanteLimiteInicialVelocidad) + distanciaSector*0.75f;
        solucion.add(Float.valueOf(distanciaAcelerada.toString()));
        
        //Cálculo de la nueva velocidad,Generamos un random con una distribución uniforme [velocidad actual,velocidad maxima]
        Float velocidad=this.random.nextFloat() * (Float.valueOf(velocidadMaximaSector.toString()) - velocidadActual) + velocidadActual;
        solucion.add(velocidad);
        
        return solucion;
    }
    
    /**
     * Función Función para generar una distancia frenada y una velocidad aleatoria 
     * @param distanciaSector Float con la distancia del sector
     * @param velocidadActual Float con la velocidad actual de la moto
     * @param velocidadMaximaSector Double con la velocidad máxima calculada del sector
     * @return Lista de Float con la distanciaFrenada en la primera posición y la velocidad en la segunda posición
     */
    ArrayList<Float> generarFrenadaAleatoria(Float distanciaSector,Float velocidadActual, Double velocidadMaximaSector){
        ArrayList<Float> solucion=new ArrayList();
        //Cálculo de la distancia frenada mediante un random en una distribución uniforme [0,distanciaSector]
        Float distanciaFrenada=this.random.nextFloat() * (distanciaSector - 0) + 0;
        solucion.add(Float.valueOf(distanciaFrenada.toString()));
        //Cáculo de la velocidad mediante un random en una distribución uniforme [0,velocidadActual]
        Float velocidad=this.random.nextFloat() * (velocidadActual - 0) + 0;
        solucion.add(velocidad);
        
        return solucion;
    }
}