package com.example.listadepessoassql.model;

public class Pessoa {

    private int id;
    private String nome;
    private int ano;

    public Pessoa() {

    }

    public Pessoa(int id, String nome, int ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getId(){
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getAno() {
        return this.ano;
    }
}
