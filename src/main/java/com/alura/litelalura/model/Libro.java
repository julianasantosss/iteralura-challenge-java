package com.alura.litelalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {
    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    @Column(name = "titulo")
    private String titulo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "idiomas_libro", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    @JsonProperty("languages")
    private List<String> idiomas;

    @JsonProperty("download_count")
    @Column(name = "numero_de_descargas")
    private Double numeroDeDescargas;

    @JsonProperty("authors")
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DatosAutor> autores = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<DatosAutor> getAutor() {
        return autores;
    }

    public void setAutor(List<DatosAutor> autor) {
        this.autores = autor;
    }
}