package com.example.ibm.academia.ruletaREST.services;

import com.example.ibm.academia.ruletaREST.entities.Cliente;
import com.example.ibm.academia.ruletaREST.respositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClienteDAOImpl extends GenericoDAOImpl<Cliente, ClienteRepository> implements ClienteDAO {


    public ClienteDAOImpl(@Qualifier("respositorioClientes")ClienteRepository repository) {
        super(repository);
    }

    @Override
    public Cliente actulizarDinero(Integer clienteId, Double apuesta, Integer estadoPuesta) {
        Optional<Cliente> clienteOptional=repository.findById(clienteId);
        Cliente clienteActulizar= clienteOptional.get();
        if (estadoPuesta==1)
            clienteActulizar.setDinero(clienteActulizar.getDinero()+apuesta);
        else
            clienteActulizar.setDinero(clienteActulizar.getDinero()-apuesta);
        clienteActulizar=repository.save(clienteActulizar);
        return clienteActulizar;
    }
}
