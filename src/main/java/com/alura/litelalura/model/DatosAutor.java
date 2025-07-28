package com.alura.litelalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosAutor {

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("birth_year")
    @Column(name = "anio_nacimiento")
    private Integer nacimiento;

    @JsonProperty("death_year")
    @Column(name = "anio_muerte")
    private Integer fallecimiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return nacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.nacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return fallecimiento;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.fallecimiento = anioMuerte;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}