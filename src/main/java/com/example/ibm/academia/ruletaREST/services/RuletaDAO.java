package com.example.ibm.academia.ruletaREST.services;

import com.example.ibm.academia.ruletaREST.entities.Ruleta;

public interface RuletaDAO extends GenericoDAO<Ruleta>{

    public Ruleta aperturaRuleta(Ruleta ruleta);

    public Ruleta cierreRuleta(Ruleta ruleta);

    public Integer guadrar(Ruleta ruleta);
}
