package com.alura.litelalura.service;

import com.alura.litelalura.dto.AutorDTO;
import com.alura.litelalura.dto.LibroDTO;
import com.alura.litelalura.model.DatosAutor;
import com.alura.litelalura.model.Libro;
import com.alura.litelalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }


    @Transactional
    public List<LibroDTO> listarLibrosDTO() {
        return libroRepository.findAll().stream()
                .map(libro -> new LibroDTO(
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAutor().stream()
                                .map(DatosAutor::getNombre)
                                .collect(Collectors.toList()),
                        libro.getIdiomas(),
                        libro.getNumeroDeDescargas()
                ))
                .collect(Collectors.toList());
    }


    public void guardarLibro(Libro libro) {
        libro.getAutor().forEach(autor -> autor.setLibro(libro));
        libroRepository.save(libro);
    }
}
