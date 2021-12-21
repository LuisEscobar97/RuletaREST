package com.example.ibm.academia.ruletaREST.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dinero",nullable = false)
    @Positive(message = "de ser positivo")
    @NotNull(message = "no debe ser nullo")
    private Double dinero;

    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "cliente")
    private List<Apuesta>apuestas;

    public Cliente(Integer id, Double dinero) {
        this.id = id;
        this.dinero = dinero;
    }

    @PrePersist
    private void antesPersistir(){
        this.fechaAlta=new Date();
    }

    @PreUpdate
    private void antesActualizar(){
        this.fechaModificacion=new Date();
    }

}
