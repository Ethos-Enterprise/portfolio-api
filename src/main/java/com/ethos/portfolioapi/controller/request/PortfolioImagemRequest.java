package com.ethos.portfolioapi.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PortfolioImagemRequest(@NotBlank(message = "Erro ao importar imagem") String urlImagemPerfil,
                                   @NotBlank(message = "Erro ao importar imagem background") Boolean cancelado ,
                                   @NotNull(message = "id não encontrado") UUID id){

}
