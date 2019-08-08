/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Datos.Archivo;
import Entorno.Entorno;
import Entorno.Simbolo;
import Entorno.Simbolo.EnumTipoDato;

/**
 *
 * @author miguel
 */
public class Contar extends Expresion{
    public Expresion exp;
    
    public Contar(Expresion exp){
        this.exp = exp;
    }
    @Override
    public Expresion obtenerValor(Entorno ent) {
        System.out.println("Haciendo contar");
        Expresion resultado = exp.obtenerValor(ent);
        Archivo arch = (Archivo)resultado.valor;
        return new Literal(EnumTipoDato.NUMERICO, arch.contar());
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        return this.tipo;
    }
    
}
