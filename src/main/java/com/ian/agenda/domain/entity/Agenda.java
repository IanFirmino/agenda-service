package com.ian.agenda.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name= "agenda")
@Entity
@Data
public class Agenda {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "descricao")
    private String descricao;
    @Column(name= "data_hora")
    private LocalDateTime horario;
    @Column(name= "data_criacao")
    private LocalDateTime dataCriacao;
    @ManyToOne
    private Paciente Paciente;

}
