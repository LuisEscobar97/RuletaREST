package com.example.ibm.academia.ruletaREST.services;

import com.example.ibm.academia.ruletaREST.entities.Ruleta;
import com.example.ibm.academia.ruletaREST.exceptions.BadRequestException;
import com.example.ibm.academia.ruletaREST.exceptions.NotFoundException;
import com.example.ibm.academia.ruletaREST.respositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuletaDAOImpl extends GenericoDAOImpl<Ruleta, RuletaRepository> implements RuletaDAO{


    public RuletaDAOImpl(@Qualifier("respositorioRuletas")RuletaRepository repository) {
        super(repository);
    }

    @Override
    public Ruleta aperturaRuleta(Ruleta ruleta) {

        Ruleta ruletaAbierta=ruleta;

        if (ruletaAbierta.getEstado()==0)
            throw new BadRequestException("La ruleta seleccionada ya fue cerrada y no se puede dar apuertura de nuevo");
        else{
        ruletaAbierta.setEstado(1);
        ruletaAbierta=repository.save(ruletaAbierta);
        }
        return ruletaAbierta;
    }

    @Override
    public Ruleta cierreRuleta(Ruleta ruleta) {


        Ruleta ruletaAbierta=ruleta;

        if (ruletaAbierta.getEstado()!=1 ||ruletaAbierta.getEstado()!=0)
            throw new BadRequestException("La ruleta seleccionada no ha sido abierta aun y no se puede cerrar");
        else{
            ruletaAbierta.setEstado(0);
            ruletaAbierta=repository.save(ruletaAbierta);
        }
        return ruletaAbierta;
    }

    @Override
    public Integer guadrar(Ruleta ruleta) {

        Ruleta ruletaGudarda= repository.save(ruleta);

        return ruletaGudarda.getId();
    }
}
