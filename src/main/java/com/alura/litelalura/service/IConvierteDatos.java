package com.alura.litelalura.service;


public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);

}