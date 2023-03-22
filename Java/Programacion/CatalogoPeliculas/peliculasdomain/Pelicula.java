package Java.Programacion.CatalogoPeliculas.peliculasdomain;

public class Pelicula {
    //Aplicamos el concepto de JavaBeans
    private String nombre;

    public Pelicula(){

    }

    public Pelicula(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre; //Retorna el nombre de la pelicula. 
    } 
    
}
