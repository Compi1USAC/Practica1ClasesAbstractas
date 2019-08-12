/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expresion;

import Analizadores.a_Lexico_datos;
import Analizadores.analisis_sintactico_datos;
import Datos.Archivo;
import static EjemploAbstractas.Mostrar.salidaConsola;
import Entorno.Entorno;
import Entorno.Simbolo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

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
        /** LEER CONTENIDO DEL ARCHIVO **/
        String datos;
        try {
            datos = leerArchivo(resultadoNombreArchivo.valor.toString());
        } catch (IOException ex) {
            salidaConsola.append("No se pudo abrir el archivo..."+resultadoNombreArchivo.valor.toString()+"\n");
            return new Literal(Simbolo.EnumTipoDato.ERROR, "%ERROR%");
        }
        /** HACER EL ARBOL DEL ARCHIVO **/
        Archivo arbol = obtenerArbolDeArchivo(datos);
        if(arbol != null){
            return new Literal(Simbolo.EnumTipoDato.ARCHIVO, arbol);
        }
        return new Literal(Simbolo.EnumTipoDato.ERROR, "%ERROR%");
    }
    /***
     * FUncion que lee el contenido de un archivo
     * @param nombreArchivo el nombre del archivo y su ruta
     * @return el contenido en String
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String leerArchivo(String nombreArchivo) throws FileNotFoundException, IOException{
        String datos = "";
        File file = new File(nombreArchivo); 
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        String st; 
        while ((st = br.readLine()) != null)
          datos = datos + st + "\n";
        return datos;
    }
    
    public Archivo obtenerArbolDeArchivo(String datos){
        Archivo arbol;
        a_Lexico_datos lexico = new a_Lexico_datos(new BufferedReader(new StringReader(datos)));
        analisis_sintactico_datos sintactico = new analisis_sintactico_datos(lexico);
        try{
            sintactico.parse();
            arbol = sintactico.resultado;
            return arbol;
        }catch(Exception e){
            salidaConsola.append("No se generó el árbol correctamente\n");
            System.out.println(e);
        }
        return null;
    }
    
    @Override
    public Simbolo.EnumTipoDato getTipo() {
        return this.tipo;
    }    
}
