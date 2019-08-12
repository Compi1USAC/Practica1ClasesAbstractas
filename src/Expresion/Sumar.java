/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;
import Datos.Archivo;
import Datos.Registro;
import Entorno.Entorno;
import Entorno.Simbolo;
import Entorno.Simbolo.EnumTipoDato;
import java.util.LinkedList;
/**
 *
 * @author miguel
 */
public class Sumar extends Expresion{
    public Expresion expId;
    public Expresion expClave;
    
    public Sumar(Expresion expId, Expresion expClave, int fila, int columna){
        this.expId = expId;
        this.expClave = expClave;
        this.fila = fila;
        this.columna = columna;
    }
    @Override
    public Expresion obtenerValor(Entorno ent) {
        Expresion resultadoArchivo = this.expId.obtenerValor(ent);
        Archivo arch = (Archivo)resultadoArchivo.valor;
        
        Expresion resultadoClave = this.expClave.obtenerValor(ent);
        String idClave = resultadoClave.valor.toString();
        
        Expresion suma = arch.sumar(idClave);
        return new Literal(EnumTipoDato.NUMERICO, Double.parseDouble(suma.valor.toString()));
    }

    @Override
    public EnumTipoDato getTipo() {
        return this.tipo;
    }
}
