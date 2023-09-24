package com.ethos.portifolioapi.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Table(name = "PORTIFOLIO")
@Entity
@Immutable
public class PortifolioEntity {
    @Id
    UUID id;
    String urlImagemPerfil;
    String urlBackgroundPerfil;
    String descricaoEmpresa;
    String sobreEmpresa;
    String linkWebsiteEmpresa;
    LocalDate dataEmpresaCertificada;
    UUID fkPrestadoraServico;

    public PortifolioEntity() {

    }

    @Builder(toBuilder = true)

    public PortifolioEntity(UUID id, String urlImagemPerfil, String urlBackgroundPerfil, String descricaoEmpresa, String sobreEmpresa, String linkWebsiteEmpresa, LocalDate dataEmpresaCertificada, UUID fkPrestadoraServico) {
        this.id = UUID.randomUUID();
        this.urlImagemPerfil = urlImagemPerfil;
        this.urlBackgroundPerfil = urlBackgroundPerfil;
        this.descricaoEmpresa = descricaoEmpresa;
        this.sobreEmpresa = sobreEmpresa;
        this.linkWebsiteEmpresa = linkWebsiteEmpresa;
        this.dataEmpresaCertificada = dataEmpresaCertificada;
        this.fkPrestadoraServico = fkPrestadoraServico;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUrlImagemPerfil(String urlImagemPerfil) {
        this.urlImagemPerfil = urlImagemPerfil;
    }

    public void setUrlBackgroundPerfil(String urlBackgroundPerfil) {
        this.urlBackgroundPerfil = urlBackgroundPerfil;
    }

    public void setDescricaoEmpresa(String descricaoEmpresa) {
        this.descricaoEmpresa = descricaoEmpresa;
    }

    public void setSobreEmpresa(String sobreEmpresa) {
        this.sobreEmpresa = sobreEmpresa;
    }

    public void setLinkWebsiteEmpresa(String linkWebsiteEmpresa) {
        this.linkWebsiteEmpresa = linkWebsiteEmpresa;
    }

    public void setDataEmpresaCertificada(LocalDate dataEmpresaCertificada) {
        this.dataEmpresaCertificada = dataEmpresaCertificada;
    }

    public void setFkPrestadoraServico(UUID fkPrestadoraServico) {
        this.fkPrestadoraServico = fkPrestadoraServico;
    }
}
