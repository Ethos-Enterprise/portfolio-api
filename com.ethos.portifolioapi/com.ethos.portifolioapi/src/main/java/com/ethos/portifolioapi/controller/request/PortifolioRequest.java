package com.ethos.portifolioapi.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

public record PortifolioRequest (@NotBlank(message = "Erro ao importar imagem") String urlImagemPerfil,
                                @NotBlank(message = "Erro ao importar imagem background") String urlBackgroundPerfil,
                                @NotBlank(message = "Descrição em branco") @Size(min=1, max=300, message = "O campo sobre esta fora do limite de caracteres") String descricaoEmpresa,
                                @NotBlank(message = "Texto sobre em branco") @Size(min=1, max=1000, message = "O campo sobre esta fora do limite de caracteres") String sobreEmpresa,
                                @NotBlank(message = "Link inválido") String linkWebsiteEmpresa,
                                @NotBlank(message = "Data inválida") LocalDate dataEmpresaCertificada,
                                @NotBlank(message = "Fk não encontrada") UUID fkPrestadoraServico){



}
