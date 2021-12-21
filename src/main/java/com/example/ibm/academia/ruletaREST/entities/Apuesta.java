package com.example.ibm.academia.ruletaREST.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apuestas")
public class Apuesta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dinero",nullable = false)
    @Positive(message = "no puede ser negativo")
    private Double dinero;

    @Column(name = "apuesta_realizada",nullable = false)
    @NotNull(message = "no puede ser nullo")
    @NotEmpty(message = "No puede ser vacio")
    private String apuestaRealizda;

    @Column(name = "resultado",nullable = false)
    @NotNull(message = "no puede ser nullo")
    @NotEmpty(message = "No puede ser vacio")
    private String resultado;

    @Column(name = "estado",nullable = false)
    @Positive(message = "no puede ser negativo")
    private Integer estado;

    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id",foreignKey = @ForeignKey(name = "FK_CLIENTE_ID"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "apuestas"})
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "ruleta_id",foreignKey = @ForeignKey(name = "FK_RULETA_ID"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "apuestas"})
    private Ruleta ruleta;

    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    public Apuesta(Integer id, Double dinero, Integer estado) {
        this.id = id;
        this.dinero = dinero;
        this.estado = estado;
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
