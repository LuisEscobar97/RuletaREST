package com.example.ibm.academia.ruletaREST.services;

import com.example.ibm.academia.ruletaREST.exceptions.NotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class GenericoDAOImpl <E,R extends CrudRepository<E,Integer>> implements  GenericoDAO<E> {

    protected final R repository;

    public GenericoDAOImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> buscarPorID(Integer id) {

        Optional<E> objetoEncontrado=repository.findById(id);
        if (!objetoEncontrado.isPresent())
            throw new NotFoundException(String.format("El registro buscado con ID: %d no existe en la base de datos",id));

        return objetoEncontrado;
    }

    @Override
    public E guardar(E entidad) {
        return repository.save(entidad);
    }

    @Override
    public Iterable<E> buscarTodos() {
        return repository.findAll();
    }
}
