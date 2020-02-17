package com.eduarda.bortoletti.desafioApiRest.controller;

import com.eduarda.bortoletti.desafioApiRest.Service.ContaDAO;
import com.eduarda.bortoletti.desafioApiRest.Service.TransferenciaDAO;
import com.eduarda.bortoletti.desafioApiRest.model.ComprovanteTransferencia;
import com.eduarda.bortoletti.desafioApiRest.model.ContaCorrente;
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
    private ContaCorrente contaCorrenteOrigem = new ContaCorrente();
    private ContaCorrente contaCorrenteDestino = new ContaCorrente();
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
    public List<ContaCorrente> contaList() {
        return contaDAO.listar();
    }

    //adiciona transferência
    @PostMapping("/transferencia")
    public ModelAndView criarTransferencia(@Valid @ModelAttribute Transferencia transferencia) {
            transferencia.setContaCorrenteOrigem(contaDAO.listar().get(0));
            transferencia.setContaCorrenteDestino(contaDAO.listar().get(1));
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

    //contaCorrente origem será salva assim que a chamada for feita
    @RequestMapping("/cadastroConta")
    public ModelAndView telaConta(@Valid @ModelAttribute ContaCorrente contaCorrente) {
        if(contaDAO.listar().size()>2) {
            contaDAO.deletar(contaDAO.listar().get(1));
        }
        contaCorrente.setId((long) 1);
        contaCorrente.setNome("Joao");
        contaCorrente.setAgencia(555555);
        contaCorrente.setConta(2222);
        contaCorrente.setSaldo(2000);
        contaCorrente.setCpf("88996655447");
        contaDAO.salvar(contaCorrente);
        return new ModelAndView("contaOrigem");
    }

    //salva contaCorrente destino informado pelo usuario
    @RequestMapping("/informarTransferencia")
    public ModelAndView telaTransferencia(@Valid @ModelAttribute ContaCorrente contaCorrente) {
        if(contaDAO.listar().size()==2) {
            contaDAO.deletar(contaDAO.listar().get(1));
        }

        contaCorrente.setSaldo(1000);
        contaDAO.salvar(contaCorrente);
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
        contaCorrenteOrigem.setId(contaDAO.listar().get(0).getId());
        contaCorrenteOrigem.setConta(contaDAO.listar().get(0).getConta());
        contaCorrenteOrigem.setAgencia(contaDAO.listar().get(0).getAgencia());
        contaCorrenteOrigem.setNome(contaDAO.listar().get(0).getNome());
        contaCorrenteOrigem.setSaldo(saldoOrigem);
        contaCorrenteOrigem.setCpf(contaDAO.listar().get(0).getCpf());
        contaCorrenteDestino.setId(contaDAO.listar().get(1).getId());
        contaCorrenteDestino.setConta(contaDAO.listar().get(1).getConta());
        contaCorrenteDestino.setAgencia(contaDAO.listar().get(1).getAgencia());
        contaCorrenteDestino.setNome(contaDAO.listar().get(1).getNome());
        contaCorrenteDestino.setSaldo(saldoDestino);
        contaCorrenteDestino.setCpf(contaDAO.listar().get(1).getCpf());
        comprovanteTransferencia.setContaCorrenteOrigem(contaCorrenteOrigem);
        comprovanteTransferencia.setContaCorrenteDestino(contaCorrenteDestino);
        return comprovanteTransferencia;
    }

    //quantidade que será transferida
    @RequestMapping(method = RequestMethod.PUT, path = "/depositar/{quantidade}/{id}")
    public ResponseEntity<Transferencia> depositar(@PathVariable double quantidade, @PathVariable Long id) {
        Optional<Transferencia> transferenciaOptional = transferenciaDAO.findById(id);
        contaCorrenteOrigem = contaDAO.listar().get(0);
        contaCorrenteDestino = contaDAO.listar().get(1);
        if (transferenciaOptional.isPresent()) {
            Transferencia transferencia = transferenciaOptional.get();
            transferencia.setValor(quantidade);
            transferencia.setContaCorrenteOrigem(contaCorrenteOrigem);
            transferencia.setContaCorrenteDestino(contaCorrenteDestino);
            transferenciaDAO.salvar(transferencia);
            return new ResponseEntity<Transferencia>(transferencia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
