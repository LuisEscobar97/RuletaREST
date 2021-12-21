package com.example.ibm.academia.ruletaREST.services;

import com.example.ibm.academia.ruletaREST.entities.Cliente;

public interface ClienteDAO extends GenericoDAO<Cliente>{

    public Cliente actulizarDinero(Integer clienteId, Double apuesta,Integer estadoPuesta);
}
