package com.ethos.portifolioapi.service;

import com.ethos.portifolioapi.controller.request.PortifolioRequest;
import com.ethos.portifolioapi.controller.response.PortifolioResponse;
import com.ethos.portifolioapi.exception.PortifolioNaoExisteException;
import com.ethos.portifolioapi.mapper.PortifolioEntityMapper;
import com.ethos.portifolioapi.mapper.PortifolioMapper;
import com.ethos.portifolioapi.mapper.PortifolioResponseMapper;
import com.ethos.portifolioapi.model.Portifolio;
import com.ethos.portifolioapi.repository.entity.PortifolioEntity;
import com.ethos.portifolioapi.repository.PortifolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PortifolioService {

    private final PortifolioRepository repository;
    private final PortifolioMapper portifolioModelMapper;
    private final PortifolioEntityMapper portifolioEntityMapper;
    private final PortifolioResponseMapper portifolioResponseMapper;


    public PortifolioResponse postPotifolio(PortifolioRequest request) {
        Portifolio model = portifolioModelMapper.from(request);
        PortifolioEntity entity = portifolioEntityMapper.from(model);
        PortifolioEntity savedEntity = savePortifolio(entity);
        return  portifolioResponseMapper.from(savedEntity);
    }

    private PortifolioEntity savePortifolio(PortifolioEntity entity) {
        PortifolioEntity portifolioSaved = null;
        portifolioSaved = repository.save(entity);
        return portifolioSaved;
    }

    public List<PortifolioResponse> getAllPortifolio() {
        List<PortifolioEntity> portifolio = (repository.findAll());
        return portifolio.stream().map(portifolioResponseMapper::from).toList();
    }

    public PortifolioResponse getPortifolioById(UUID id) {
        Optional<PortifolioEntity> portifolio = repository.findById(id);
        if (portifolio.isEmpty()) {
            throw new PortifolioNaoExisteException("Empresa com id %s não existe".formatted(id.toString()));
        }
        return portifolio.map(portifolioResponseMapper::from).get();
    }

    public PortifolioResponse putPortifolioById(UUID id, PortifolioRequest request) {
        Optional<PortifolioEntity> entity;
        entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new PortifolioNaoExisteException("Portifolio com o id %s não existe".formatted(id));
        }
        if (request.urlImagemPerfil() != null && !request.urlImagemPerfil().isEmpty()) {
            repository.updateUrlImagemPerfil(request.urlImagemPerfil(), id);
        }
        if (request.urlBackgroundPerfil() != null && !request.urlBackgroundPerfil().isEmpty()) {
            repository.updateUrlBackgroundPerfil(request.urlBackgroundPerfil(), id);
        }
        if (request.descricaoEmpresa() != null && !request.descricaoEmpresa().isEmpty()) {
            repository.updateUrlDescricaoEmpresa(request.descricaoEmpresa(), id);
        }
        if (request.sobreEmpresa() != null && !request.sobreEmpresa().isEmpty()) {
            repository.updateUrlSobreEmpresa(request.sobreEmpresa(), id);
        }
        if (request.linkWebsiteEmpresa() != null && !request.linkWebsiteEmpresa().isEmpty()) {
            repository.updateUrlLinkWebsiteEmpresa(request.linkWebsiteEmpresa(), id);
        }
        if (request.dataEmpresaCertificada() != null) {
            repository.updateUrlDataEmpresaCertificada(request.dataEmpresaCertificada(), id);
        }

        return repository.findById(id).map(portifolioResponseMapper::from).get();
    }

    public String deletePortifolioById(UUID id) {
        Optional<PortifolioEntity> portifolio = repository.findById(id);
        if (portifolio.isEmpty()) {
            throw new PortifolioNaoExisteException("Portifolio com o id %s não existe".formatted(id));
        }
        repository.deleteById(id);
        return "Portifolio deletado com sucesso";
    }

    public List<PortifolioResponse> getPortifolioByUrlImagemPerfil(String urlImagemPerfil) {
        List<PortifolioEntity> portifolio = repository.findByUrlImagemPerfil(urlImagemPerfil);
        return portifolio.stream().map(portifolioResponseMapper::from).toList();
    }

    public List<PortifolioResponse> getPortifolioByUrlBackgroundPerfil(String urlBackgroundPerfil) {
        List<PortifolioEntity> portifolio = repository.findByUrlBackgroundPerfil(urlBackgroundPerfil);
        return portifolio.stream().map(portifolioResponseMapper::from).toList();
    }

    public List<PortifolioResponse> getPortifolioByDescricaoEmpresa(String descricaoEmpresa) {
        List<PortifolioEntity> portifolio = repository.findByDescricaoEmpresa(descricaoEmpresa);
        return portifolio.stream().map(portifolioResponseMapper::from).toList();
    }
    public List<PortifolioResponse> getPortifolioBySobreEmpresa(String sobreEmpresa) {
        List<PortifolioEntity> portifolio = repository.findBySobreEmpresa(sobreEmpresa);
        return portifolio.stream().map(portifolioResponseMapper::from).toList();
    }
    public List<PortifolioResponse> getPortifolioByLinkWebsiteEmpresa(String linkWebsiteEmpresa) {
        List<PortifolioEntity> portifolio = repository.findByLinkWebsiteEmpresa(linkWebsiteEmpresa);
        return portifolio.stream().map(portifolioResponseMapper::from).toList();
    }

    public List<PortifolioResponse> getPortifolioByDataEmpresaCertificada(LocalDate dataEmpresaCertificada) {
        List<PortifolioEntity> portifolio = repository.findByDataEmpresaCertificada(dataEmpresaCertificada);
        return portifolio.stream().map(portifolioResponseMapper::from).toList();
    }


    public List<PortifolioResponse> getPortifolioByFkPrestadoraServico(UUID fkPrestadoraServico) {
        List<PortifolioEntity> portifolio = repository.findByFkPrestadoraServico(fkPrestadoraServico);
        return portifolio.stream().map(portifolioResponseMapper::from).toList();
    }


}
