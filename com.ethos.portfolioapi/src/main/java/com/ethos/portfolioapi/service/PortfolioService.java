package com.ethos.portfolioapi.service;

import com.ethos.portfolioapi.controller.request.PortfolioRequest;
import com.ethos.portfolioapi.controller.response.PortfolioResponse;
import com.ethos.portfolioapi.exception.PortfolioNaoExisteException;
import com.ethos.portfolioapi.mapper.PortfolioEntityMapper;
import com.ethos.portfolioapi.mapper.PortfolioMapper;
import com.ethos.portfolioapi.mapper.PortfolioResponseMapper;
import com.ethos.portfolioapi.model.Portfolio;
import com.ethos.portfolioapi.repository.entity.PortfolioEntity;
import com.ethos.portfolioapi.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository repository;
    private final PortfolioMapper portfolioModelMapper;
    private final PortfolioEntityMapper portfolioEntityMapper;
    private final PortfolioResponseMapper portfolioResponseMapper;


    public PortfolioResponse postPortfolio(PortfolioRequest request) {
        Portfolio model = portfolioModelMapper.from(request);
        PortfolioEntity entity = portfolioEntityMapper.from(model);
        PortfolioEntity savedEntity = savePortfolio(entity);
        return  portfolioResponseMapper.from(savedEntity);
    }

    public PortfolioEntity savePortfolio(PortfolioEntity entity) {
        PortfolioEntity portfolioSaved = null;
        portfolioSaved = repository.save(entity);
        return portfolioSaved;
    }
    public PortfolioResponse updateUrlImagemPerfil(UUID id, String newUrlImagemPerfil) {
            Optional<PortfolioEntity> optionalPortfolio = repository.findById(id);

            if (optionalPortfolio.isPresent()) {
                PortfolioEntity portfolio = optionalPortfolio.get();
                portfolio.setUrlImagemPerfil(newUrlImagemPerfil);

                // Save the updated entity
                PortfolioEntity updatedPortfolio = repository.save(portfolio);

                // Convert and return the updated entity as a response
                return portfolioResponseMapper.from(updatedPortfolio);
            } else {
                throw new PortfolioNaoExisteException("Portfólio com o ID " + id + " não existe");
            }
    }
    public PortfolioResponse updateurlBackgroundPerfil(UUID id, String newUrlBackgroundPerfil) {
            Optional<PortfolioEntity> optionalPortfolio = repository.findById(id);

            if (optionalPortfolio.isPresent()) {
                PortfolioEntity portfolio = optionalPortfolio.get();
                portfolio.setUrlImagemPerfil(newUrlBackgroundPerfil);

                // Save the updated entity
                PortfolioEntity updatedPortfolio = repository.save(portfolio);

                // Convert and return the updated entity as a response
                return portfolioResponseMapper.from(updatedPortfolio);
            } else {
                throw new PortfolioNaoExisteException("Portfólio com o ID " + id + " não existe");
            }
    }

    public List<PortfolioResponse> getAllPortfolio() {
        List<PortfolioEntity> portfolio = (repository.findAll());
        return portfolio.stream().map(portfolioResponseMapper::from).toList();
    }

    public PortfolioResponse getPortfolioById(UUID id) {
        Optional<PortfolioEntity> portfolio = repository.findById(id);
        if (portfolio.isEmpty()) {
            throw new PortfolioNaoExisteException("Empresa com id %s não existe".formatted(id.toString()));
        }
        return portfolio.map(portfolioResponseMapper::from).get();
    }

    public PortfolioResponse putPortfolioById(UUID id, PortfolioRequest request) {
        Optional<PortfolioEntity> entity;
        entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new PortfolioNaoExisteException("Portfólio com o id %s não existe".formatted(id));
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

        return repository.findById(id).map(portfolioResponseMapper::from).get();
    }

    public String deletePortfolioById(UUID id) {
        Optional<PortfolioEntity> portfolio = repository.findById(id);
        if (portfolio.isEmpty()) {
            throw new PortfolioNaoExisteException("Portfólio com o id %s não existe".formatted(id));
        }
        repository.deleteById(id);
        return "Portfólio deletado com sucesso";
    }

    public List<PortfolioResponse> getPortfolioByUrlImagemPerfil(String urlImagemPerfil) {
        List<PortfolioEntity> portfolio = repository.findByUrlImagemPerfil(urlImagemPerfil);
        return portfolio.stream().map(portfolioResponseMapper::from).toList();
    }

    public List<PortfolioResponse> getPortfolioByUrlBackgroundPerfil(String urlBackgroundPerfil) {
        List<PortfolioEntity> portfolio = repository.findByUrlBackgroundPerfil(urlBackgroundPerfil);
        return portfolio.stream().map(portfolioResponseMapper::from).toList();
    }

    public List<PortfolioResponse> getPortfolioByDescricaoEmpresa(String descricaoEmpresa) {
        List<PortfolioEntity> portfolio = repository.findByDescricaoEmpresa(descricaoEmpresa);
        return portfolio.stream().map(portfolioResponseMapper::from).toList();
    }
    public List<PortfolioResponse> getPortfolioBySobreEmpresa(String sobreEmpresa) {
        List<PortfolioEntity> portfolio = repository.findBySobreEmpresa(sobreEmpresa);
        return portfolio.stream().map(portfolioResponseMapper::from).toList();
    }
    public List<PortfolioResponse> getPortfolioByLinkWebsiteEmpresa(String linkWebsiteEmpresa) {
        List<PortfolioEntity> portfolio = repository.findByLinkWebsiteEmpresa(linkWebsiteEmpresa);
        return portfolio.stream().map(portfolioResponseMapper::from).toList();
    }

    public List<PortfolioResponse> getPortfolioByDataEmpresaCertificada(LocalDate dataEmpresaCertificada) {
        List<PortfolioEntity> portfolio = repository.findByDataEmpresaCertificada(dataEmpresaCertificada);
        return portfolio.stream().map(portfolioResponseMapper::from).toList();
    }


    public List<PortfolioResponse> getPortfolioByFkPrestadoraServico(UUID fkPrestadoraServico) {
        List<PortfolioEntity> portfolio = repository.findByFkPrestadoraServico(fkPrestadoraServico);
        return portfolio.stream().map(portfolioResponseMapper::from).toList();
    }


}
