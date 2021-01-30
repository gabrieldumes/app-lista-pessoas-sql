package com.example.listadepessoassql.model;

public class Pessoa {

    private String nome;
    private int ano;

    public Pessoa() {

    }

    public Pessoa(String nome, int ano) {
        this.nome = nome;
        this.ano = ano;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getNome() {
        return this.nome;
    }

    public int getAno() {
        return this.ano;
    }
}
