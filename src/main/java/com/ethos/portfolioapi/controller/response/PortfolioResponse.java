package com.ethos.portfolioapi.controller.response;

import java.time.LocalDate;
import java.util.UUID;

public record PortfolioResponse(UUID id, String urlImagemPerfil, String urlBackgroundPerfil, String descricaoEmpresa, String sobreEmpresa, String linkWebsiteEmpresa, LocalDate dataEmpresaCertificada, String fkPrestadoraServico){

}
