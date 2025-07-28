package com.alura.litelalura.service;

import com.alura.litelalura.dto.AutorDTO;
import com.alura.litelalura.dto.LibroDTO;
import com.alura.litelalura.model.DatosAutor;
import com.alura.litelalura.repository.AutorRepository;
import com.alura.litelalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional
    public List<AutorDTO> listarAutoresDTO() {
        return autorRepository.findAll().stream()
                .map(a -> new AutorDTO(a.getNombre(), a.getNacimiento(), a.getFallecimiento()))
                        .collect(Collectors.toList());
    }

    public List<AutorDTO> listarAutoresVivosEn(int anio) {
        return autorRepository.buscarAutoresVivosEnAnio(anio);
    }
}
