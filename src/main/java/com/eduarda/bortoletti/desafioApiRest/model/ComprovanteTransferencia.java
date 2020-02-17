package com.eduarda.bortoletti.desafioApiRest.model;

import javax.persistence.*;

@Entity(name = "comprovante")
public class ComprovanteTransferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cod_transferencia")
    private int codTransferencia;
    @ManyToOne(fetch = FetchType.EAGER)
    private ContaCorrente contaCorrenteOrigem, contaCorrenteDestino;

    public int getCodTransferencia() {
        return codTransferencia;
    }

    public void setCodTransferencia(int codTransferencia) {
        this.codTransferencia = codTransferencia;
    }

    public ContaCorrente getContaCorrenteOrigem() {
        return contaCorrenteOrigem;
    }

    public void setContaCorrenteOrigem(ContaCorrente contaCorrenteOrigem) {
        this.contaCorrenteOrigem = contaCorrenteOrigem;
    }

    public ContaCorrente getContaCorrenteDestino() {
        return contaCorrenteDestino;
    }

    public void setContaCorrenteDestino(ContaCorrente contaCorrenteDestino) {
        this.contaCorrenteDestino = contaCorrenteDestino;
    }
}
