/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.modelo.entidade;

/**
 *
 * @author rulli
 */

public class Mesa {
    private int id;
    private int numero;
    private int capacidade;
    private boolean disponivel;

  // Construtor padrão
    public Mesa() {
        // Inicializar com valores padrão
        this.numero = 0;
        this.capacidade = 0;
        this.disponivel = true; // Supondo que a mesa esteja disponível por padrão
    }

    // Construtor com todos os atributos
    public Mesa(int numero, int capacidade, boolean disponivel) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    
}

