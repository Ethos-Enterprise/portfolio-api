package com.ethos.portifolioapi.controller;

import com.ethos.portifolioapi.controller.response.PortifolioResponse;
import com.ethos.portifolioapi.service.PortifolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1.0/portifolios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PortifolioController {

    public final PortifolioService portifolioService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PortifolioResponse postPortifolio(){
    return null;
    }

}
