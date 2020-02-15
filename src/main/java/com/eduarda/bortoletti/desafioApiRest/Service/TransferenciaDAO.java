package com.eduarda.bortoletti.desafioApiRest.Service;

import com.eduarda.bortoletti.desafioApiRest.Repository.TransferenciaRepository;
import com.eduarda.bortoletti.desafioApiRest.model.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaDAO {
    @Autowired
    TransferenciaRepository transferenciaRepository;

    public Optional<Transferencia> findById(Long id) {
        return transferenciaRepository.findById(id);
    }

    public Transferencia salvar(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> listar() {
        return (List<Transferencia>) transferenciaRepository.findAll();
    }

    public void deletar(Transferencia transferencia){
        transferenciaRepository.delete(transferencia);
    }
}
