package com.ian.agenda.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PacienteRequest {

    @NotBlank(message = "Campo nome preenchido incorretamente")
    private String nome;
    @NotBlank(message = "Campo sobrenome preenchido incorretamente")
    private String sobrenome;
    @NotBlank(message = "Campo cpf preenchido incorretamente")
    private String cpf;
    private String email;

}
