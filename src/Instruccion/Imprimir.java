/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instruccion;

import static EjemploAbstractas.Mostrar.salidaConsola;
import Entorno.Entorno;
import Entorno.Simbolo.EnumTipoDato;
import Expresion.Expresion;
import java.util.LinkedList;

/**
 *
 * @author miguel
 */
public class Imprimir extends Instruccion{
    Expresion expresion;
    
    public Imprimir(Expresion expresion, int fila, int columna) {
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public void ejecutar(Entorno ent) {
        System.out.println("Ejecutando la instrucción Imprimir");
        Expresion resultado = this.expresion.obtenerValor(ent);
        if(resultado.getTipo() == EnumTipoDato.ERROR){
            System.out.println("La expresion es un error :(");
            return;
        }
        System.out.println(String.valueOf(resultado.valor));
        salidaConsola.append(String.valueOf(resultado.valor));
    }

    @Override
    public EnumTipoIns getTipo() {
        return this.tipo;
    }
    
}
