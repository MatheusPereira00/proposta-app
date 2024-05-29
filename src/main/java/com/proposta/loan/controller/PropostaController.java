package com.proposta.loan.controller;

import com.proposta.loan.dto.PropostaRequestDto;
import com.proposta.loan.dto.PropostaResponseDto;
import com.proposta.loan.service.PropostaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/proposta")
@RestController
public class PropostaController {


    private PropostaService propostaService;

    public PropostaController(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto propostaRequestDto){
        PropostaResponseDto propostaResponseDto = propostaService.criar(propostaRequestDto);
        return ResponseEntity.ok().body(propostaResponseDto);
    }
}
