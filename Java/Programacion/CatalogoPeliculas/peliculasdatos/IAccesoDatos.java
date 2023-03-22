package Java.Programacion.CatalogoPeliculas.peliculasdatos;

import java.util.List;

import Java.Programacion.CatalogoPeliculas.peliculasdomain.Pelicula;
import Java.Programacion.CatalogoPeliculas.peliculasexcepciones.*;
import Java.Programacion.CatalogoPeliculas.peliculasexcepciones.*;

public interface IAccesoDatos {
    boolean existe(String nombreRecurso) throws AccesoDatosEx;
    
    List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx;

    void escribir(Pelicula pelicula, String nombreRecurso,boolean anexar) throws EscrituraDatosEx;

    String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx;

    void crear(String nombreRecurso) throws AccesoDatosEx;

    void borrar(String nombreRecurso) throws AccesoDatosEx; 

    
}
