package com.ian.agenda.domain.service;

import com.ian.agenda.domain.entity.Paciente;
import com.ian.agenda.domain.repository.PacienteRepository;
import com.ian.agenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente p){

        var cpfExistesnte = repository.findByCpf(p.getCpf());
        if(cpfExistesnte.isPresent()){
            throw new BusinessException("CPF Já cadastrado: " + cpfExistesnte.get().getCpf());
        }

        return repository.save(p);
    }

    public List<Paciente> listarTodos(){
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id){
        return repository.findById(id);
    }

    public Paciente atualizar(Long id, Paciente paciente){
        var pacienteAtualizar = buscarPorId(id);
        if(pacienteAtualizar.isPresent()){
            paciente.setId(pacienteAtualizar.get().getId());
            return salvar(paciente);
        }
        throw new BusinessException("Paciente não encontrado");
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


}
