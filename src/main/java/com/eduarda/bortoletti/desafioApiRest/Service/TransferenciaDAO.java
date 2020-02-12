package com.eduarda.bortoletti.desafioApiRest.Service;

import com.eduarda.bortoletti.desafioApiRest.Repository.TransferenciaRepository;
import com.eduarda.bortoletti.desafioApiRest.model.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferenciaDAO {
    @Autowired
    TransferenciaRepository repository;

    public Transferencia salvar(Transferencia transferencia) {
        return repository.save(transferencia);
    }

    public List<Transferencia> listar() {
        return (List<Transferencia>) repository.findAll();
    }
}
