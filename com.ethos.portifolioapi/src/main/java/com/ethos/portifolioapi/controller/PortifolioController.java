package com.ethos.portifolioapi.controller;

import com.ethos.portifolioapi.controller.request.PortifolioRequest;
import com.ethos.portifolioapi.controller.response.PortifolioResponse;
import com.ethos.portifolioapi.service.PortifolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1.0/portifolios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PortifolioController {

    public final PortifolioService portifolioService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PortifolioResponse postPortifolio(@RequestBody @Valid PortifolioRequest request) {
        return portifolioService.postPotifolio(request);
    }

    @GetMapping
    public List<PortifolioResponse> getAllPortifolio(@RequestParam(value = "urlImagemPerfil", required = false) String urlImagemPerfil, @RequestParam(value = "urlBackgroundPerfil", required = false) String urlBackgroundPerfil, @RequestParam(value = "descricaoEmpresa", required = false) String descricaoEmpresa, @RequestParam(value = "sobreEmpresa", required = false) String sobreEmpresa, @RequestParam(value = "linkWebsiteEmpresa", required = false) String linkWebsiteEmpresa, @RequestParam(value = "dataEmpresaCertificada", required = false) LocalDate dataEmpresaCertificada, @RequestParam(value = "fkPrestadoraServico", required = false) UUID fkPrestadoraServico) {
        if (urlImagemPerfil != null) {
            return portifolioService.getPortifolioByUrlImagemPerfil(urlImagemPerfil);
        } else if (urlBackgroundPerfil != null) {
            return portifolioService.getPortifolioByUrlBackgroundPerfil(urlBackgroundPerfil);
        } else if (descricaoEmpresa != null) {
            return portifolioService.getPortifolioByDescricaoEmpresa(descricaoEmpresa);
        } else if (sobreEmpresa != null) {
            return portifolioService.getPortifolioBySobreEmpresa(sobreEmpresa);
        }else if (linkWebsiteEmpresa != null) {
            return portifolioService.getPortifolioByLinkWebsiteEmpresa(linkWebsiteEmpresa);
        }else if (dataEmpresaCertificada != null) {
            return portifolioService.getPortifolioByDataEmpresaCertificada(dataEmpresaCertificada);
        }else if (fkPrestadoraServico != null) {
            return portifolioService.getPortifolioByFkPrestadoraServico(fkPrestadoraServico);
        }
        return portifolioService.getAllPortifolio();
    }

    @GetMapping("/{id}")
    public PortifolioResponse getPortifolioById(@PathVariable UUID id) {
        return portifolioService.getPortifolioById(id);
    }

    @PutMapping("/{id}")
    public PortifolioResponse putPortifolioById(@PathVariable UUID id, @RequestBody PortifolioRequest request) {
        return portifolioService.putPortifolioById(id, request);
    }

    @DeleteMapping("/{id}")
    public String deletePortifolioById(@PathVariable UUID id) {
        return portifolioService.deletePortifolioById(id);
    }

}
