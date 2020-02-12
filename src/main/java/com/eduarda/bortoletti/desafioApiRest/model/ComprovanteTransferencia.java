package com.eduarda.bortoletti.desafioApiRest.model;

import javax.persistence.Entity;

@Entity
public class ComprovanteTransferencia {
    private int codTransferencia;
    private Conta contaOrigem;
    private Conta contaDestino;

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
