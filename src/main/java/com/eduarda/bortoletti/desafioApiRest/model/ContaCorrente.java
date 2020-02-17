package com.eduarda.bortoletti.desafioApiRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "conta")
public class ContaCorrente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "conta")
    private int conta;
    @Column(name = "saldo")
    private double saldo;
    @Column(name = "agencia")
    private int agencia;
    @Column(name = "cpf")
    private String cpf;

    public ContaCorrente() {
    }

    public ContaCorrente(String nome, int conta, double saldo, int agencia, String cpf) {
        this.nome = nome;
        this.conta = conta;
        this.saldo = saldo;
        this.agencia = agencia;
        this.cpf = cpf;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
