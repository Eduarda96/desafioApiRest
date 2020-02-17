package com.eduarda.bortoletti.desafioApiRest.controller;

import com.eduarda.bortoletti.desafioApiRest.Service.ContaDAO;
import com.eduarda.bortoletti.desafioApiRest.Service.TransferenciaDAO;
import com.eduarda.bortoletti.desafioApiRest.model.ComprovanteTransferencia;
import com.eduarda.bortoletti.desafioApiRest.model.Conta;
import com.eduarda.bortoletti.desafioApiRest.model.Transferencia;
import jdk.nashorn.internal.runtime.regexp.joni.Warnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.*;
import javax.validation.Valid;
import java.awt.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ContaController {
    private Conta contaOrigem = new Conta();
    private Conta contaDestino = new Conta();
    @Autowired
    ContaDAO contaDAO;
    @Autowired
    TransferenciaDAO transferenciaDAO;

    //apresenta menu principal
    @RequestMapping("/")
    public ModelAndView menu() {
        if(transferenciaDAO.listar().size()>0){
            transferenciaDAO.deletar(transferenciaDAO.listar().get(0));
        }
        if(contaDAO.listar().size()>0) {
            contaDAO.deletar(contaDAO.listar().get(0));
            if(contaDAO.listar().size()>0){
                contaDAO.deletar(contaDAO.listar().get(0));
            }
        }
        return new ModelAndView("menuPrincipal");
    }

    //lista conta cadastradas
    @GetMapping("/conta")
    public List<Conta> contaList() {
        return contaDAO.listar();
    }

    //adiciona transferência
    @PostMapping("/transferencia")
    public ModelAndView criarTransferencia(@Valid @ModelAttribute Transferencia transferencia) {
            transferencia.setContaOrigem(contaDAO.listar().get(0));
            transferencia.setContaDestino(contaDAO.listar().get(1));
            transferenciaDAO.salvar(transferencia);

        if(transferencia.getValor() < 20 || transferenciaDAO.listar().get(0).getValor() > 2000){
            return new ModelAndView("valorExcedido");
        }
        return  new ModelAndView("comprovante");
    }

    //lista transferência cadastrada
    @GetMapping("/transferencia")
    public List<Transferencia> transferenciaList() {
        return transferenciaDAO.listar();
    }

    //conta origem será salva assim que a chamada for feita
    @RequestMapping("/cadastroConta")
    public ModelAndView telaConta(@Valid @ModelAttribute Conta conta) {
        if(contaDAO.listar().size()>2) {
            contaDAO.deletar(contaDAO.listar().get(1));
        }
        conta.setId((long) 1);
        conta.setNome("João");
        conta.setAgencia(5555);
        conta.setConta(2222);
        conta.setSaldo(2000);
        conta.setCpf("88996655447");
        contaDAO.salvar(conta);
        return new ModelAndView("contaOrigem");
    }

    //salva conta destino informado pelo usuario
    @RequestMapping("/informarTransferencia")
    public ModelAndView telaTransferencia(@Valid @ModelAttribute Conta conta) {
        if(contaDAO.listar().size()==2) {
            contaDAO.deletar(contaDAO.listar().get(1));
        }

        conta.setSaldo(1000);
        contaDAO.salvar(conta);
        return new ModelAndView("transferencia");
    }

    //calculo da transferência
    @RequestMapping(method = RequestMethod.GET, path = "/efetuarTransferencia")
    private ComprovanteTransferencia efetuarTransferencia(Transferencia transferencia) {
        double saldoOrigem;
        double saldoDestino;
        ComprovanteTransferencia comprovanteTransferencia = new ComprovanteTransferencia();
        comprovanteTransferencia.setCodTransferencia(1);
        saldoOrigem = contaDAO.listar().get(0).getSaldo() - transferenciaDAO.listar().get(0).getValor();
        saldoDestino = contaDAO.listar().get(1).getSaldo() + transferenciaDAO.listar().get(0).getValor();
        contaOrigem.setId(contaDAO.listar().get(0).getId());
        contaOrigem.setConta(contaDAO.listar().get(0).getConta());
        contaOrigem.setAgencia(contaDAO.listar().get(0).getAgencia());
        contaOrigem.setNome(contaDAO.listar().get(0).getNome());
        contaOrigem.setSaldo(saldoOrigem);
        contaOrigem.setCpf(contaDAO.listar().get(0).getCpf());
        contaDestino.setId(contaDAO.listar().get(1).getId());
        contaDestino.setConta(contaDAO.listar().get(1).getConta());
        contaDestino.setAgencia(contaDAO.listar().get(1).getAgencia());
        contaDestino.setNome(contaDAO.listar().get(1).getNome());
        contaDestino.setSaldo(saldoDestino);
        contaDestino.setCpf(contaDAO.listar().get(1).getCpf());
        comprovanteTransferencia.setContaOrigem(contaOrigem);
        comprovanteTransferencia.setContaDestino(contaDestino);
        return comprovanteTransferencia;
    }

    //quantidade que será transferida
    @RequestMapping(method = RequestMethod.PUT, path = "/depositar/{quantidade}/{id}")
    public ResponseEntity<Transferencia> depositar(@PathVariable double quantidade, @PathVariable Long id) {
        Optional<Transferencia> transferenciaOptional = transferenciaDAO.findById(id);
        contaOrigem = contaDAO.listar().get(0);
        contaDestino = contaDAO.listar().get(1);
        if (transferenciaOptional.isPresent()) {
            Transferencia transferencia = transferenciaOptional.get();
            transferencia.setValor(quantidade);
            transferencia.setContaOrigem(contaOrigem);
            transferencia.setContaDestino(contaDestino);
            transferenciaDAO.salvar(transferencia);
            return new ResponseEntity<Transferencia>(transferencia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
