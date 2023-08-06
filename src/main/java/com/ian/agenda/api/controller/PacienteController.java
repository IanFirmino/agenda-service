package com.ian.agenda.api.controller;

import com.ian.agenda.api.mapper.PacienteMapper;
import com.ian.agenda.api.request.PacienteRequest;
import com.ian.agenda.api.response.PacienteResponse;
import com.ian.agenda.domain.entity.Paciente;
import com.ian.agenda.domain.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;
    private final PacienteMapper mapper;

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest pacienteRequest){
        var paciente = mapper.toPaciente(pacienteRequest);
        var pacienteResponse = mapper.toPacienteResponse(service.salvar(paciente));
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarTodos(){
        var pacientes = mapper.listToPacienteResponse(service.listarTodos());
        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> listarPorId(@PathVariable Long id){
        Optional<Paciente> optPaciente = service.buscarPorId(id);
        if(optPaciente.isPresent()){
            var pacienteResponse = mapper.toPacienteResponse(optPaciente.get());
            return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id,@Valid @RequestBody PacienteRequest pacienteRequest){
        var paciente = mapper.toPaciente(pacienteRequest);
        var atualizar = service.atualizar(id, paciente);
        if(atualizar == null){
            return ResponseEntity.notFound().build();
        }
        var atualizarResponse = mapper.toPacienteResponse(atualizar);
        return ResponseEntity.status(HttpStatus.OK).body(atualizarResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }



}
