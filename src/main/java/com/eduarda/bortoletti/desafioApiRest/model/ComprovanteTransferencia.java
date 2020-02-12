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
    private Conta contaOrigem, contaDestino;

    public int getCodTransferencia() {
        return codTransferencia;
    }

    public void setCodTransferencia(int codTransferencia) {
        this.codTransferencia = codTransferencia;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }
}
