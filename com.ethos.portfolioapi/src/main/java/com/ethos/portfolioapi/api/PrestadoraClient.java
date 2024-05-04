package com.ethos.portfolioapi.api;

import com.ethos.portfolioapi.api.prestadoraDto.PrestadoraDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "prestadora", url = "http://localhost:8085/v1.0/prestadoras")
public interface PrestadoraClient {
    @GetMapping("/{id}")
    PrestadoraDto getPrestadoraById(@PathVariable UUID id);

}
