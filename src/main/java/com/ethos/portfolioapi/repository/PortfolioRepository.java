package com.ethos.portfolioapi.repository;

import com.ethos.portfolioapi.repository.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PortfolioRepository extends JpaRepository<PortfolioEntity, UUID> {

    @Transactional
    @Modifying
    @Query("update PortfolioEntity p set p.urlImagemPerfil = ?1 where p.id = ?2")
    void updateUrlImagemPerfil(@NonNull String urlImagemPerfil, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortfolioEntity p set p.urlBackgroundPerfil = ?1 where p.id = ?2")
    void updateUrlBackgroundPerfil(@NonNull String urlBackgroundPerfil, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortfolioEntity p set p.descricaoEmpresa = ?1 where p.id = ?2")
    void updateUrlDescricaoEmpresa(@NonNull String descricaoEmpresa, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortfolioEntity p set p.sobreEmpresa = ?1 where p.id = ?2")
    void updateUrlSobreEmpresa(@NonNull String sobreEmpresa, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortfolioEntity p set p.linkWebsiteEmpresa = ?1 where p.id = ?2")
    void updateUrlLinkWebsiteEmpresa(@NonNull String linkWebsiteEmpresa, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortfolioEntity p set p.dataEmpresaCertificada = ?1 where p.id = ?2")
    void updateUrlDataEmpresaCertificada(@NonNull LocalDate dataEmpresaCertificada, @NonNull UUID id);

    PortfolioEntity findByUrlImagemPerfil(String urlImagemPerfil);
    List<PortfolioEntity> findByUrlBackgroundPerfil(String urlBackgroundPerfil);
    List<PortfolioEntity> findByDescricaoEmpresa(String descricaoEmpresa);
    List<PortfolioEntity> findBySobreEmpresa(String sobreEmpresa);
    List<PortfolioEntity> findByLinkWebsiteEmpresa(String linkWebsiteEmpresa);
    List<PortfolioEntity> findByDataEmpresaCertificada(LocalDate dataEmpresaCertificada);
    Optional <PortfolioEntity> findByFkPrestadoraServico(UUID fkPrestadoraServico);
}

