package com.example.ibm.academia.ruletaREST.services;

import com.example.ibm.academia.ruletaREST.entities.Apuesta;
import com.example.ibm.academia.ruletaREST.entities.Ruleta;
import com.example.ibm.academia.ruletaREST.exceptions.BadRequestException;
import com.example.ibm.academia.ruletaREST.exceptions.NotFoundException;
import com.example.ibm.academia.ruletaREST.respositories.ApuestaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ApuestaDAOImpl extends GenericoDAOImpl<Apuesta, ApuestaRepository> implements ApuestaDAO {
    public ApuestaDAOImpl(ApuestaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Apuesta> obtenerApuestaDeRuleta(Integer ruletaId) {

        Iterable<Apuesta> apuestasruleta= repository.obtenerApuestaDeRuleta(ruletaId);
            if (((List<Apuesta>)apuestasruleta).isEmpty())
                throw new NotFoundException("No hay apuestas realizadas para la ruleta seleccionada");

        return apuestasruleta;
    }

    @Override
    public Apuesta validarApuesta(Double dineroApuesta, Ruleta ruleta, Integer tipoApuesta, String selccionApuesta) {

        Random random = new Random();
        Integer numeroAleatorio=0;
        Integer nuneroApuesta=0;
        String colorAouesta="";

        if (ruleta.getEstado()!=1)
            throw new BadRequestException("La suleta seleccionada no puede ser utilizada ya que esta cerrada o no ha sido abierta");

        if (dineroApuesta<=0||dineroApuesta>10000)
            throw new BadRequestException("La apuesta tiene que ser un valor mayor a 0 o menor a 10000");

        Apuesta apuesta= new Apuesta();
        apuesta.setDinero(dineroApuesta);
        apuesta.setRuleta(ruleta);


            if (tipoApuesta==1){
                numeroAleatorio=random.ints(0, 36).findFirst().getAsInt();
                nuneroApuesta=Integer.parseInt(selccionApuesta);
                if (nuneroApuesta>=0||nuneroApuesta<=36) {
                    if (numeroAleatorio == nuneroApuesta) {
                        apuesta.setEstado(1);
                    } else {
                        apuesta.setEstado(0);
                    }
                }else
                    throw new BadRequestException("Aapuesta invalida");

                apuesta.setApuestaRealizda(selccionApuesta);
                apuesta.setResultado(String.valueOf(numeroAleatorio));
                apuesta=repository.save(apuesta);

            }else if(tipoApuesta==2){
                numeroAleatorio=random.ints(1, 3).findFirst().getAsInt();
                colorAouesta=selccionApuesta;

                if (numeroAleatorio==1){
                    if(colorAouesta.toUpperCase().equals("ROJO")){
                        apuesta.setEstado(1);
                        apuesta.setResultado("ROJO");
                    }else if (colorAouesta.toUpperCase().equals("NEGRO")){
                        apuesta.setResultado("NEGRO");
                        apuesta.setEstado(0);
                    }else
                        throw new BadRequestException("Aapuesta invalida");

                }else if(numeroAleatorio==2){
                    if (colorAouesta.toUpperCase().equals("NEGRO")){
                        apuesta.setResultado("NEGRO");
                        apuesta.setEstado(1);
                    }else if (colorAouesta.toUpperCase().equals("ROJO")){
                        apuesta.setResultado("ROJO");
                        apuesta.setEstado(0);
                    }else
                        throw new BadRequestException("Aapuesta invalida");

                }else{
                    throw new BadRequestException("Aapuesta invalida");
                }
                apuesta.setApuestaRealizda(colorAouesta.toUpperCase());
                apuesta=repository.save(apuesta);
            }else{
                throw new BadRequestException("Apuesta invalida");
            }
        return apuesta;
    }
}
