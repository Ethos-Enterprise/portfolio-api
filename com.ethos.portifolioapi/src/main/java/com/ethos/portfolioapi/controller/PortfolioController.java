package com.ethos.portfolioapi.controller;

import com.ethos.portfolioapi.controller.request.PortfolioRequest;
import com.ethos.portfolioapi.controller.response.PortfolioResponse;
import com.ethos.portfolioapi.service.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1.0/portfolios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PortfolioController {

    public final PortfolioService portfolioService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PortfolioResponse postPortfolio(@RequestBody @Valid PortfolioRequest request) {
        return portfolioService.postPortfolio(request);
    }

    @GetMapping
    public List<PortfolioResponse> getAllPortfolio(@RequestParam(value = "urlImagemPerfil", required = false) String urlImagemPerfil, @RequestParam(value = "urlBackgroundPerfil", required = false) String urlBackgroundPerfil, @RequestParam(value = "descricaoEmpresa", required = false) String descricaoEmpresa, @RequestParam(value = "sobreEmpresa", required = false) String sobreEmpresa, @RequestParam(value = "linkWebsiteEmpresa", required = false) String linkWebsiteEmpresa, @RequestParam(value = "dataEmpresaCertificada", required = false) LocalDate dataEmpresaCertificada, @RequestParam(value = "fkPrestadoraServico", required = false) UUID fkPrestadoraServico) {
        if (urlImagemPerfil != null) {
            return portfolioService.getPortfolioByUrlImagemPerfil(urlImagemPerfil);
        } else if (urlBackgroundPerfil != null) {
            return portfolioService.getPortfolioByUrlBackgroundPerfil(urlBackgroundPerfil);
        } else if (descricaoEmpresa != null) {
            return portfolioService.getPortfolioByDescricaoEmpresa(descricaoEmpresa);
        } else if (sobreEmpresa != null) {
            return portfolioService.getPortfolioBySobreEmpresa(sobreEmpresa);
        }else if (linkWebsiteEmpresa != null) {
            return portfolioService.getPortfolioByLinkWebsiteEmpresa(linkWebsiteEmpresa);
        }else if (dataEmpresaCertificada != null) {
            return portfolioService.getPortfolioByDataEmpresaCertificada(dataEmpresaCertificada);
        }else if (fkPrestadoraServico != null) {
            return portfolioService.getPortfolioByFkPrestadoraServico(fkPrestadoraServico);
        }
        return portfolioService.getAllPortfolio();
    }

    @GetMapping("/{id}")
    public PortfolioResponse getPortfolioById(@PathVariable UUID id) {
        return portfolioService.getPortfolioById(id);
    }

    @PutMapping("/{id}")
    public PortfolioResponse putPortfolioById(@PathVariable UUID id, @RequestBody PortfolioRequest request) {
        return portfolioService.putPortfolioById(id, request);
    }

    @DeleteMapping("/{id}")
    public String deletePortfolioById(@PathVariable UUID id) {
        return portfolioService.deletePortfolioById(id);
    }

}
