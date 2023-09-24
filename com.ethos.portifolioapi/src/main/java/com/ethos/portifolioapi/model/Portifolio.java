package com.ethos.portifolioapi.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

public record Portifolio(String urlImagemPerfil, String urlBackgroundPerfil, String descricaoEmpresa, String sobreEmpresa,
                         String linkWebsiteEmpresa, LocalDate dataEmpresaCertificada, UUID fkPrestadoraServico){

    @Builder(toBuilder = true)
    public Portifolio(String urlImagemPerfil, String urlBackgroundPerfil, String descricaoEmpresa, String sobreEmpresa, String linkWebsiteEmpresa, LocalDate dataEmpresaCertificada, UUID fkPrestadoraServico) {
        this.urlImagemPerfil = urlImagemPerfil;
        this.urlBackgroundPerfil = urlBackgroundPerfil;
        this.descricaoEmpresa = descricaoEmpresa;
        this.sobreEmpresa = sobreEmpresa;
        this.linkWebsiteEmpresa = linkWebsiteEmpresa;
        this.dataEmpresaCertificada = dataEmpresaCertificada;
        this.fkPrestadoraServico = fkPrestadoraServico;
    }



}
