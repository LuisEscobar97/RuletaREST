package com.example.ibm.academia.ruletaREST.services;

import com.example.ibm.academia.ruletaREST.entities.Apuesta;
import com.example.ibm.academia.ruletaREST.entities.Ruleta;
import com.example.ibm.academia.ruletaREST.exceptions.BadRequestException;
import com.example.ibm.academia.ruletaREST.exceptions.NotFoundException;
import com.example.ibm.academia.ruletaREST.respositories.RuletaRepository;
import org.dom4j.rule.Rule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
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
            return repository.save(ruletaAbierta);
        }

    }

    @Override
    public Ruleta cierreRuleta(Ruleta ruleta) {

        Ruleta ruletaAbierta=ruleta;

        if (ruletaAbierta.getEstado()==1) {
            ruletaAbierta.setEstado(0);
            return repository.save(ruletaAbierta);
        }else if(ruletaAbierta.getEstado()==0)
            throw new BadRequestException("La ruleta seleccionada ya ha sido cerrada");
        else
            throw new BadRequestException("La ruleta seleccionada no se puede cerrar ya que aun no ha sido abierta");

    }

    @Override
    public Integer guadrar(Ruleta ruleta) {

        Ruleta ruletaGudarda= repository.save(ruleta);

        return ruletaGudarda.getId();
    }

    @Override
    public List<Ruleta> buscarTodosRuletas() {
    List<Ruleta> ruletas =(List<Ruleta>)repository.findAll();
    if (ruletas.isEmpty())
        throw new NotFoundException("No hay suletas dadas de alta en la base de datos");

        return ruletas ;
    }


}
