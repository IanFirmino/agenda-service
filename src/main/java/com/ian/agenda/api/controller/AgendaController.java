package com.ian.agenda.api.controller;

import com.ian.agenda.api.mapper.AgendaMapper;
import com.ian.agenda.api.request.AgendaRequest;
import com.ian.agenda.api.response.AgendaResponse;
import com.ian.agenda.domain.entity.Agenda;
import com.ian.agenda.domain.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agenda")
public class AgendaController {

    private final AgendaService service;
    private final AgendaMapper agendaMapper;

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@RequestBody AgendaRequest agendaRequest){
        var agenda = agendaMapper.toAgenda(agendaRequest);
        var agendaResponse = agendaMapper.toAgendaResponse(service.salvar(agenda));
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> listarTodos(){
        var agendas = service.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(agendaMapper.listToAgendaResponse(agendas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> listarPorId(@PathVariable Long id){
        Optional<Agenda> agenda = service.buscarPorId(id);
        if(agenda.isPresent()){
            var agendaResponse = agendaMapper.toAgendaResponse(agenda.get());
            return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaResponse> atualizar(@PathVariable Long id, @RequestBody AgendaRequest agendaRequest){
        var atualizar = service.atualizar(id, agendaMapper.toAgenda(agendaRequest));
        if (atualizar == null){
            ResponseEntity.notFound().build();
        }
        var agendaResponse = agendaMapper.toAgendaResponse(atualizar);
        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
