package com.example.ibm.academia.ruletaREST.services;

import com.example.ibm.academia.ruletaREST.dto.ApuestaDTO;
import com.example.ibm.academia.ruletaREST.entities.Apuesta;
import com.example.ibm.academia.ruletaREST.entities.Ruleta;

import java.util.List;

public interface ApuestaDAO extends GenericoDAO<Apuesta>{

    public List<ApuestaDTO> obtenerApuestaDeRuleta(Integer ruletaId);
    public Apuesta validarApuesta(Double dineroApuesta, Ruleta ruleta, Integer tipoApuesta, String selccionApuesta);
}
