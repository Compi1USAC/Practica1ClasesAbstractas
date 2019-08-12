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
    LinkedList<Expresion> expresiones;
    
    public Imprimir(LinkedList<Expresion> expresiones, int fila, int columna) {
        this.expresiones = expresiones;
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public void ejecutar(Entorno ent) {
        System.out.println("Ejecutando la instrucción Imprimir");
        String salida = "";
        for (int i = 0; i < this.expresiones.size(); i++) {
         Expresion resultado = this.expresiones.get(i).obtenerValor(ent);
            if(resultado.getTipo() == EnumTipoDato.ERROR){
                System.out.println("La expresion es un error :(");
                return;
            }else{
                salida += resultado.valor.toString();
            }
        }
        System.out.println(salida);
        salidaConsola.append(salida+"\n");
    }

    @Override
    public EnumTipoIns getTipo() {
        return this.tipo;
    }
    
}
