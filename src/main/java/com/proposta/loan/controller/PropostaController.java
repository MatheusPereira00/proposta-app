package com.proposta.loan.controller;

import com.proposta.loan.dto.PropostaRequestDto;
import com.proposta.loan.dto.PropostaResponseDto;
import com.proposta.loan.service.PropostaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/proposta")
public class PropostaController {

    private final PropostaService propostaService;

    public PropostaController(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto propostaRequestDto){
        PropostaResponseDto propostaResponseDto = propostaService.criar(propostaRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(propostaResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> obterProposta(){
        List<PropostaResponseDto> propostaResponseDtoList = propostaService.obterProposta();
        return ResponseEntity.ok().body(propostaResponseDtoList);
    }

}
