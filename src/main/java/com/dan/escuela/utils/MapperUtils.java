package com.dan.escuela.utils;

import java.util.function.Function;

public class MapperUtils {
    public static <T, E, D> D entidadAObjetoDato(T entidad, Function<T, E> obtenerRelacion, Function<E, D> mapper) {
        if (entidad == null) return null;

        E objeto = obtenerRelacion.apply(entidad);

        return objeto != null ? mapper.apply(objeto) : null;
    }
}
