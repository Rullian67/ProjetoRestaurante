/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.entidade;

/**
 *
 * @author tulio
 */

public class Cliente {

  
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String senha;

  // Construtor padrão
    public Cliente() {
        // Inicializar com valores padrão ou deixar em branco
        this.nome = "";
        this.email = "";
        this.telefone = "";
        this.cpf = "";
        this.senha = "";
    }

    // Construtor com nomeCliente
    public Cliente(String nomeCliente) {
        this.nome = nomeCliente;
        // Inicializar outros atributos com valores padrão
        this.email = "";
        this.telefone = "";
        this.cpf = "";
        this.senha = "";
    }

    // Construtor com todos os atributos
    public Cliente(String nome, String email, String telefone, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
}

