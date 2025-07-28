package com.alura.litelalura.principal;

import com.alura.litelalura.model.Libro;
import com.alura.litelalura.model.RespuestaAPI;
import com.alura.litelalura.repository.LibroRepository;
import com.alura.litelalura.service.AutorService;
import com.alura.litelalura.service.ConsumoAPI;
import com.alura.litelalura.service.ConvierteDatos;
import com.alura.litelalura.service.LibroService;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private final LibroService libroService;
    private final AutorService autorService;
    private final ConvierteDatos convierteDatos = new ConvierteDatos();
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final Scanner lectura = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books/";

    public Principal(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("Elija la opción a través de su número:");
            System.out.println("1- buscar libro por título");
            System.out.println("2- listar libros registrados");
            System.out.println("3- listar autores registrados");
            System.out.println("4- listar autores vivos en un determinado año");
            System.out.println("0- salir");

            opcion = lectura.nextInt();
            lectura.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroYRegistrar();
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
                case 4:
                    mostrarAutoresVivosEnAnio();
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }




    private void buscarLibroYRegistrar() {
        var datos = getDatosSerie();
        Libro libroObtenido = datos.getDatosLibros().get(0);

        System.out.println("Libro encontrado:");
        System.out.println("Título: " + libroObtenido.getTitulo());
        System.out.println("Idioma: " + libroObtenido.getIdiomas());
        System.out.println("Número de descargas: " + libroObtenido.getNumeroDeDescargas());
        System.out.println("Autores: " + libroObtenido.getAutor().toString());


        try {
            libroService.guardarLibro(libroObtenido);
            System.out.println("Libro guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar el libro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private RespuestaAPI getDatosSerie() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = lectura.nextLine();
        final String urlConBusqueda = URL_BASE + "?search=" + nombreLibro.replace(" ", "%20");

        var jsonFiltrado = consumoAPI.obtenerDatos(urlConBusqueda);
        return convierteDatos.obtenerDatos(jsonFiltrado, RespuestaAPI.class);
    }

    private void mostrarLibros() {
        var libros = libroService.listarLibrosDTO();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(libro -> {
                System.out.println("────────────────────────────");
                System.out.println("Titulo: " + libro.titulo());
                System.out.println("Idiomas: " + libro.idiomas());
                System.out.println("Descargas: " + libro.numeroDescargas());
                System.out.println("Autores:");
                libro.autores().forEach(autor ->
                        System.out.println("   • " + autor));
            });
        }
    }

    private void mostrarAutores() {
        var autores = autorService.listarAutoresDTO();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(autor -> {
                System.out.println("────────────────────────────");
                System.out.println("Nombre: " + autor.nombre());
                System.out.println("Fecha de nacimiento: " + autor.nacimiento());
                System.out.println("Fecha de fallecimiento: " + autor.fallecimiento());
            });
        }
    }

    private void mostrarAutoresVivosEnAnio() {
        System.out.print("Ingrese el año: ");
        int anio = lectura.nextInt();

        var autores = autorService.listarAutoresVivosEn(anio);

        System.out.println("Autores vivos en el año " + anio + ":");
        autores.forEach(autor -> {
            System.out.println("────────────────────────────");
            System.out.println("Nombre: " + autor.nombre());
            System.out.println("Nacimiento: " + autor.nacimiento());
            System.out.println("Fallecimiento: " + autor.fallecimiento());
        });
    }



}
