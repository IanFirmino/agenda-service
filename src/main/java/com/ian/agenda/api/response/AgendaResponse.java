package com.ian.agenda.api.response;

import com.ian.agenda.domain.entity.Paciente;
import java.time.LocalDateTime;

public class AgendaResponse {

    private Long id;
    private String descricao;
    private LocalDateTime horario;
    private LocalDateTime dataCriacao;
    private Paciente Paciente;

}
