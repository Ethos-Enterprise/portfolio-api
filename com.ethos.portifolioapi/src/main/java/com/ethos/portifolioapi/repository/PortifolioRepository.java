package com.ethos.portifolioapi.repository;

import com.ethos.portifolioapi.repository.entity.PortifolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public interface PortifolioRepository extends JpaRepository<PortifolioEntity, UUID> {

    @Transactional
    @Modifying
    @Query("update PortifolioEntity p set p.urlImagemPerfil = ?1 where p.id = ?2")
    void updateUrlImagemPerfil(@NonNull String urlImagemPerfil, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortifolioEntity p set p.urlBackgroundPerfil = ?1 where p.id = ?2")
    void updateUrlBackgroundPerfil(@NonNull String urlBackgroundPerfil, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortifolioEntity p set p.descricaoEmpresa = ?1 where p.id = ?2")
    void updateUrlDescricaoEmpresa(@NonNull String descricaoEmpresa, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortifolioEntity p set p.sobreEmpresa = ?1 where p.id = ?2")
    void updateUrlSobreEmpresa(@NonNull String sobreEmpresa, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortifolioEntity p set p.linkWebsiteEmpresa = ?1 where p.id = ?2")
    void updateUrlLinkWebsiteEmpresa(@NonNull String linkWebsiteEmpresa, @NonNull UUID id);

    @Transactional
    @Modifying
    @Query("update PortifolioEntity p set p.dataEmpresaCertificada = ?1 where p.id = ?2")
    void updateUrlDataEmpresaCertificada(@NonNull LocalDate dataEmpresaCertificada, @NonNull UUID id);

    List<PortifolioEntity> findByUrlImagemPerfil(String urlImagemPerfil);
    List<PortifolioEntity> findByUrlBackgroundPerfil(String urlBackgroundPerfil);
    List<PortifolioEntity> findByDescricaoEmpresa(String descricaoEmpresa);
    List<PortifolioEntity> findBySobreEmpresa(String sobreEmpresa);
    List<PortifolioEntity> findByLinkWebsiteEmpresa(String linkWebsiteEmpresa);
    List<PortifolioEntity> findByDataEmpresaCertificada(LocalDate dataEmpresaCertificada);
    List<PortifolioEntity> findByFkPrestadoraServico(UUID fkPrestadoraServico);

}

