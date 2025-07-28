package com.alura.litelalura.repository;

import com.alura.litelalura.model.DatosAutor;
import com.alura.litelalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository  extends JpaRepository<Libro, Long> {


}
