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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ContaController {
    /* private List<Double> quantidadeNotasEnviadas;
    private Gson gson = new Gson();
    private String msgRetorno;
    private String msgErro;*/
    private Conta contaOrigem = new Conta();
    private Conta contaDestino = new Conta();
    @Autowired
    ContaDAO contaDAO;
    @Autowired
    TransferenciaDAO transferenciaDAO;

    @PostMapping("/conta")
    public Conta criarConta(@Valid @RequestBody Conta conta) {
        return contaDAO.salvar(conta);
    }

    @GetMapping("/conta")
    public List<Conta> contaList() {
        return contaDAO.listar();
    }

    @PostMapping("/transferencia")
    public Transferencia criarTransferencia(@Valid @RequestBody Transferencia transferencia) {
        return transferenciaDAO.salvar(transferencia);
    }

    @GetMapping("/transferencia")
    public List<Transferencia> transferenciaList() {
        return transferenciaDAO.listar();
    }

    @RequestMapping("/")
    public String home() {
        return "Ola";
    }

    /*@RequestMapping(method = RequestMethod.GET, path = "/efetuarTransferencia")
    public ResponseEntity<String> transferencia(Transferencia transferencia) {
//        inicializarContas();
        ComprovanteTransferencia comprovanteTransferencia = efetuarTransferencia(transferencia);
        if (comprovanteTransferencia != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    private void inicializarContas() {
        contaOrigem = new Conta("Eduarda Bortoletti", 324, 1500);
        contaDestino = new Conta("Brenda Danni", 432, 2000);
    }

    //efetua transferencia e verica qual conta está retirando e qual está recebendo
    @RequestMapping(method = RequestMethod.GET, path = "/efetuarTransferencia")
    private ComprovanteTransferencia efetuarTransferencia(Transferencia transferencia) {
       /* ComprovanteTransferencia comprovante = new ComprovanteTransferencia();
//        comprovante.setCodTransferencia(transferencia.hashCode());
        int conta = transferencia.getContaOrigem().getConta();

        if (conta == contaOrigem.getConta()) {
            if (transferencia.getValor() > contaOrigem.getSaldo()) {
                return null;
            }

            double saldoOrigem = contaDestino.getSaldo() - transferencia.getValor();
            double saldoDestino = contaOrigem.getSaldo() + transferencia.getValor();

            contaOrigem.setSaldo(saldoOrigem);
            contaDestino.setSaldo(saldoDestino);

            comprovante.setContaOrigem(contaOrigem);
            comprovante.setContaDestino(contaDestino);
        } else if (conta == contaDestino.getConta()) {
            if (transferencia.getValor() > contaDestino.getSaldo()) {
                return null;
            }

            double saldoOrigem = contaDestino.getSaldo() - transferencia.getValor();
            double saldoDestino = contaOrigem.getSaldo() + transferencia.getValor();

            contaOrigem.setSaldo(saldoOrigem);
            contaDestino.setSaldo(saldoDestino);

            comprovante.setContaOrigem(contaOrigem);
            comprovante.setContaDestino(contaDestino);
        } else {
            return null;
        }
*/
        List<Conta> listar = contaDAO.listar();
        List<Transferencia> listaTransferencia = transferenciaDAO.listar();
        double saldoOrigem = 0;
        double saldoDestino = 0;

        for (Conta conta : listar) {
            for (Transferencia transf : listaTransferencia) {
                if (conta.getId().equals(transf.getContaOrigem().getId())) {
                    saldoOrigem = conta.getSaldo() - transf.getValor();
                }
                if (conta.getId().equals(transf.getContaDestino().getId())) {
                    saldoDestino = conta.getSaldo() + transferencia.getValor();
                }
            }
        }

        ComprovanteTransferencia comprovanteTransferencia = new ComprovanteTransferencia();
        comprovanteTransferencia.setCodTransferencia(1);

        contaOrigem.setSaldo(saldoOrigem);
        contaDestino.setSaldo(saldoDestino);
        comprovanteTransferencia.setContaOrigem(contaOrigem);
        comprovanteTransferencia.setContaDestino(contaDestino);
        return comprovanteTransferencia;

    }

    @RequestMapping(method = RequestMethod.PUT, path = "depositar/{quantidade}/{id}")
    public ResponseEntity<Transferencia> depositar(@PathVariable double quantidade,
                                                   @PathVariable Long id, @Valid @RequestBody Transferencia newTransf) {
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
