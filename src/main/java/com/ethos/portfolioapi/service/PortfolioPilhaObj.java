package com.ethos.portfolioapi.service;

import com.ethos.portfolioapi.controller.request.PortfolioImagemRequest;
import com.ethos.portfolioapi.controller.response.PortfolioImagemResponse;

public class PortfolioPilhaObj {

    private PortfolioImagemResponse[] pilha;
    private int topo;

    // Construtor
    public PortfolioPilhaObj() {
        pilha = new PortfolioImagemResponse[2];
        topo = -1;
    }

    // Método isEmpty
    public Boolean isEmpty() {
        return topo == -1;
    }

    // Método isFull
    public Boolean isFull() {
        return topo >= pilha.length - 1;
    }

    // Método push
    public void push(PortfolioImagemRequest info) {
        if (isFull()) {
            throw new IllegalStateException("Pilha cheia!");
        }
        pilha[++topo] = new PortfolioImagemResponse(info.urlImagemPerfil(), info.cancelado(), info.id());
    }

    // Método pop
    public PortfolioImagemResponse pop() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo--];
    }

    // Método peek
    public PortfolioImagemResponse peek() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo];
    }

    // Método exibe
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia!");
        } else {
            System.out.println("\nElementos da pilha:");
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }

    // Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}
