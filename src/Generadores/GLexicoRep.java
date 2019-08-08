package Generadores;

import java.io.File;

public class GLexicoRep 
{
    public static void main(String[] args) 
    {
        String path="src/Analizadores/a_Lexico_rep.jflex";
        generarLexer(path);
    } 
    
    public static void generarLexer(String path)
    {
        File file=new File(path);
        jflex.Main.generate(file);
    } 
}
