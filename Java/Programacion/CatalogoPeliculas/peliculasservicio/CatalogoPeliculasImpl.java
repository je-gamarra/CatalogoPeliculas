package Java.Programacion.CatalogoPeliculas.peliculasservicio;

import Java.Programacion.CatalogoPeliculas.peliculasdatos.AccesoDatosImpl;
import Java.Programacion.CatalogoPeliculas.peliculasdatos.IAccesoDatos;
import Java.Programacion.CatalogoPeliculas.peliculasdomain.Pelicula;
import Java.Programacion.CatalogoPeliculas.peliculasexcepciones.AccesoDatosEx;
import Java.Programacion.CatalogoPeliculas.peliculasexcepciones.LecturaDatosEx;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas {

    private final IAccesoDatos datos; 

    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula); 
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx e) {
            System.out.println("Error de acceso a datos");
            
            e.printStackTrace(System.out);
        } 
        
    }

    @Override
    public void listarPeliculas() {
        try {
            var peliculas = this.datos.listar(NOMBRE_RECURSO);
            for (var pelicula : peliculas) {
                System.out.println("pelicula = " + pelicula);

                
            }
        } catch (LecturaDatosEx e) {
            System.out.println("Error de acceso dato");
            e.printStackTrace(System.out);
        }

        
    }

    @Override
    public void buscarPelicula(String buscar) {

        String resultado = null;
        try {
            resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
        } catch (AccesoDatosEx e) {
            System.out.println("Error de acceso datos");
            e.printStackTrace(System.out);
        }
        System.out.println("resultado = " + resultado);
        
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if(this.datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }
            else{
                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx e) {
           System.out.println("Error al inicuiar catalogo de peliculas");
            e.printStackTrace(System.out);
        }
        
    }
    
}
