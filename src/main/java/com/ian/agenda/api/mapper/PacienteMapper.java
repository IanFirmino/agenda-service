package com.ian.agenda.api.mapper;

import com.ian.agenda.api.request.PacienteRequest;
import com.ian.agenda.api.response.PacienteResponse;
import com.ian.agenda.domain.entity.Paciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper modelMapper;

    public Paciente toPaciente(PacienteRequest pacienteRequest){
        return modelMapper.map(pacienteRequest, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente){
        return modelMapper.map(paciente, PacienteResponse.class);
    }

    public List<PacienteResponse> listToPacienteResponse(List<Paciente> pacientes){
        var pacientesResponse = pacientes
                .stream()
                .map(paciente -> toPacienteResponse(paciente))
                .collect(Collectors.toList());

        return pacientesResponse;
    }

}
