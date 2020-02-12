package com.eduarda.bortoletti.desafioApiRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;

@Entity
public class Conta {
    private String nome;
    private int conta;
    private double saldo;

    public Conta() {
    }

    public Conta(String nome, int conta, double saldo) {
        this.nome = nome;
        this.conta = conta;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public double getSaldo() {
        return saldo;
    }

    @JsonIgnore
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
