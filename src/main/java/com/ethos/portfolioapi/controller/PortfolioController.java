package com.ethos.portfolioapi.controller;

import com.ethos.portfolioapi.controller.request.PortfolioImagemRequest;
import com.ethos.portfolioapi.controller.request.PortfolioRequest;
import com.ethos.portfolioapi.controller.response.PortfolioImagemResponse;
import com.ethos.portfolioapi.controller.response.PortfolioResponse;
import com.ethos.portfolioapi.repository.entity.PortfolioEntity;
import com.ethos.portfolioapi.service.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;

import java.util.List;

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
    public List<PortfolioResponse> getAllPortfolio() {
        return portfolioService.getAllPortfolio();
    }

    @GetMapping("/{id}")
    public PortfolioResponse getPortfolioById(@PathVariable UUID id) {
        return portfolioService.getPortfolioById(id);
    }

    @GetMapping("/prestadora/{fkPrestadoraServico}")
    public PortfolioResponse getPortfolioByFk(@PathVariable UUID fkPrestadoraServico){
        return portfolioService.getPortfolioByFkPrestadoraServico(fkPrestadoraServico);
    }

    @PutMapping("/{id}")
    public PortfolioResponse putPortfolioById(@PathVariable UUID id, @RequestBody PortfolioRequest request) {
        return portfolioService.putPortfolioById(id, request);
    }

    @PutMapping("/{id}/imagem")
    public PortfolioImagemResponse putPortfolioImagemById(@PathVariable UUID id, @RequestParam Boolean cancelado, @RequestParam String url_imagem) {
        return portfolioService.pilhaImagemPortfolio(url_imagem, id, false);
    }


    @DeleteMapping("/{id}")
    public String deletePortfolioById(@PathVariable UUID id) {
        return portfolioService.deletePortfolioById(id);
    }


    private Path diretorioBase = Path.of(System.getProperty("java.io.tmpdir") + "/arquivos"); // temporario


    @PatchMapping("/upload/perfil/{id}")
    public ResponseEntity<PortfolioResponse> uploadUrlPerfil(@RequestParam("arquivo") MultipartFile file, @PathVariable UUID id) {
        if (file.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        if (!this.diretorioBase.toFile().exists()) {
            this.diretorioBase.toFile().mkdir();
        }

        String nomeArquivoFormatado = formatarNomeArquivo(file.getOriginalFilename());

        String filePath = this.diretorioBase + "/" + nomeArquivoFormatado;
        File dest = new File(filePath);

        try {
           file.transferTo(dest);
//            portfolioService.convertFileToBase64(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível salvar o arquivo", null);
        }

        PortfolioResponse updatedPortfolio = portfolioService.updateUrlImagemPerfil(id, nomeArquivoFormatado);

        return ResponseEntity.status(200).body(updatedPortfolio);
    }
    @PatchMapping("/upload/background/{id}")
    public ResponseEntity<PortfolioResponse> uploadUrlBackgroundPerfil(@RequestParam("arquivo") MultipartFile file, @PathVariable UUID id) {
        if (file.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        if (!this.diretorioBase.toFile().exists()) {
            this.diretorioBase.toFile().mkdir();
        }

        String nomeArquivoFormatado = formatarNomeArquivo(file.getOriginalFilename());

        String filePath = this.diretorioBase + "/" + nomeArquivoFormatado;
        File dest = new File(filePath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível salvar o arquivo", null);
        }

        PortfolioResponse updatedPortfolio = portfolioService.updateurlBackgroundPerfil(id, nomeArquivoFormatado);

        return ResponseEntity.status(200).body(updatedPortfolio);
    }



    private String formatarNomeArquivo(String nomeOriginal) {
        return String.format("%s_%s", UUID.randomUUID(), nomeOriginal);
    }
    }
