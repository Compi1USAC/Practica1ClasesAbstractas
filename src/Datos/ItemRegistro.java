/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.Archivo.TipoItemRegistro;

/**
 *
 * @author miguel
 */
public class ItemRegistro {
    int fila, columna;
    Object valor;
    TipoItemRegistro tipo;
    
    public ItemRegistro(TipoItemRegistro tipo, Object valor, int fila, int columna){
        this.tipo = tipo;
        this.valor =valor;
        this.fila = fila;
        this.columna = columna;
    }
    
    
   
}
