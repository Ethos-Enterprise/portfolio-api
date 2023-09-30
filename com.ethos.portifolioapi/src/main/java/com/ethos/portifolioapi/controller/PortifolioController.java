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
        } else if (cnpj != null) {
            return empresaService.getEmpresaByCnpj(cnpj);
        } else if (telefone != null) {
            return empresaService.getEmpresaByTelefone(telefone);
        } else if (setor != null) {
            return empresaService.getEmpresaBySetor(setor);
        }
        return empresaService.getAllEmpresa();
    }


//    putPortifolioById
}
