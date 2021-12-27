package com.example.ibm.academia.ruletaREST.respositories;

import com.example.ibm.academia.ruletaREST.entities.Apuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("respositorioApuestas")
public interface ApuestaRepository extends CrudRepository<Apuesta,Integer> {

    @Query("Select a from Apuesta a where a.ruleta.id=:parametroRuletaId")
    public Iterable<Apuesta> obtenerApuestaDeRuleta(@Param("parametroRuletaId")Integer ruletaId);

}
