package com.alura.litelalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaAPI {
    @JsonProperty("results")
    private List<Libro> datosLibros;

    public List<Libro> getDatosLibros() {
        return datosLibros;
    }

    public void setDatosLibros(List<Libro> datosLibros) {
        this.datosLibros = datosLibros;
    }
}