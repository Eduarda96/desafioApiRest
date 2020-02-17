package com.eduarda.bortoletti.desafioApiRest.Service;

import com.eduarda.bortoletti.desafioApiRest.Repository.ContaRepository;
import com.eduarda.bortoletti.desafioApiRest.Repository.TransferenciaRepository;
import com.eduarda.bortoletti.desafioApiRest.model.ContaCorrente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaDAO {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    TransferenciaRepository transferenciaRepository;

    public ContaCorrente salvar(ContaCorrente contaCorrente) {
        return contaRepository.save(contaCorrente);
    }

    public List<ContaCorrente> listar() {
        return (List<ContaCorrente>) contaRepository.findAll();
    }

    public void deletar(ContaCorrente contaCorrente){
        contaRepository.delete(contaCorrente);
    }
}
