package com.eduarda.bortoletti.desafioApiRest.Service;

import com.eduarda.bortoletti.desafioApiRest.Repository.ContaRepository;
import com.eduarda.bortoletti.desafioApiRest.Repository.TransferenciaRepository;
import com.eduarda.bortoletti.desafioApiRest.model.ComprovanteTransferencia;
import com.eduarda.bortoletti.desafioApiRest.model.Conta;
import com.eduarda.bortoletti.desafioApiRest.model.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaDAO {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    TransferenciaRepository transferenciaRepository;

    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> listar() {
        return (List<Conta>) contaRepository.findAll();
    }

    public void deposita(Transferencia transferencia) {
        Conta origem = new Conta();
        Conta destino = new Conta();
        ComprovanteTransferencia comprovanteTransferencia = new ComprovanteTransferencia();
        comprovanteTransferencia.setCodTransferencia(transferencia.hashCode());

        double saldoOrigem = origem.getSaldo() - transferencia.getValor();
        double saldoDestino = destino.getSaldo() + transferencia.getValor();

        origem.setSaldo(saldoOrigem);
        destino.setSaldo(saldoDestino);
        comprovanteTransferencia.setContaOrigem(origem);
        comprovanteTransferencia.setContaDestino(destino);




    }

    public Optional<Conta> findById(Long id) {
        return contaRepository.findById(id);
    }
}
