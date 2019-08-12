/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import static EjemploAbstractas.Mostrar.salidaConsola;
import Entorno.Simbolo;
import Entorno.Simbolo.EnumTipoDato;
import static Entorno.Simbolo.EnumTipoDato.CADENA;
import Expresion.Expresion;
import Expresion.Literal;
import java.util.LinkedList;

/**
 *
 * @author miguel
 */
public class Archivo {
    int fila, columna;
    LinkedList<Registro> listaRegistros;
    LinkedList<ItemRegistro> listaClaves;
    
    public Archivo(LinkedList<ItemRegistro> listaClaves, LinkedList<Registro> listaRegistros, int fila, int columna){
        this.listaRegistros = listaRegistros;
        this.listaClaves = listaClaves;
        this.fila = fila;
        this.columna = columna;
    }
    /**
     * @return Retornará un String con las claves separadas por comas y el num de registros
     */
    @Override
    public String toString(){
        String salida = "Claves = [";
        for (int i = 0; i < this.listaClaves.size(); i++) {
            salida = salida + "\""+this.listaClaves.get(i).valor.toString()+"\",";
        }
        salida+="]";
        salida+="\n";
        salida+="Número de registros = "+this.listaRegistros.size();
        salida+="\n";
        return salida;
    }
   
    /** 
     * Devuelve en indice donde se encuentra la clave a usar
     * @param idClave
     * @return 
     */
    public int indiceDeLaClave(String idClave){
        int salida = -1;
        for (int i = 0; i < this.listaClaves.size(); i++) {
            if(this.listaClaves.get(i).tipo == TipoItemRegistro.CADENA){
                /*** Si algun valor de la lista de claves coincide con **idClave**/
                if(idClave.equals(this.listaClaves.get(i).valor.toString())){
                    return i;
                }
            }
        }
        return salida;
    }
    
    /**
     * @return Numero de registros en la lista
     */
    public int contar(){
       return listaRegistros.size();
    }
    /**
     * Suma de acuerdo a una clave dada
     * @param idClave
     * @return 
     */
    public Expresion sumar(String idClave){
        double salida = 0;
        int indiceClave = indiceDeLaClave(idClave);
        if(indiceClave == -1){
            salidaConsola.append("No se encontró la clave "+idClave+" en el archivo.\n");
            return null;
        }
        
        for (int i = 0; i < this.listaRegistros.size(); i++) {
            Registro r = this.listaRegistros.get(i);
            if(indiceClave < r.elementos.size()){
                ItemRegistro ir = r.elementos.get(indiceClave);
                if(ir.tipo == TipoItemRegistro.NUMERO){
                    salida += Double.parseDouble(ir.valor.toString());
                }else{
                    salidaConsola.append("El registro en la filaregistro "+i+" y posicion registro "+indiceClave+" NO es numérico.\n");   
                }
            }else{
                salidaConsola.append("El indice de la clave excede los registros por fila.\n");
            }
        }
        return new Literal(Simbolo.EnumTipoDato.NUMERICO, salida);
    }
    
    public Expresion contarSi(String idClave, TipoRelacional operadorRelacional, Expresion valor){
        double salida = 0;
        int indiceClave = indiceDeLaClave(idClave);
        if(indiceClave == -1){
            salidaConsola.append("No se encontró la clave "+idClave+" en el archivo.\n");
            return null;
        }
        
        for (int i = 0; i < this.listaRegistros.size(); i++) {
            Registro r = this.listaRegistros.get(i);
            if(indiceClave < r.elementos.size()){
                ItemRegistro ir = r.elementos.get(indiceClave);
                    if(comparacionRelacionalyDeTipos(ir, operadorRelacional, valor) != null){
                        salida = salida + 1;    
                    }
            }else{
                salidaConsola.append("El indice de la clave excede los registros por fila.\n");
            }
        }
        return new Literal(Simbolo.EnumTipoDato.NUMERICO, salida);
    }
        
    public String comparacionRelacionalyDeTipos(ItemRegistro ir, TipoRelacional operadorRelacional, Expresion valorExp){
        String salida = null;
        /*** COMPROBACION DE TIPOS ***/
        if(ir.tipo == TipoItemRegistro.NUMERO && valorExp.tipo == EnumTipoDato.NUMERICO){
            /*** COMPARACION RELACIONAL **/
            switch (operadorRelacional) {
                case IGUALIGUAL:
                    if(Double.parseDouble(ir.valor.toString()) == Double.parseDouble(valorExp.valor.toString()))
                        return ir.valor.toString();
                    else
                        return null;
                default:
                    return null;
            }
        }
        else if(ir.tipo == TipoItemRegistro.CADENA && valorExp.tipo == EnumTipoDato.CADENA){
            /*** COMPARACION RELACIONAL **/
            switch (operadorRelacional) {
                case IGUALIGUAL:
                    if(ir.valor.toString().equals(valorExp.valor.toString()))
                        return ir.valor.toString();
                    else
                        return null;
                default:
                    return null;
            }
        }
        return salida;
    }
    
    public enum TipoItemRegistro{
        CADENA,
        NUMERO
    }
    
    public enum TipoRelacional{
        IGUALIGUAL
    }
}
