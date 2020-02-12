package com.eduarda.bortoletti.desafioApiRest.Service;

import com.eduarda.bortoletti.desafioApiRest.Repository.ContaRepository;
import com.eduarda.bortoletti.desafioApiRest.model.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaDAO {
    @Autowired
    private ContaRepository contaRepository;

    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> listar() {
        return (List<Conta>) contaRepository.findAll();
    }

}
