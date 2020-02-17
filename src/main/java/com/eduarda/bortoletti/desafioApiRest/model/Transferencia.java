package com.eduarda.bortoletti.desafioApiRest.model;

import javax.persistence.*;

@Entity(name = "transferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor")
    private double valor;
    @ManyToOne
    private ContaCorrente contaCorrenteOrigem, contaCorrenteDestino;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
