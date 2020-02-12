package com.eduarda.bortoletti.desafioApiRest.model;

import javax.persistence.Entity;

@Entity
public class Transferencia {
    private double valor;
    private Conta contaOrigem;
    private Conta contaDestino;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
