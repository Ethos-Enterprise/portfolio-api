package com.ethos.portfolioapi.service;

import com.ethos.portfolioapi.api.PrestadoraClient;
import com.ethos.portfolioapi.api.prestadoraDto.PrestadoraDto;
import com.ethos.portfolioapi.controller.request.PortfolioImagemRequest;
import com.ethos.portfolioapi.controller.request.PortfolioRequest;
import com.ethos.portfolioapi.controller.response.PortfolioImagemResponse;
import com.ethos.portfolioapi.controller.response.PortfolioResponse;
import com.ethos.portfolioapi.exception.EmpresaException;
import com.ethos.portfolioapi.exception.EmpresaNaoExisteException;
import com.ethos.portfolioapi.exception.PortfolioNaoExisteException;
import com.ethos.portfolioapi.mapper.PortfolioEntityMapper;
import com.ethos.portfolioapi.mapper.PortfolioImagemMapper;
import com.ethos.portfolioapi.mapper.PortfolioMapper;
import com.ethos.portfolioapi.mapper.PortfolioResponseMapper;
import com.ethos.portfolioapi.model.Portfolio;
import com.ethos.portfolioapi.repository.entity.PortfolioEntity;
import com.ethos.portfolioapi.repository.PortfolioRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Import(PortfolioService.PortfolioConfig.class)
public class PortfolioService {

    private final PortfolioRepository repository;
    private final PortfolioMapper portfolioModelMapper;
    private final PortfolioEntityMapper portfolioEntityMapper;
    private final PortfolioResponseMapper portfolioResponseMapper;
    private final PortfolioPilhaObj pilhaObj;
    private final PrestadoraClient prestadoraClient;
    private final PortfolioEntity portfolioEntity;
    private final PortfolioImagemMapper portfolioImagemMapper;


    public PortfolioResponse postPortfolio(PortfolioRequest request) {
        Portfolio model = portfolioModelMapper.from(request);
        PortfolioEntity entity = portfolioEntityMapper.from(model);
        PortfolioEntity savedEntity = savePortfolio(entity);
        return  portfolioResponseMapper.from(savedEntity);
    }

    public PortfolioEntity savePortfolio(PortfolioEntity entity) {
        try{
            PrestadoraDto prestadoraDto = prestadoraClient.getPrestadoraById(entity.getFkPrestadoraServico());
        }catch(FeignException e){
            if(e.status() == 404){
                throw new EmpresaNaoExisteException("Prestadora com o ID %s não existe".formatted(entity.getFkPrestadoraServico()));
            }
            throw new EmpresaException("Erro ao buscar prestadora");
        }
        PortfolioEntity portfolioSaved = repository.save(entity);
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
        List<PortfolioEntity> portfolios = repository.findAll();
        return portfolios.stream().map(portfolioResponseMapper::from).toList();
    }

    public PortfolioResponse getPortfolioById(UUID id) {
        Optional<PortfolioEntity> portfolio = repository.findById(id);
        if (portfolio.isEmpty()) {
            throw new PortfolioNaoExisteException("Empresa com id %s não existe".formatted(id.toString()));
        }
        PortfolioEntity portfolioReturned = portfolio.get();
        PortfolioResponse portfolioResponse = portfolioResponseMapper.from(portfolioReturned);
        return portfolioResponse;
    }

    public PortfolioResponse putPortfolioById(UUID id, PortfolioRequest request) {
        Optional<PortfolioEntity> entity;
        entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new PortfolioNaoExisteException("Portfólio com o id %s não existe".formatted(id));
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

    public PortfolioResponse getPortfolioByUrlImagemPerfil(String urlImagemPerfil) {
       PortfolioEntity portfolio = repository.findByUrlImagemPerfil(urlImagemPerfil);
        return portfolioResponseMapper.from(portfolio);
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


    public PortfolioResponse getPortfolioByFkPrestadoraServico(UUID fkPrestadoraServico) {
       PortfolioEntity portfolio = repository.findByFkPrestadoraServico(fkPrestadoraServico).orElseThrow(
               () -> new PortfolioNaoExisteException("Portfólio com o id %s não existe".formatted(fkPrestadoraServico))
       );
        return portfolioResponseMapper.from(portfolio);

    }

    public static String convertFileToBase64(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] fileBytes = Files.readAllBytes(path);

        String base64String = Base64.getEncoder().encodeToString(fileBytes);

        return base64String;
    }

    public PortfolioImagemResponse pilhaImagemPortfolio(String url_imagem, UUID id, Boolean cancelado) {
        String padrao = portfolioEntity.getUrlImagemPerfil();
        String novaImagem = url_imagem;

        pilhaObj.push(new PortfolioImagemRequest(padrao, cancelado, id));

        if (cancelado) {
            pilhaObj.pop();
        }

        pilhaObj.push(new PortfolioImagemRequest(novaImagem, cancelado, id));
        pilhaObj.push(new PortfolioImagemRequest(url_imagem, cancelado, id));

        // Retorna o objeto no topo da pilha
        PortfolioImagemResponse objetoNoTopo = pilhaObj.peek();

        return objetoNoTopo;
    }



    public static class PortfolioConfig {

        @Bean
        public PortfolioPilhaObj pilhaObj() {
            return new PortfolioPilhaObj();
        }

        @Bean
        public PortfolioEntity portfolioEntity() {
            return new PortfolioEntity();
        }
    }


}
