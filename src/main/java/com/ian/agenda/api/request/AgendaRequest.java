package com.ian.agenda.api.request;

import com.ian.agenda.domain.entity.Paciente;
import java.time.LocalDateTime;

public class AgendaRequest {

    private String descricao;
    private LocalDateTime horario;
    private LocalDateTime dataCriacao;
    private Paciente Paciente;

}
