package com.alura.litelalura.repository;

import com.alura.litelalura.dto.AutorDTO;
import com.alura.litelalura.model.DatosAutor;
import com.alura.litelalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository  extends JpaRepository<DatosAutor, Long> {
    @Query("SELECT a FROM DatosAutor a WHERE a.nacimiento <= :anio AND a.fallecimiento >= :anio")
    List<AutorDTO> buscarAutoresVivosEnAnio(@Param("anio") Integer anio);

}
