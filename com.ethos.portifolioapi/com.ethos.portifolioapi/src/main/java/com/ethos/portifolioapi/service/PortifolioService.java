package com.ethos.portifolioapi.service;

import com.ethos.portifolioapi.controller.request.PortifolioRequest;
import com.ethos.portifolioapi.controller.response.PortifolioResponse;
import com.ethos.portifolioapi.mapper.PortifolioEntityMapper;
import com.ethos.portifolioapi.mapper.PortifolioMapper;
import com.ethos.portifolioapi.model.Portifolio;
import com.ethos.portifolioapi.repository.entity.PortifolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortifolioService {

    private final PortifolioRepository repository;
    private final PortifolioMapper portifolioModelMapper;
    private final PortifolioEntityMapper portifolioEntityMapper;
    private final PortifolioResponse portifolioResponseMapper;


    public PortifolioResponse postPotifolio(PortifolioRequest request){
    Portifolio model = portifolioModelMapper.from(request);



    }


}
