/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Entorno.Entorno;
import Entorno.Simbolo;
import java.io.File;

/**
 *
 * @author miguel
 */
public class FuncionLeerArchivo extends Expresion{
    public Expresion expRuta;
    /**
     * @param expRuta Expresión que contiene la ruta del archivo a analizar
     * @param fila Línea donde está la función leerArchivo
     * @param columna Columna donde está la función leerArchivo
     */
    public FuncionLeerArchivo(Expresion expRuta, int fila, int columna) {
        this.expRuta = expRuta;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Expresion obtenerValor(Entorno ent) {
        Expresion resultadoNombreArchivo = this.expRuta.obtenerValor(ent);
        return new Literal(Simbolo.EnumTipoDato.LEERARCHIVO,
                        "Aquí debe imprimir el contenido del archivo llamado: "+resultadoNombreArchivo.valor.toString());
    }

    @Override
    public Simbolo.EnumTipoDato getTipo() {
        return this.tipo;
    }    
}
