package com.example.ibm.academia.ruletaREST.services;

import com.example.ibm.academia.ruletaREST.entities.Apuesta;
import com.example.ibm.academia.ruletaREST.entities.Ruleta;

public interface ApuestaDAO extends GenericoDAO<Apuesta>{

    public Iterable<Apuesta> obtenerApuestaDeRuleta(Integer ruletaId);
    public Apuesta validarApuesta(Double dineroApuesta, Ruleta ruleta, Integer tipoApuesta, String selccionApuesta);
}
