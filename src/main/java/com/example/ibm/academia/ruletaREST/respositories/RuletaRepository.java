package com.example.ibm.academia.ruletaREST.respositories;

import com.example.ibm.academia.ruletaREST.entities.Ruleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("respositorioRuletas")
public interface RuletaRepository extends CrudRepository<Ruleta,Integer> {



}
