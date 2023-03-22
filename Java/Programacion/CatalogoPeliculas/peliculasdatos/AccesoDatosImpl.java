package Java.Programacion.CatalogoPeliculas.peliculasdatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Java.Programacion.CatalogoPeliculas.peliculasdomain.Pelicula;
import Java.Programacion.CatalogoPeliculas.peliculasexcepciones.AccesoDatosEx;
import Java.Programacion.CatalogoPeliculas.peliculasexcepciones.EscrituraDatosEx;
import Java.Programacion.CatalogoPeliculas.peliculasexcepciones.LecturaDatosEx;

public class AccesoDatosImpl implements IAccesoDatos{

    @Override
    public boolean existe(String nombreRecurso){
        File archivo = new File(nombreRecurso);
        return archivo.exists();
        
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList<>();//Definimos nuestra variable que va definir nuestra lista de peliculas. y almacena la lista de peliculas, se define el plurar. 
        try (BufferedReader entrada = new BufferedReader(new FileReader(archivo))) { 
            String linea = null; // Definimos la variable para leer cada una de las lineas. 
            linea = entrada.readLine(); 
            while (linea != null){
                var Pelicula  = new Pelicula(linea);// Utilizamos el constructor que estamos leyendo. 
                 peliculas.add(Pelicula);// Por cada linea que leamos en nuestro archivo, esta agregando un objeto de tipo pelicula a nuestra lista. 
                 linea = entrada.readLine(); // Volvemos a leer la linea
            }
            entrada.close();//Aqui cerramos nuestro flujo. 
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();// Captura la excepcion. 
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + e.getMessage()); //Propagamos la excepcion 
        } catch (IOException e) {
            e.printStackTrace();// Captura la excepcion. 
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + e.getMessage());
        }  
        return peliculas;   
    }
     

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {

        var archivo = new File(nombreRecurso);
        try (var salida = new PrintWriter(new FileWriter(archivo, anexar))) {
            salida.println(pelicula.toString());//Mandamos a llamar el objeto para que se imprima
            salida.close();//Cerramos el flujo
            System.out.println("Se ha escrito informacion al archivo: " + pelicula);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir peliculas: " + e.getMessage());
        }  

        
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {

        File archivo = new File(nombreRecurso);
        String resultado = null; 
        try {
            var entrada = new BufferedReader(new FileReader(archivo));// Esta linea arroja una excepcion por lo tanto lo envolvemos en un bloquer try-catch
            String linea = null;
            linea = entrada.readLine();// Este metodo readLine arroja una excepcion. 
            int indice = 1; 
            while(linea != null){
                if(buscar != null && buscar.equalsIgnoreCase(linea)){
                    resultado = "Pelicula " + linea + " encontrada en el indice " + indice; 
                    break; 
                }
                linea = entrada.readLine(); 
                indice++; 

            }
            entrada.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar peliculas: " + e.getMessage());
         
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultado; 
        
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        File archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear archivo: " + e.getMessage());
        } 

        
    }

    @Override
    public void borrar(String nombreRecurso) {
        var archivo = new File(nombreRecurso);
        if(archivo.exists())
        archivo.delete(); 
        System.out.println("Se ha borrado el archivo");
        
    }
    
}
