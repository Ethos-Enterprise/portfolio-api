package com.ethos.portfolioapi.service;

import com.ethos.portfolioapi.api.PrestadoraClient;
import com.ethos.portfolioapi.api.prestadoraDto.PrestadoraDto;
import com.ethos.portfolioapi.controller.request.PortfolioRequest;
import com.ethos.portfolioapi.mapper.PortfolioEntityMapper;
import com.ethos.portfolioapi.mapper.PortfolioImagemMapper;
import com.ethos.portfolioapi.mapper.PortfolioMapper;
import com.ethos.portfolioapi.mapper.PortfolioResponseMapper;
import com.ethos.portfolioapi.repository.PortfolioRepository;
import com.ethos.portfolioapi.repository.entity.PortfolioEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

    @InjectMocks
    PortfolioService service;

    @Mock
    PortfolioRepository repository;

    @Mock
    PrestadoraClient prestadoraClient;

    @Spy
    PortfolioMapper portfolioMapper;

    @Spy
    PortfolioImagemMapper portfolioImagemMapper;

    @Spy
    PortfolioEntityMapper portfolioEntityMapper;

    @Spy
    PortfolioResponseMapper portfolioResponseMapper;

    @Captor
    ArgumentCaptor<UUID> idClienteArgumentCaptor;

    @Captor
    ArgumentCaptor<PortfolioEntity> portfolioEntityArgumentCaptor;

    public static PortfolioRequest createPortfolioRequest() {
        return new PortfolioRequest(
                "descricaoEmpresa",
                "sobreEmpresa",
                "linkWebsiteEmpresa",
                LocalDate.now(),
                UUID.fromString("62b3105b-4d0e-4b98-bbca-19991ebcb0b8")
        );
    }

    public static PortfolioEntity createPortfolioEntity() {
        return new PortfolioEntity(
                "imagem.jpg",
                "imagemBackground.jpg",
                "descricaoEmpresa",
                "sobreEmpresa",
                "linkWebsiteEmpresa",
                LocalDate.now(),
                UUID.fromString("62b3105b-4d0e-4b98-bbca-19991ebcb0b8")
        );
    }

    public static PrestadoraDto createPrestadoraDto(){
        return new PrestadoraDto(
                UUID.fromString("62b3105b-4d0e-4b98-bbca-19991ebcb0b8")
        );
    }

    @Test
    void should_create_a_portfolio() {
        PortfolioEntity entity = createPortfolioEntity();
       PortfolioRequest portfolioRequest = createPortfolioRequest();
       when(repository.save(portfolioEntityArgumentCaptor.capture())).thenReturn(entity);
       when(prestadoraClient.getPrestadoraById(idClienteArgumentCaptor.capture())).thenReturn(createPrestadoraDto());

       PortfolioEntity entitySaved = service.savePortfolio(entity);

       PortfolioEntity entityExpected = portfolioEntityArgumentCaptor.getValue();

       assertEquals(entityExpected, entitySaved);
    }

    @Test
    void savePortfolio() {
    }

    @Test
    void updateUrlImagemPerfil() {
    }

    @Test
    void updateurlBackgroundPerfil() {
    }

    @Test
    void getAllPortfolio() {
    }

    @Test
    void getPortfolioById() {
    }

    @Test
    void putPortfolioById() {
    }

    @Test
    void deletePortfolioById() {
    }

    @Test
    void getPortfolioByUrlImagemPerfil() {
    }

    @Test
    void getPortfolioByUrlBackgroundPerfil() {
    }

    @Test
    void getPortfolioByDescricaoEmpresa() {
    }

    @Test
    void getPortfolioBySobreEmpresa() {
    }

    @Test
    void getPortfolioByLinkWebsiteEmpresa() {
    }

    @Test
    void getPortfolioByDataEmpresaCertificada() {
    }

    @Test
    void getPortfolioByFkPrestadoraServico() {
    }

    @Test
    void convertFileToBase64() {
    }

    @Test
    void pilhaImagemPortfolio() {
    }
}