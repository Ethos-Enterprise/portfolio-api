package com.ethos.portfolioapi.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

public record Portfolio(String urlImagemPerfil, String urlBackgroundPerfil, String descricaoEmpresa, String sobreEmpresa,
                         String linkWebsiteEmpresa, LocalDate dataEmpresaCertificada, UUID fkPrestadoraServico){

    @Builder(toBuilder = true)
    public Portfolio(String urlImagemPerfil, String urlBackgroundPerfil, String descricaoEmpresa, String sobreEmpresa, String linkWebsiteEmpresa, LocalDate dataEmpresaCertificada, UUID fkPrestadoraServico) {
        this.urlImagemPerfil = urlImagemPerfil;
        this.urlBackgroundPerfil = urlBackgroundPerfil;
        this.descricaoEmpresa = descricaoEmpresa;
        this.sobreEmpresa = sobreEmpresa;
        this.linkWebsiteEmpresa = linkWebsiteEmpresa;
        this.dataEmpresaCertificada = dataEmpresaCertificada;
        this.fkPrestadoraServico = fkPrestadoraServico;
    }



}
