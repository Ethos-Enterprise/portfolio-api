package com.ethos.portfolioapi.service;

import com.ethos.portfolioapi.api.PrestadoraClient;
import com.ethos.portfolioapi.api.prestadoraDto.PrestadoraDto;
import com.ethos.portfolioapi.controller.request.PortfolioRequest;
import com.ethos.portfolioapi.controller.response.PortfolioResponse;
import com.ethos.portfolioapi.exception.EmpresaException;
import com.ethos.portfolioapi.exception.EmpresaNaoExisteException;
import com.ethos.portfolioapi.exception.PortfolioNaoExisteException;
import com.ethos.portfolioapi.mapper.*;
import com.ethos.portfolioapi.mapper.PortfolioEntityMapperImpl;
import com.ethos.portfolioapi.mapper.PortfolioImagemMapperImpl;
import com.ethos.portfolioapi.mapper.PortfolioMapperImpl;
import com.ethos.portfolioapi.mapper.PortfolioResponseMapperImpl;
import com.ethos.portfolioapi.repository.PortfolioRepository;
import com.ethos.portfolioapi.repository.entity.PortfolioEntity;
import feign.FeignException;
import feign.Headers;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Map;
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
    PortfolioMapper portfolioMapper = new PortfolioMapperImpl();

    @Spy
    PortfolioImagemMapper portfolioImagemMapper = new PortfolioImagemMapperImpl();

    @Spy
    PortfolioEntityMapper portfolioEntityMapper = new PortfolioEntityMapperImpl();

    @Spy
    PortfolioResponseMapper portfolioResponseMapper = new PortfolioResponseMapperImpl();

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
    void should_throws_EmpresaNaoExisteException() {
        PortfolioEntity entity = createPortfolioEntity();
        FeignException feignException = FeignException.errorStatus("Prestadora não existe",
                Response.builder()
                        .status(404)
                        .reason("Not Found")
                        .request(Request.create(Request.HttpMethod.GET, "", Map.of(), null, null, null))
                        .build());
        when(prestadoraClient.getPrestadoraById(idClienteArgumentCaptor.capture())).thenThrow(feignException);

        EmpresaNaoExisteException e = assertThrows(EmpresaNaoExisteException.class, () -> service.savePortfolio(entity));

        assertEquals(e.getMessage(), "Prestadora com o ID 62b3105b-4d0e-4b98-bbca-19991ebcb0b8 não existe");
    }

    @Test
    void should_return_all_portfolios() {
        PortfolioEntity entity = createPortfolioEntity();
        when(repository.findAll()).thenReturn(java.util.List.of(entity));

        var portfolios = service.getAllPortfolio();

        assertEquals(portfolios.size(), 1);
    }

    @Test
    void should_throws_PortfolioNaoExisteException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(java.util.Optional.empty());

        PortfolioNaoExisteException e = assertThrows(PortfolioNaoExisteException.class, () -> service.getPortfolioById(id));

        assertEquals(e.getMessage(), "Empresa com id %s não existe".formatted(id.toString()));
    }

    @Test
    void should_return_a_portfolio_by_id() {
        PortfolioEntity entity = createPortfolioEntity();
        when(repository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));

        PortfolioResponse portfolio = service.getPortfolioById(entity.getId());

        assertEquals(portfolio.id(), entity.getId());
    }

    @Test
    void should_update_url_imagem_perfil() {
        PortfolioEntity entity = createPortfolioEntity();
        when(repository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));
        when(repository.save(portfolioEntityArgumentCaptor.capture())).thenReturn(entity);

        PortfolioResponse portfolio = service.updateUrlImagemPerfil(entity.getId(), "imagem.jpg");

        assertEquals(portfolio.urlImagemPerfil(), "imagem.jpg");
    }

    @Test
    void should_update_url_background_perfil() {
        PortfolioEntity entity = createPortfolioEntity();
        when(repository.findById(entity.getId())).thenReturn(java.util.Optional.of(entity));
        when(repository.save(portfolioEntityArgumentCaptor.capture())).thenReturn(entity);

        PortfolioResponse portfolio = service.updateurlBackgroundPerfil(entity.getId(), "imagemBackground.jpg");

        assertEquals(portfolio.urlBackgroundPerfil(), "imagemBackground.jpg");
    }

    @Test
    void should_throws_PortfolioNaoExisteException_when_update_url_imagem_perfil() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(java.util.Optional.empty());

        PortfolioNaoExisteException e = assertThrows(PortfolioNaoExisteException.class, () -> service.updateUrlImagemPerfil(id, "imagem.jpg"));

        assertEquals(e.getMessage(), "Portfólio com o ID %s não existe".formatted(id));
    }

    @Test
    void should_throws_PortfolioNaoExisteException_when_update_url_background_perfil() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(java.util.Optional.empty());

        PortfolioNaoExisteException e = assertThrows(PortfolioNaoExisteException.class, () -> service.updateurlBackgroundPerfil(id, "imagemBackground.jpg"));

        assertEquals(e.getMessage(), "Portfólio com o ID %s não existe".formatted(id));
    }
}