package com.ian.agenda.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name= "paciente")
@Entity
@Data
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "nome")
    private String nome;
    @Column(name= "sobrenome")
    private String sobrenome;
    @Column(name= "cpf")
    private String cpf;
    @Column(name= "email")
    private String email;


}
