package com.eduarda.bortoletti.desafioApiRest.controller;

import com.eduarda.bortoletti.desafioApiRest.Service.ContaDAO;
import com.eduarda.bortoletti.desafioApiRest.Service.TransferenciaDAO;
import com.eduarda.bortoletti.desafioApiRest.model.ComprovanteTransferencia;
import com.eduarda.bortoletti.desafioApiRest.model.Conta;
import com.eduarda.bortoletti.desafioApiRest.model.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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


    @RequestMapping("/")
    public ModelAndView menu() {
        if(contaDAO.listar().size()>0) {
            contaDAO.deletar(contaDAO.listar().get(0));
            if(contaDAO.listar().size()>0){
                contaDAO.deletar(contaDAO.listar().get(0));
            }
        }
        return new ModelAndView("menuPrincipal");
    }

    @GetMapping("/conta")
    public List<Conta> contaList() {
        return contaDAO.listar();
    }

    @PostMapping("/transferencia")
    public ModelAndView criarTransferencia(@Valid @ModelAttribute Transferencia transferencia) {
            transferencia.setContaOrigem(contaDAO.listar().get(0));
            transferencia.setContaDestino(contaDAO.listar().get(1));
        transferenciaDAO.salvar(transferencia);
        return  new ModelAndView("comprovante");
    }

    @GetMapping("/transferencia")
    public List<Transferencia> transferenciaList() {
        return transferenciaDAO.listar();
    }

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

    @RequestMapping("/informarTransferencia")
    public ModelAndView telaTransferencia(@Valid @ModelAttribute Conta conta) {
        if(contaDAO.listar().size()==2) {
            contaDAO.deletar(contaDAO.listar().get(1));
        }
        conta.setSaldo(1000);
        contaDAO.salvar(conta);
        return new ModelAndView("transferencia");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/efetuarTransferencia")
    private ComprovanteTransferencia efetuarTransferencia(Transferencia transferencia) {
        List<Conta> listar = contaDAO.listar();
        List<Transferencia> listaTransferencia = transferenciaDAO.listar();
        double saldoOrigem = 0;
        double saldoDestino = 0;

        saldoOrigem = listar.get(0).getSaldo() - listaTransferencia.get(0).getValor();

        saldoDestino = listar.get(1).getSaldo() + listaTransferencia.get(0).getValor();

        ComprovanteTransferencia comprovanteTransferencia = new ComprovanteTransferencia();
        comprovanteTransferencia.setCodTransferencia(1);

        contaOrigem.setId(listar.get(0).getId());
        contaOrigem.setConta(listar.get(0).getConta());
        contaOrigem.setAgencia(listar.get(0).getAgencia());
        contaOrigem.setNome(listar.get(0).getNome());
        contaOrigem.setSaldo(saldoOrigem);

        contaDestino.setId(listar.get(1).getId());
        contaDestino.setConta(listar.get(1).getConta());
        contaDestino.setAgencia(listar.get(1).getAgencia());
        contaDestino.setNome(listar.get(1).getNome());
        contaDestino.setSaldo(saldoDestino);
        comprovanteTransferencia.setContaOrigem(contaOrigem);
        comprovanteTransferencia.setContaDestino(contaDestino);
        return comprovanteTransferencia;

    }

    @RequestMapping(method = RequestMethod.PUT, path = "/depositar/{quantidade}/{id}")
    public ResponseEntity<Transferencia> depositar(@PathVariable double quantidade,
                                                   @PathVariable Long id, @Valid @ModelAttribute Transferencia newTransf) {
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
