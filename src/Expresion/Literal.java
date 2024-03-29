/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Entorno.Entorno;
import Entorno.Simbolo.EnumTipoDato;

/**
 *
 * @author miguel
 */
public class Literal extends Expresion{

       /**
     * @param tipo Tipo del literal (Numerico, Cadena)
     * @param valor valor del literal
     */
    public Literal(EnumTipoDato tipo, Object valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        return this; //Retorno la instancia del objeto Expresion
    }

    @Override
    public EnumTipoDato getTipo() {
        return this.tipo;
    }
       
}
