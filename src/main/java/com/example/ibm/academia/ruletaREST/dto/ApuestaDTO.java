package com.example.ibm.academia.ruletaREST.dto;

import com.example.ibm.academia.ruletaREST.entities.Cliente;
import com.example.ibm.academia.ruletaREST.entities.Ruleta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApuestaDTO {


    private Integer id;

    private Double dinero;

    private String apuestaRealizda;

    private String resultado;

    private String estado;



}
