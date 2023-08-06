package com.ian.agenda.domain.service;

import com.ian.agenda.domain.entity.Agenda;
import com.ian.agenda.domain.entity.Paciente;
import com.ian.agenda.domain.repository.AgendaRepository;
import com.ian.agenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository repository;
    private final PacienteService pacienteService;

    public Agenda salvar(Agenda a) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(a.getPaciente().getId());
        if (paciente.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }
        if (repository.findByHorario(a.getHorario()).isPresent()) {
            throw new BusinessException("Há um agendamento reservado para este horário");
        }
        a.setPaciente(paciente.get());
        a.setDataCriacao(LocalDateTime.now());
        return repository.save(a);
    }

    public List<Agenda> listarTodos() {
        return repository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Agenda atualizar(Long id, Agenda a) {
        Optional<Agenda> agenda = buscarPorId(id);
        if (agenda.isPresent()) {
            a.setId(agenda.get().getId());
            return salvar(a);
        }
        throw new BusinessException("Agenda não encontrada");
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
