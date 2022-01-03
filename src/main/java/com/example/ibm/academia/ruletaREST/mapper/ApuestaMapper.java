package com.example.ibm.academia.ruletaREST.mapper;

import com.example.ibm.academia.ruletaREST.dto.ApuestaDTO;
import com.example.ibm.academia.ruletaREST.entities.Apuesta;

public class ApuestaMapper {
    public static ApuestaDTO mapApuesta(Apuesta apuesta){
        ApuestaDTO apuestaDTO= new ApuestaDTO();
        apuestaDTO.setApuestaRealizda(apuesta.getApuestaRealizda());
        apuestaDTO.setResultado(apuesta.getResultado());
        apuestaDTO.setDinero(apuesta.getDinero());
        apuestaDTO.setId(apuesta.getId());
        if(apuesta.getEstado()==0)
            apuestaDTO.setEstado("Perdida");
        else
            apuestaDTO.setEstado("Ganada");

        return apuestaDTO;
    }
}
