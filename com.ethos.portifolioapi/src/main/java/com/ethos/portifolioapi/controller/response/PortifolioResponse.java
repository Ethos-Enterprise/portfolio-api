package com.ethos.portifolioapi.controller.response;

import java.time.LocalDate;
import java.util.UUID;

public record PortifolioResponse (UUID id, String urlImagemPerfil, String urlBackgroundPerfil, String descricaoEmpresa, String sobreEmpresa, String linkWebsiteEmpresa, LocalDate dataEmpresaCertificada, String fkPrestadoraServico){

}
