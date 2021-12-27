package com.example.ibm.academia.ruletaREST.respositories;

import com.example.ibm.academia.ruletaREST.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("respositorioClientes")
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {


}
