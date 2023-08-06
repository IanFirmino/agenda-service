package com.ian.agenda.api.mapper;

import com.ian.agenda.api.request.AgendaRequest;
import com.ian.agenda.api.response.AgendaResponse;
import com.ian.agenda.domain.entity.Agenda;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    private final ModelMapper modelMapper;

    public Agenda toAgenda(AgendaRequest agendaRequest){
        return modelMapper.map(agendaRequest, Agenda.class);
    }

    public AgendaResponse toAgendaResponse(Agenda agenda){
        return modelMapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> listToAgendaResponse(List<Agenda> agendas){
        var agendasResponse = agendas
                .stream()
                .map(agenda -> toAgendaResponse(agenda))
                .collect(Collectors.toList());

        return agendasResponse;
    }

}
